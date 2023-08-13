package de.rafael.mods.chronon.technology.screen.block.base;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.Nullable;

/**
 * @author Rafael K.
 * @since 09/08/2023
 */

public abstract class BaseContainerMenu extends AbstractContainerMenu {

    protected BaseContainerMenu(@Nullable MenuType<?> menuType, int syncId) {
        super(menuType, syncId);
    }

    protected void addPlayerInventory(Inventory inventory, int hotbarY, int inventoryY) {
        // Normal inventory
        for(int j = 0; j < 3; ++j) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(inventory, k + j * 9 + 9, 8 + k * 18, inventoryY + j * 18));
            }
        }
        // Hotbar
        for(int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(inventory, j, 8 + j * 18, hotbarY));
        }
    }

}
