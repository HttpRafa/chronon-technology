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

import de.rafael.mods.chronon.technology.attribute.AttributeHolder;
import de.rafael.mods.chronon.technology.block.base.entity.BaseMachineBlockEntity;
import de.rafael.mods.chronon.technology.client.network.PacketPlayInChrononSync;
import de.rafael.mods.chronon.technology.config.AcceleratorConfig;
import de.rafael.mods.chronon.technology.item.PlatingItem;
import de.rafael.mods.chronon.technology.item.abstracted.ChrononStorageItem;
import de.rafael.mods.chronon.technology.registry.ModBlockEntities;
import de.rafael.mods.chronon.technology.registry.ModPackets;
import de.rafael.mods.chronon.technology.screen.block.CollectorScreenHandler;
import de.rafael.mods.chronon.technology.util.helper.CompactContainerData;
import de.rafael.mods.chronon.technology.util.values.NbtKey;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

@Getter
public class CollectorBlockEntity extends BaseMachineBlockEntity implements AttributeHolder {

    public static final long MAX_STORAGE_SIZE = AcceleratorConfig.storageSize * 2;

    private final ContainerData containerData;

    private boolean requiresSync = false;
    private int progress = 0;

    @Setter
    private long storedChronons = 0;

    public CollectorBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.CHRONON_COLLECTOR.get(), blockPos, blockState,
                Data.INVENTORY_SIZE, Component.translatable("screen.chronontech.collector"));
        this.containerData = new CompactContainerData(Data.SYNC_AMOUNT) {
            @Override
            public int get(int i) {
                if(i == 0) {
                    return progress;
                } else {
                    return 0;
                }
            }

            @Override
            public void set(int i, int value) {
                if (i == 0) {
                    progress = value;
                }
            }
        };
    }

    private int ticks = 0;
    @Override
    public void serverTick(Level level, BlockPos blockPos, BlockState blockState) {
        var platingStack = this.inventory.getStackInSlot(Data.PLATING_SLOT);
        if (!platingStack.isEmpty() && platingStack.getItem() instanceof PlatingItem item) {
            ticks++;
            if (item.getPlatingType().getEfficiency() < 0) {
                this.storedChronons = MAX_STORAGE_SIZE;
                this.ticks = 0;
            } else if (ticks >= item.getPlatingType().getTicksPerChronon()) {
                this.ticks = 0;
                this.storedChronons = Math.min(MAX_STORAGE_SIZE, ++this.storedChronons);
            }
            setChanged(level, blockPos, blockState);
            requiresSync = true;
        }

        var storageStack = this.inventory.getStackInSlot(Data.STORAGE_SLOT);
        if (!storageStack.isEmpty() && storageStack.getItem() instanceof ChrononStorageItem item) {
            long amount = Math.min(this.storedChronons, item.getSpaceLeft(storageStack, 20 * 60));
            item.addChronons(storageStack, amount);
            this.storedChronons -= amount;
            setChanged(level, blockPos, blockState);
            requiresSync = true;
        }
        syncChronons();
    }

    @Override
    public void clientTick(Level level, BlockPos blockPos, BlockState blockState) {
    }

    public void syncChronons() {
        assert level != null;
        if(level.isClientSide()) return;
        if(requiresSync) {
            requiresSync = false;

            ModPackets.channel().send(PacketDistributor.ALL.noArg(),
                    new PacketPlayInChrononSync(this.getBlockPos(), this.storedChronons));
        }
    }

    public boolean isCollecting() {
        return this.containerData.get(0) > 0;
    }

    @Override
    public AbstractContainerMenu createMenu(int syncId, @NotNull Inventory inventory, @NotNull Player player) {
        return new CollectorScreenHandler(syncId, inventory, this, this.containerData);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putLong(NbtKey.STORED_CHRONONS.getKey(), storedChronons);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        this.storedChronons = tag.getLong(NbtKey.STORED_CHRONONS.getKey());
    }

    @Override
    public List<NbtKey> getNbtKeys() {
        return List.of(NbtKey.STORED_CHRONONS, NbtKey.INVENTORY);
    }

    public static class Data {

        public static final int SYNC_AMOUNT = 1;

        public static final int INVENTORY_SIZE = 2;

        public static final int PLATING_SLOT = 0;
        public static final int STORAGE_SLOT = 1;

    }

}
