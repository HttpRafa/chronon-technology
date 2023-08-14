package de.rafael.mods.chronon.technology.item;

import de.rafael.mods.chronon.technology.config.AcceleratorConfig;
import de.rafael.mods.chronon.technology.entity.AcceleratorEntity;
import de.rafael.mods.chronon.technology.item.abstracted.ChrononStorageItem;
import de.rafael.mods.chronon.technology.utils.values.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
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
        super(ChrononStorageItem.CORE_MAX_STORAGE_SIZE * 2, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext useOnContext) {
        Player player = useOnContext.getPlayer();
        assert player != null;
        Level level = useOnContext.getLevel();
        if(level.isClientSide()) return InteractionResult.PASS;
        BlockPos blockPos = useOnContext.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        if(!blockState.isRandomlyTicking() && level.getBlockEntity(blockPos) == null) return InteractionResult.FAIL;

        // Check if accelerator already exists
        level.getEntitiesOfClass(AcceleratorEntity.class, new AABB(blockPos)).stream().findFirst().ifPresentOrElse(accelerator -> {
            int rate = accelerator.getTickRate();
            int ticks = AcceleratorConfig.boostTime - accelerator.getTicksLeft();

            // Check if rate is at maximum
            if(rate >= Math.pow(2, AcceleratorConfig.maxTickRate)) return;

            rate = rate * 2; // Increment current rate
            ticks = ticks / 2; // Add additional ticks to the old time
            int cost = player.isCreative() ? 0 : (rate / 2) * ((accelerator.getTicksLeft() + ticks) / 20);

            accelerator.setTickRate(rate);
            accelerator.setTicksLeft(accelerator.getTicksLeft() + ticks);

            removeChronons(useOnContext.getItemInHand(), cost);
            playSound(level, blockPos, rate);
        }, () -> {
            int cost = player.isCreative() ? 0 : (AcceleratorConfig.boostTime / 20);
            if(cost > getChronons(useOnContext.getItemInHand())) return;

            AcceleratorEntity accelerator = new AcceleratorEntity(level, blockPos);
            accelerator.setTicksLeft(AcceleratorConfig.boostTime);
            accelerator.setTickRate(2);
            level.addFreshEntity(accelerator);

            removeChronons(useOnContext.getItemInHand(), cost);
            playSound(level, blockPos, 2);
        });

        return InteractionResult.SUCCESS;
    }

    private void playSound(@NotNull Level level, BlockPos blockPos, int rate) {
        level.playSound(null, blockPos, SoundEvents.NOTE_BLOCK_PLING.value(), SoundSource.BLOCKS, 0.5f, Constants.Sounds.SOUND_SUPPLIER.apply(rate));
    }

}
