package de.rafael.mods.chronon.technology.item;

import de.rafael.mods.chronon.technology.config.AcceleratorConfig;
import de.rafael.mods.chronon.technology.item.abstracted.ChrononStorageItem;
import de.rafael.mods.chronon.technology.registry.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
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

}
