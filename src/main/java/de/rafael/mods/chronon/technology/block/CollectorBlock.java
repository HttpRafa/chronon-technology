package de.rafael.mods.chronon.technology.block;

import de.rafael.mods.chronon.technology.block.base.BaseMachineBlock;
import de.rafael.mods.chronon.technology.block.entity.CollectorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.apache.logging.log4j.core.jmx.Server;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

public class CollectorBlock extends BaseMachineBlock {

    public CollectorBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion());
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        if(!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if(blockEntity instanceof CollectorBlockEntity entity) {
                NetworkHooks.openScreen(((ServerPlayer) player), entity, blockPos);
            } else {
                throw new IllegalStateException("Container provider is missing");
            }
        }
        return InteractionResult.SUCCESS;
    }

    // Block entity
    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new CollectorBlockEntity(blockPos, blockState);
    }

}
