package de.rafael.mods.chronon.technology.block.base.entity;

import de.rafael.mods.chronon.technology.block.base.interfaces.Tickable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

public abstract class BaseMachineBlockEntity extends BlockEntity implements Tickable {

    public BaseMachineBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

}
