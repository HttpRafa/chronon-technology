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
package de.rafael.mods.chronon.technology.item.abstracted;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.attribute.AttributeHolder;
import de.rafael.mods.chronon.technology.util.helper.TimeHelper;
import de.rafael.mods.chronon.technology.util.values.Constants;
import de.rafael.mods.chronon.technology.util.values.NbtKey;
import java.util.List;
import lombok.Getter;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

@Getter
public class ChrononStorageItem extends ItemWithDescription implements AttributeHolder {

    private final long maxStorageSize;

    public ChrononStorageItem(long maxStorageSize, Properties properties) {
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
        return ChrononTech.CONFIG.get().gui.chrononColor;
    }

    @Override
    public int getBarWidth(ItemStack itemStack) {
        return Math.round(13f - 13f * (1 - (getChronons(itemStack) / (float)maxStorageSize)));
    }

    public long getChronons(@NotNull ItemStack itemStack) {
        return itemStack.getOrCreateTag().getLong(NbtKey.STORED_CHRONONS.getKey());
    }

    public void setChronons(@NotNull ItemStack itemStack, long amount) {
        itemStack.getOrCreateTag().putLong(NbtKey.STORED_CHRONONS.getKey(), amount);
    }

    public void removeChronons(ItemStack itemStack, long amount) {
        setChronons(itemStack, Math.max(0, getChronons(itemStack) - amount));
    }

    public void addChronons(ItemStack itemStack, long amount) {
        setChronons(itemStack, Math.min(this.maxStorageSize, getChronons(itemStack) + amount));
    }

    public long getSpaceLeft(ItemStack storageStack, long maxAmount) {
        return Math.min(maxAmount, this.maxStorageSize - getChronons(storageStack));
    }

    @Override
    public List<NbtKey> getNbtKeys() {
        return List.of(NbtKey.STORED_CHRONONS);
    }

}
