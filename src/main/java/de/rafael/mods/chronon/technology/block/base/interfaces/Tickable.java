package de.rafael.mods.chronon.technology.block.base.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

public interface Tickable {

    void tick(Level level, BlockPos blockPos, BlockState blockState);

}
