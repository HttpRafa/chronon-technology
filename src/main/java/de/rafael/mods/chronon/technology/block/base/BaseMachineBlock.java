package de.rafael.mods.chronon.technology.block.base;

import de.rafael.mods.chronon.technology.block.base.entity.BaseMachineBlockEntity;
import de.rafael.mods.chronon.technology.block.base.interfaces.Tickable;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

public abstract class BaseMachineBlock extends BaseEntityBlock {

    protected BaseMachineBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onRemove(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState2, boolean bl) {
        if(!blockState.is(blockState2.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof BaseMachineBlockEntity entity) {
                if (level instanceof ServerLevel) {
                    Containers.dropContents(level, blockPos, entity.getDropsContainer());
                }

                level.updateNeighbourForOutputSignal(blockPos, this);
            }
            super.onRemove(blockState, level, blockPos, blockState2, bl);
        }
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState blockState, @NotNull BlockEntityType<T> blockEntityType) {
        return (tickLevel, tickPos, tickState, tickEntity) -> ((Tickable)tickEntity).tick(tickLevel, tickPos, tickState);
    }

}
