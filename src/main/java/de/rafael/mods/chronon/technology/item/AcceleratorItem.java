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
package de.rafael.mods.chronon.technology.item;

import de.rafael.mods.chronon.technology.config.AcceleratorConfig;
import de.rafael.mods.chronon.technology.entity.AcceleratorEntity;
import de.rafael.mods.chronon.technology.item.abstracted.ChrononStorageItem;
import de.rafael.mods.chronon.technology.registry.ModItems;
import de.rafael.mods.chronon.technology.registry.ModTags;
import de.rafael.mods.chronon.technology.util.values.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class AcceleratorItem extends ChrononStorageItem {

    public AcceleratorItem() {
        super(AcceleratorConfig.storageSize * 2, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    public static @NotNull ItemStack fullyChargedStack() {
        AcceleratorItem item = ((AcceleratorItem)ModItems.CHRONON_ACCELERATOR.get());
        ItemStack itemStack = new ItemStack(item);
        item.setChronons(itemStack, item.getMaxStorageSize());
        return itemStack;
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext useOnContext) {
        Player player = useOnContext.getPlayer();
        assert player != null;
        Level level = useOnContext.getLevel();
        if(level.isClientSide()) return InteractionResult.PASS;
        BlockPos blockPos = useOnContext.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        if(blockState.is(ModTags.Blocks.ACCELERATION_BLACKLIST) || (!blockState.isRandomlyTicking() && level.getBlockEntity(blockPos) == null)) return InteractionResult.FAIL;

        // Check if accelerator already exists
        level.getEntitiesOfClass(AcceleratorEntity.class, new AABB(blockPos)).stream().findFirst().ifPresentOrElse(accelerator -> {
            int rate = accelerator.getTickRate();
            long ticks = AcceleratorConfig.boostTime - accelerator.getTicksLeft();

            // Check if rate is at maximum
            if(rate >= Math.pow(2, AcceleratorConfig.maxTickRate)) return;

            rate = rate * 2; // Increment current rate
            ticks = ticks / 2; // Add additional ticks to the old time
            long cost = player.isCreative() ? 0 : (rate / 2) * ((accelerator.getTicksLeft() + ticks) / 20L);
            if(cost > getChronons(useOnContext.getItemInHand())) { playMissingSound(player); return; }

            accelerator.setTickRate(rate);
            accelerator.setTicksLeft(accelerator.getTicksLeft() + ticks);

            removeChronons(useOnContext.getItemInHand(), cost);
            playSound(level, blockPos, rate);
        }, () -> {
            long cost = player.isCreative() ? 0 : (AcceleratorConfig.boostTime / 20L);
            if(cost > getChronons(useOnContext.getItemInHand())) { playMissingSound(player); return; }

            AcceleratorEntity accelerator = new AcceleratorEntity(level, blockPos);
            accelerator.setTicksLeft(AcceleratorConfig.boostTime);
            accelerator.setTickRate(2);
            level.addFreshEntity(accelerator);

            removeChronons(useOnContext.getItemInHand(), cost);
            playSound(level, blockPos, 2);
        });

        return InteractionResult.SUCCESS;
    }

    private void playMissingSound(@NotNull Player player) {
        player.playNotifySound(SoundEvents.CHEST_LOCKED, SoundSource.BLOCKS, 0.25f, 2f);
    }

    private void playSound(@NotNull Level level, BlockPos blockPos, int rate) {
        level.playSound(null, blockPos, SoundEvents.NOTE_BLOCK_PLING.value(), SoundSource.BLOCKS, 0.5f, Constants.Sounds.SOUND_SUPPLIER.apply(rate));
    }

}
