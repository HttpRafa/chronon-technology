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
package de.rafael.mods.chronon.technology.screen.block;

import de.rafael.mods.chronon.technology.block.entity.CollectorBlockEntity;
import de.rafael.mods.chronon.technology.item.AcceleratorItem;
import de.rafael.mods.chronon.technology.item.PlatingItem;
import de.rafael.mods.chronon.technology.registry.ModBlocks;
import de.rafael.mods.chronon.technology.registry.ModScreenHandlers;
import de.rafael.mods.chronon.technology.screen.block.base.BaseContainerMenu;
import de.rafael.mods.chronon.technology.screen.slot.TypeLockedSlot;
import lombok.Getter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 09/08/2023
 */

@Getter
public class CollectorScreenHandler extends BaseContainerMenu {

    public static final int BAR_SIZE = 132;

    private final Level level;
    private final CollectorBlockEntity entity;
    private final ContainerData containerData;

    public CollectorScreenHandler(int syncId, Inventory inventory, @NotNull FriendlyByteBuf extraData) {
        this(syncId, inventory, inventory.player.getLevel().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(CollectorBlockEntity.Data.SYNC_AMOUNT));
    }

    public CollectorScreenHandler(int syncId, @NotNull Inventory inventory, BlockEntity entity, ContainerData containerData) {
        super(ModScreenHandlers.CHRONON_COLLECTOR.get(), syncId);
        checkContainerSize(inventory, CollectorBlockEntity.Data.INVENTORY_SIZE);
        this.entity = ((CollectorBlockEntity) entity);
        this.level = inventory.player.getLevel();
        inventory.startOpen(inventory.player);
        this.containerData = containerData;

        addPlayerInventory(inventory, 162, 104);
        addDataSlots(containerData);

        this.entity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new TypeLockedSlot(handler, CollectorBlockEntity.Data.PLATING_SLOT, 80, 52, PlatingItem.class));
            this.addSlot(new TypeLockedSlot(handler, CollectorBlockEntity.Data.STORAGE_SLOT, 152, 72, AcceleratorItem.class));
        });
    }

    public int scaledBarSize() {
        long chronons = this.entity.getStoredChronons();
        double a = chronons * (double)BAR_SIZE;
        a /= CollectorBlockEntity.MAX_STORAGE_SIZE;
        return (int) a;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int invSlot) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if(slot.hasItem()) {
            stack = slot.getItem().copy();
            if(invSlot < this.entity.getInventory().getSlots()) {
                if(!this.moveItemStackTo(slot.getItem(), this.entity.getInventory().getSlots(), this.slots.size(), true)) return ItemStack.EMPTY;
            } else if(!this.moveItemStackTo(slot.getItem(), 0, this.entity.getInventory().getSlots(), false)) return ItemStack.EMPTY;
            if(slot.getItem().isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return stack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(ContainerLevelAccess.create(level, entity.getBlockPos()), player, ModBlocks.CHRONON_COLLECTOR.get());
    }

}
