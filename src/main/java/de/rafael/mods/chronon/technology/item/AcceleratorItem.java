package de.rafael.mods.chronon.technology.item;

import de.rafael.mods.chronon.technology.item.abstracted.ChrononStorageItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class AcceleratorItem extends ChrononStorageItem {

    public AcceleratorItem() {
        super(1728000, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

}
