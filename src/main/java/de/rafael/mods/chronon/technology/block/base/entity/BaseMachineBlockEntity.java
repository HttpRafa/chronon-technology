package de.rafael.mods.chronon.technology.block.base.entity;

import de.rafael.mods.chronon.technology.block.base.interfaces.Tickable;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

@Getter
@Setter
public abstract class BaseMachineBlockEntity extends BlockEntity implements Tickable, MenuProvider {

    protected final ItemStackHandler inventory;
    protected final Component displayName;

    protected LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public BaseMachineBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState, int inventorySize, Component displayName) {
        super(blockEntityType, blockPos, blockState);
        this.inventory = new ItemStackHandler(inventorySize) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
        this.displayName = displayName;
    }

    public void markDirty() {}

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", this.inventory.serializeNBT());
        super.saveAdditional(tag);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        this.inventory.deserializeNBT(tag.getCompound("inventory"));
    }

    @Override
    public @NotNull Component getDisplayName() {
        return this.displayName;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability) {
        if(capability == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(capability);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> inventory);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public Container getDropsContainer() {
        SimpleContainer simpleContainer = new SimpleContainer(this.inventory.getSlots());
        for (int i = 0; i < this.inventory.getSlots(); i++) {
            simpleContainer.setItem(i, this.inventory.getStackInSlot(i));
        }
        return simpleContainer;
    }

}
