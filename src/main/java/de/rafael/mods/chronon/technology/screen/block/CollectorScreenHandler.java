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
    private final CollectorBlockEntity blockEntity;
    private final ContainerData containerData;

    public CollectorScreenHandler(int syncId, Inventory inventory, @NotNull FriendlyByteBuf extraData) {
        this(syncId, inventory, inventory.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(CollectorBlockEntity.SYNC_AMOUNT));
    }

    public CollectorScreenHandler(int syncId, @NotNull Inventory inventory, BlockEntity blockEntity, ContainerData containerData) {
        super(ModScreenHandlers.CHRONON_COLLECTOR.get(), syncId);
        checkContainerSize(inventory, CollectorBlockEntity.INVENTORY_SIZE);
        this.blockEntity = ((CollectorBlockEntity) blockEntity);
        this.level = inventory.player.level();
        inventory.startOpen(inventory.player);
        this.containerData = containerData;

        addPlayerInventory(inventory, 162, 104);
        addDataSlots(containerData);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new TypeLockedSlot(handler, CollectorBlockEntity.PLATING_SLOT, 80, 52, PlatingItem.class));
            this.addSlot(new TypeLockedSlot(handler, CollectorBlockEntity.STORAGE_SLOT, 152, 72, AcceleratorItem.class));
        });
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
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int invSlot) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if(slot.hasItem()) {
            stack = slot.getItem().copy();
            if(invSlot < this.blockEntity.getInventory().getSlots()) {
                if(!this.moveItemStackTo(slot.getItem(), this.blockEntity.getInventory().getSlots(), this.slots.size(), true)) return ItemStack.EMPTY;
            } else if(!this.moveItemStackTo(slot.getItem(), 0, this.blockEntity.getInventory().getSlots(), false)) return ItemStack.EMPTY;
            if(slot.getItem().isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return stack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.CHRONON_COLLECTOR.get());
    }

}
