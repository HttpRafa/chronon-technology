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
import de.rafael.mods.chronon.technology.item.abstracted.ChrononStorageItem;
import de.rafael.mods.chronon.technology.registry.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class TimeChangerItem extends ChrononStorageItem {

    public TimeChangerItem() {
        super(AcceleratorConfig.storageSize * 2, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    public static @NotNull ItemStack fullyChargedStack() {
        TimeChangerItem item = ((TimeChangerItem) ModItems.TIME_CHANGER);
        ItemStack itemStack = new ItemStack(item);
        item.setChronons(itemStack, item.getMaxStorageSize());
        return itemStack;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, InteractionHand interactionHand) {
        if(!level.isClientSide()) return super.use(level, player, interactionHand);
        player.sendSystemMessage(Component.literal("Client text"));
        return super.use(level, player, interactionHand);
    }

}
