/*
 * MIT License
 *
 * Copyright (c) 2023 Rafael
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package de.rafael.mods.chronon.technology.block;

import de.rafael.mods.chronon.technology.block.base.BaseMachineBlock;
import de.rafael.mods.chronon.technology.block.entity.CollectorBlockEntity;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

public class CollectorBlock extends BaseMachineBlock {

    public static final List<BlockPos> OFFSETS = BlockPos.betweenClosedStream(-2, 0, -2, 2, 1, 2).filter((blockPos) -> Math.abs(blockPos.getX()) == 2 || Math.abs(blockPos.getZ()) == 2).map(BlockPos::immutable).toList();
    public static final int PARTICLE_POSSIBILITY = 128;

    public CollectorBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion());
    }

    @Override
    public @NotNull InteractionResult use(BlockState blockState, @NotNull Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(!level.isClientSide()) {
            MenuProvider menuProvider = (CollectorBlockEntity)level.getBlockEntity(blockPos);
            if(menuProvider != null) {
                player.openMenu(menuProvider);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        super.animateTick(blockState, level, blockPos, randomSource);

        if(level.getBlockEntity(blockPos) instanceof CollectorBlockEntity entity && entity.isCollecting()) {
            OFFSETS.forEach(sourcePos -> {
                if(randomSource.nextInt(PARTICLE_POSSIBILITY) == 0) level.addParticle(ParticleTypes.PORTAL, (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1f, (double)blockPos.getZ() + 0.5, (double)((float)sourcePos.getX() + randomSource.nextFloat()) - 0.5, (float)sourcePos.getY() - randomSource.nextFloat() + 0.85F, (double)((float)sourcePos.getZ() + randomSource.nextFloat()) - 0.5);
            });
        }
    }

    /* Block entity */
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CollectorBlockEntity(blockPos, blockState);
    }

}
