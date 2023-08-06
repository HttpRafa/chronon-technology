package de.rafael.mods.chronon.technology.item;

import de.rafael.mods.chronon.technology.config.ConfigData;
import de.rafael.mods.chronon.technology.entity.AcceleratorEntity;
import de.rafael.mods.chronon.technology.interfaces.ChrononStorageItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Rafael K.
 * @since 06/08/2023
 */

public class AcceleratorItem extends Item implements ChrononStorageItem {

    public static final float[] SOUNDS = {0.707107f, 0.793701f, 0.943874f, 1.059463f, 1.189207f, 1.334840f, 1.414214f, 1.587401f, 1.781797f, 1.887749f};

    public static final String CHRONONS_KEY = "storedChronons";

    public AcceleratorItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.RARE));
    }

    @Override
    public InteractionResult useOn(@NotNull UseOnContext useOnContext) {
        var level = useOnContext.getLevel();
        if(level.isClientSide) {
            return InteractionResult.PASS;
        }
        var blockPos = useOnContext.getClickedPos();
        var blockState = level.getBlockState(blockPos);
        var blockEntity = level.getBlockEntity(blockPos);
        if(blockEntity == null && !blockState.isRandomlyTicking()) {
            return InteractionResult.FAIL;
        }

        var player = useOnContext.getPlayer();
        assert player != null;
        var itemStack = useOnContext.getItemInHand();

        var accelerators = level.getEntitiesOfClass(AcceleratorEntity.class, new AABB(blockPos)).stream().findFirst();

        if(accelerators.isPresent()) {
            var accelerator = accelerators.get();
            var currentRate = accelerator.tickRate();
            var usedTime = ConfigData.EACH_USE - accelerator.ticksLeft();

            if(currentRate >= Math.pow(2, ConfigData.MAX_TIME - 1)) {
                return InteractionResult.SUCCESS;
            }

            var newRate = currentRate * 2;
            var newTime = usedTime / 2;
            var energyRequired = calcCost(newRate);

            if(!player.isCreative() && chronons(itemStack) < energyRequired) {
                return InteractionResult.SUCCESS;
            }

            accelerator.tickRate(newRate);
            accelerator.extendLifetime(newTime);

            removeChronons(itemStack, energyRequired);
            playSound(level, blockPos, newRate);
        } else {
            if(!player.isCreative() && chronons(itemStack) < ConfigData.EACH_USE) {
                return InteractionResult.SUCCESS;
            }

            AcceleratorEntity accelerator = new AcceleratorEntity(level, blockPos);
            accelerator.ticksLeft(ConfigData.EACH_USE);
            level.addFreshEntity(accelerator);

            removeChronons(itemStack, ConfigData.EACH_USE);
            playSound(level, blockPos, 1);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, tooltip, tooltipFlag);
        tooltip.add(Component.literal("Chronons: " + chronons(itemStack)));
    }

    private long calcCost(int rate) {
        if(rate <= 1) return ConfigData.EACH_USE;
        return rate / 2 * ConfigData.EACH_USE;
    }

    private void playSound(Level level, BlockPos blockPos, int rate) {
        switch (rate) {
            case 1 -> level.playSound(null, blockPos, SoundEvents.NOTE_BLOCK_HARP.value(), SoundSource.BLOCKS, 0.5f, SOUNDS[0]);
            case 2 -> level.playSound(null, blockPos, SoundEvents.NOTE_BLOCK_HARP.value(), SoundSource.BLOCKS, 0.5f, SOUNDS[1]);
            case 4 -> level.playSound(null, blockPos, SoundEvents.NOTE_BLOCK_HARP.value(), SoundSource.BLOCKS, 0.5f, SOUNDS[2]);
            case 8 -> level.playSound(null, blockPos, SoundEvents.NOTE_BLOCK_HARP.value(), SoundSource.BLOCKS, 0.5f, SOUNDS[3]);
            case 16 -> level.playSound(null, blockPos, SoundEvents.NOTE_BLOCK_HARP.value(), SoundSource.BLOCKS, 0.5f, SOUNDS[4]);
            case 32 -> level.playSound(null, blockPos, SoundEvents.NOTE_BLOCK_HARP.value(), SoundSource.BLOCKS, 0.5f, SOUNDS[5]);
            case 64 -> level.playSound(null, blockPos, SoundEvents.NOTE_BLOCK_HARP.value(), SoundSource.BLOCKS, 0.5f, SOUNDS[6]);
            case 128 -> level.playSound(null, blockPos, SoundEvents.NOTE_BLOCK_HARP.value(), SoundSource.BLOCKS, 0.5f, SOUNDS[7]);
            case 256 -> level.playSound(null, blockPos, SoundEvents.NOTE_BLOCK_HARP.value(), SoundSource.BLOCKS, 0.5f, SOUNDS[8]);
            case 512 -> level.playSound(null, blockPos, SoundEvents.NOTE_BLOCK_HARP.value(), SoundSource.BLOCKS, 0.5f, SOUNDS[9]);
        }
    }

    @Override
    public long chronons(@NotNull ItemStack itemStack) {
        return itemStack.getOrCreateTag().getLong(CHRONONS_KEY);
    }

    @Override
    public void chronons(@NotNull ItemStack itemStack, long amount) {
        itemStack.getOrCreateTag().putLong(CHRONONS_KEY, amount);
    }

    @Override
    public void removeChronons(ItemStack itemStack, long amount) {
        long chronons = chronons(itemStack);
        chronons = Math.max(0, chronons - amount);
    }

}
