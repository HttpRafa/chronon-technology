package de.rafael.mods.chronon.technology.item.abstracted;

import de.rafael.mods.chronon.technology.config.GuiConfig;
import de.rafael.mods.chronon.technology.util.helper.TimeHelper;
import de.rafael.mods.chronon.technology.util.values.Constants;
import de.rafael.mods.chronon.technology.util.values.NbtKeys;
import lombok.Getter;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
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

@Getter
public class ChrononStorageItem extends ItemWithDescription {

    public static final int CORE_MAX_STORAGE_SIZE = 86400;

    private final int maxStorageSize;

    public ChrononStorageItem(int maxStorageSize, Properties properties) {
        super(properties, Constants.Components.STORAGE_DESCRIPTION);
        this.maxStorageSize = maxStorageSize;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, @NotNull List<Component> tooltip, TooltipFlag tooltipFlag) {
        tooltip.add(Component.translatable("tooltip.chronontech.storage.storedTime")
                .withStyle(ChatFormatting.AQUA).append(Component.literal(TimeHelper
                                .formatTime(TimeHelper
                                        .millisFromChronons(getChronons(itemStack))))
                        .withStyle(ChatFormatting.GRAY)));
        super.appendHoverText(itemStack, level, tooltip, tooltipFlag);
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

    public int getSpaceLeft(ItemStack storageStack, int maxAmount) {
        return Math.min(maxAmount, this.maxStorageSize - getChronons(storageStack));
    }

}
