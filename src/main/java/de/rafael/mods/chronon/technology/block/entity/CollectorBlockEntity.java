/*
 * MIT License
 *
 * Copyright (c) 2023 Rafael
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package de.rafael.mods.chronon.technology.block.entity;

import de.rafael.mods.chronon.technology.block.base.entity.BaseMachineBlockEntity;
import de.rafael.mods.chronon.technology.item.PlatingItem;
import de.rafael.mods.chronon.technology.item.abstracted.ChrononStorageItem;
import de.rafael.mods.chronon.technology.registry.ModBlockEntities;
import de.rafael.mods.chronon.technology.screen.block.CollectorScreenHandler;
import de.rafael.mods.chronon.technology.util.helper.CompactContainerData;
import de.rafael.mods.chronon.technology.util.values.NbtKeys;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

@Getter
public class CollectorBlockEntity extends BaseMachineBlockEntity {

    public static final int INVENTORY_SIZE = 2;

    public static final int SYNC_AMOUNT = 2;
    public static final int PROGRESS_SYNC_ID = 0;
    public static final int STORED_CHRONONS_SYNC_ID = 1;

    public static final int PLATING_SLOT = 0;
    public static final int STORAGE_SLOT = 1;

    public static final int MAX_STORAGE_SIZE = ChrononStorageItem.CORE_MAX_STORAGE_SIZE * 2;

    private final ContainerData containerData;

    private int storedChronons = 0;
    private int progress = 0;

    public CollectorBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.CHRONON_COLLECTOR.get(), blockPos, blockState,
                INVENTORY_SIZE, Component.translatable("screen.chronontech.collector"));
        this.containerData = new CompactContainerData(SYNC_AMOUNT) {
            @Override
            public int get(int i) {
                return switch (i) {
                    case PROGRESS_SYNC_ID -> progress;
                    case STORED_CHRONONS_SYNC_ID -> storedChronons;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case PROGRESS_SYNC_ID -> progress = value;
                    case STORED_CHRONONS_SYNC_ID -> storedChronons = value;
                }
            }
        };
    }

    private int ticks = 0;
    @Override
    public void tick(@NotNull Level level, BlockPos blockPos, BlockState blockState) {
        if(level.isClientSide()) return;

        var platingStack = this.inventory.getStackInSlot(PLATING_SLOT);
        if(!platingStack.isEmpty() && platingStack.getItem() instanceof PlatingItem item) {
            ticks++;
            if(item.getPlatingType().getEfficiency() < 0) {
                this.storedChronons = MAX_STORAGE_SIZE;
                this.ticks = 0;
            } else if(ticks >= item.getPlatingType().getTicksPerChronon()) {
                this.ticks = 0;
                this.storedChronons = Math.min(MAX_STORAGE_SIZE, ++this.storedChronons);
            }
            setChanged(level, blockPos, blockState);
        }

        var storageStack = this.inventory.getStackInSlot(STORAGE_SLOT);
        if(!storageStack.isEmpty() && storageStack.getItem() instanceof ChrononStorageItem item) {
            int amount = Math.min(this.storedChronons, item.getSpaceLeft(storageStack, 20 * 60));
            item.addChronons(storageStack, amount);
            this.storedChronons -= amount;
            setChanged(level, blockPos, blockState);
        }
    }

    @Override
    public AbstractContainerMenu createMenu(int syncId, @NotNull Inventory inventory, @NotNull Player player) {
        return new CollectorScreenHandler(syncId, inventory, this, this.containerData);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt(NbtKeys.STORED_CHRONONS.getKey(), storedChronons);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        this.storedChronons = tag.getInt(NbtKeys.STORED_CHRONONS.getKey());
    }

}
