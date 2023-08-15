package de.rafael.mods.chronon.technology.screen.block;

import de.rafael.mods.chronon.technology.block.entity.CollectorBlockEntity;
import de.rafael.mods.chronon.technology.item.AcceleratorItem;
import de.rafael.mods.chronon.technology.item.PlatingItem;
import de.rafael.mods.chronon.technology.registry.ModScreenHandlers;
import de.rafael.mods.chronon.technology.screen.block.base.BaseContainerMenu;
import de.rafael.mods.chronon.technology.screen.slot.TypeLockedSlot;
import lombok.Getter;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 09/08/2023
 */

@Getter
public class CollectorScreenHandler extends BaseContainerMenu {

    public static final int BAR_SIZE = 132;

    private final Container container;
    private final ContainerData containerData;

    public CollectorScreenHandler(int syncId, Inventory inventory) {
        this(syncId, inventory, new SimpleContainer(CollectorBlockEntity.INVENTORY_SIZE), new SimpleContainerData(CollectorBlockEntity.SYNC_AMOUNT));
    }

    public CollectorScreenHandler(int syncId, @NotNull Inventory inventory, Container container, ContainerData containerData) {
        super(ModScreenHandlers.CHRONON_COLLECTOR, syncId);
        checkContainerSize(container, CollectorBlockEntity.INVENTORY_SIZE);
        this.container = container;
        inventory.startOpen(inventory.player);
        this.containerData = containerData;

        this.addSlot(new TypeLockedSlot(container, CollectorBlockEntity.PLATING_SLOT, 80, 52, PlatingItem.class));
        this.addSlot(new TypeLockedSlot(container, CollectorBlockEntity.STORAGE_SLOT, 152, 72, AcceleratorItem.class));

        addPlayerInventory(inventory, 162, 104);

        addDataSlots(containerData);
    }

    public boolean isCollecting() {
        return this.containerData.get(CollectorBlockEntity.PROGRESS_SYNC_ID) > 0;
    }

    public int getChrononAmount() {
        return this.containerData.get(CollectorBlockEntity.STORED_CHRONONS_SYNC_ID);
    }

    public int scaledBarSize() {
        int chronons = this.containerData.get(CollectorBlockEntity.STORED_CHRONONS_SYNC_ID);
        double a = chronons * (double)BAR_SIZE;
        a /= CollectorBlockEntity.MAX_STORAGE_SIZE;
        return (int) a;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int invSlot) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if(slot.hasItem()) {
            stack = slot.getItem().copy();
            if(invSlot < this.container.getContainerSize()) {
                if(!this.moveItemStackTo(slot.getItem(), this.container.getContainerSize(), this.slots.size(), true)) return ItemStack.EMPTY;
            } else if(!this.moveItemStackTo(slot.getItem(), 0, this.container.getContainerSize(), false)) return ItemStack.EMPTY;
            if(slot.getItem().isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return stack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

}
