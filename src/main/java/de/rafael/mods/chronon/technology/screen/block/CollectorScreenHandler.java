package de.rafael.mods.chronon.technology.screen.block;

import de.rafael.mods.chronon.technology.block.entity.CollectorBlockEntity;
import de.rafael.mods.chronon.technology.registry.ModScreenHandlers;
import de.rafael.mods.chronon.technology.screen.block.base.BaseContainerMenu;
import lombok.Getter;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Rafael K.
 * @since 09/08/2023
 */

@Getter
public class CollectorScreenHandler extends BaseContainerMenu {

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

        // TODO: Add slots

        addPlayerInventory(inventory);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int invSlot) {
        throw new UnsupportedOperationException("Not implemented"); // TODO: Implement Shift click
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

}
