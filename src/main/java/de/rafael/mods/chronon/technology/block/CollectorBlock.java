package de.rafael.mods.chronon.technology.block;

import de.rafael.mods.chronon.technology.block.base.BaseMachineBlock;
import de.rafael.mods.chronon.technology.block.base.entity.BaseMachineBlockEntity;
import de.rafael.mods.chronon.technology.block.entity.CollectorBlockEntity;
import de.rafael.mods.chronon.technology.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

public class CollectorBlock extends BaseMachineBlock {

    public CollectorBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK));
    }

    /* Block entity */
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CollectorBlockEntity(blockPos, blockState);
    }

}
