package de.rafael.mods.chronon.technology.block.entity;

import de.rafael.mods.chronon.technology.block.base.entity.BaseMachineBlockEntity;
import de.rafael.mods.chronon.technology.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

public class CollectorBlockEntity extends BaseMachineBlockEntity {

    public CollectorBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.CHRONON_COLLECTOR, blockPos, blockState);
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState) {

    }

}
