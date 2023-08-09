package de.rafael.mods.chronon.technology.block.entity;

import de.rafael.mods.chronon.technology.block.base.entity.BaseMachineBlockEntity;
import de.rafael.mods.chronon.technology.registry.ModBlockEntities;
import de.rafael.mods.chronon.technology.screen.block.CollectorScreenHandler;
import de.rafael.mods.chronon.technology.utils.helper.CompactContainerData;
import de.rafael.mods.chronon.technology.utils.values.NbtKeys;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

@Getter
public class CollectorBlockEntity extends BaseMachineBlockEntity {

    public static final int INVENTORY_SIZE = 2;
    public static final int SYNC_AMOUNT = 2;

    private final ContainerData containerData;

    private int storedChronons = 0;
    private int progress = 0;

    public CollectorBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.CHRONON_COLLECTOR, blockPos, blockState,
                INVENTORY_SIZE, Component.translatable("screen.chronontech.collector"));
        this.containerData = new CompactContainerData(SYNC_AMOUNT) {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> progress;
                    case 1 -> storedChronons;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0 -> progress = value;
                    case 1 -> storedChronons = value;
                }
            }
        };
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        // TODO: Do collector magic
    }

    @Override
    public int @NotNull [] getSlotsForFace(Direction direction) {
        return new int[] {0, 1}; // TODO: Do hopper magic
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack itemStack, @Nullable Direction direction) {
        return true; // TODO: Do hopper magic
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack itemStack, Direction direction) {
        return true; // TODO: Do hopper magic
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory inventory, Player player) {
        return new CollectorScreenHandler(syncId, inventory, this, this.containerData);
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        ContainerHelper.saveAllItems(compoundTag, this.inventory);
        compoundTag.putInt(NbtKeys.STORED_CHRONONS.getKey(), storedChronons);
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        ContainerHelper.loadAllItems(compoundTag, this.inventory);
        this.storedChronons = compoundTag.getInt(NbtKeys.STORED_CHRONONS.getKey());
    }

}
