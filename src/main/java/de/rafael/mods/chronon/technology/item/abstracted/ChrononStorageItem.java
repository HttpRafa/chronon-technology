package de.rafael.mods.chronon.technology.item.abstracted;

import de.rafael.mods.chronon.technology.config.GuiConfig;
import de.rafael.mods.chronon.technology.values.NbtKeys;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class ChrononStorageItem extends Item {

    private final int maxStorageSize;

    public ChrononStorageItem(int maxStorageSize, Properties properties) {
        super(properties);
        this.maxStorageSize = maxStorageSize;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, tooltip, tooltipFlag);
        tooltip.add(Component.translatable("tooltip.storage.chronons").withStyle(ChatFormatting.GREEN)
                .append(Component.literal(String.valueOf(getChronons(itemStack))).withStyle(ChatFormatting.GRAY)));
    }

    @Override
    public boolean isBarVisible(ItemStack itemStack) {
        return true;
    }

    @Override
    public int getBarColor(ItemStack itemStack) {
        return GuiConfig.chrononColor;
    }

    @Override
    public int getBarWidth(ItemStack itemStack) {
        return Math.round(13f - 13f * (1 - (getChronons(itemStack) / (float)maxStorageSize)));
    }

    public int getChronons(@NotNull ItemStack itemStack) {
        return itemStack.getOrCreateTag().getInt(NbtKeys.STORED_CHRONONS.getKey());
    }

    public void setChronons(@NotNull ItemStack itemStack, int amount) {
        itemStack.getOrCreateTag().putInt(NbtKeys.STORED_CHRONONS.getKey(), amount);
    }

    public void removeChronons(ItemStack itemStack, int amount) {
        setChronons(itemStack, Math.max(0, getChronons(itemStack) - amount));
    }

    public void addChronons(ItemStack itemStack, int amount) {
        setChronons(itemStack, Math.min(this.maxStorageSize, getChronons(itemStack) + amount));
    }

}
