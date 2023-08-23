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
