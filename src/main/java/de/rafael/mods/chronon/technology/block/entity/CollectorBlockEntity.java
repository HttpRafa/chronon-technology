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
import de.rafael.mods.chronon.technology.config.AcceleratorConfig;
import de.rafael.mods.chronon.technology.item.PlatingItem;
import de.rafael.mods.chronon.technology.item.abstracted.ChrononStorageItem;
import de.rafael.mods.chronon.technology.network.ModPackets;
import de.rafael.mods.chronon.technology.registry.ModBlockEntities;
import de.rafael.mods.chronon.technology.screen.block.CollectorScreenHandler;
import de.rafael.mods.chronon.technology.util.helper.CompactContainerData;
import de.rafael.mods.chronon.technology.util.helper.NetworkHelper;
import de.rafael.mods.chronon.technology.util.values.NbtKeys;
import lombok.Getter;
import lombok.Setter;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.core.jmx.Server;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

@Getter
@Setter
public class CollectorBlockEntity extends BaseMachineBlockEntity {

    public static final long MAX_STORAGE_SIZE = AcceleratorConfig.storageSize * 2;

    private final ContainerData containerData;

    private boolean requiresSync = false;
    private int progress = 0;
    private long storedChronons = 0;

    public CollectorBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.CHRONON_COLLECTOR, blockPos, blockState,
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
    public void tick(@NotNull Level level, BlockPos blockPos, BlockState blockState) {
        if(level.isClientSide()) return;

        var platingStack = getItem(Data.PLATING_SLOT);
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
            requiresSync = true;
        }

        var storageStack = getItem(Data.STORAGE_SLOT);
        if(!storageStack.isEmpty() && storageStack.getItem() instanceof ChrononStorageItem item) {
            long amount = Math.min(this.storedChronons, item.getSpaceLeft(storageStack, 20 * 60));
            item.addChronons(storageStack, amount);
            this.storedChronons -= amount;
            setChanged(level, blockPos, blockState);
            requiresSync = true;
        }
        syncChronons();
    }

    public void syncChronons() {
        assert level != null;
        if(level.isClientSide()) return;
        if(requiresSync) {
            requiresSync = false;

            FriendlyByteBuf byteBuf = PacketByteBufs.create();
            byteBuf.writeBlockPos(this.getBlockPos());
            byteBuf.writeLong(this.storedChronons);
            NetworkHelper.broadCast(ModPackets.Client.CHRONON_SYNC, (ServerLevel) this.getLevel(), this.getBlockPos(), byteBuf);
        }
    }

    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory inventory, Player player) {
        return new CollectorScreenHandler(syncId, inventory, this, this.containerData);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayer player, @NotNull FriendlyByteBuf buf) {
        buf.writeBlockPos(this.getBlockPos());
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        ContainerHelper.saveAllItems(compoundTag, this.inventory);
        compoundTag.putLong(NbtKeys.STORED_CHRONONS.getKey(), storedChronons);
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        ContainerHelper.loadAllItems(compoundTag, this.inventory);
        this.storedChronons = compoundTag.getLong(NbtKeys.STORED_CHRONONS.getKey());
    }

    @Override
    public int @NotNull [] getSlotsForFace(Direction direction) {
        return new int[0];
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack itemStack, @Nullable Direction direction) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack itemStack, Direction direction) {
        return false;
    }

    public static class Data {

        public static final int SYNC_AMOUNT = 1;

        public static final int INVENTORY_SIZE = 2;

        public static final int PLATING_SLOT = 0;
        public static final int STORAGE_SLOT = 1;

    }

}
