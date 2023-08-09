package de.rafael.mods.chronon.technology.block.base.entity;

import de.rafael.mods.chronon.technology.block.base.interfaces.Tickable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

public abstract class BaseMachineBlockEntity extends BlockEntity implements Tickable, WorldlyContainer, MenuProvider {

    protected final NonNullList<ItemStack> inventory;
    protected final Component displayName;

    public BaseMachineBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState, int inventorySize, Component displayName) {
        super(blockEntityType, blockPos, blockState);
        this.inventory = NonNullList.withSize(inventorySize, ItemStack.EMPTY);
        this.displayName = displayName;
    }

    public void markDirty() {}

    @Override
    public @NotNull Component getDisplayName() {
        return this.displayName;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemStack : this.inventory) {
            if (!itemStack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(this.inventory, slot);
    }

    @Override
    public @NotNull ItemStack removeItem(int slot, int count) {
        ItemStack itemStack = ContainerHelper.removeItem(this.inventory, slot, count);
        if(!itemStack.isEmpty()) {
            markDirty();
        }
        return itemStack;
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        this.inventory.set(i, itemStack);
        if(itemStack.getCount() > getMaxStackSize()) {
            itemStack.setCount(getMaxStackSize());
        }
    }

    @Override
    public @NotNull ItemStack getItem(int i) {
        return this.inventory.get(i);
    }

    @Override
    public int getContainerSize() {
        return this.inventory.size();
    }

    @Override
    public void clearContent() {
        this.inventory.clear();
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

}
