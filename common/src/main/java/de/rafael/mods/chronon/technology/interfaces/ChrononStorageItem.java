package de.rafael.mods.chronon.technology.interfaces;

import net.minecraft.world.item.ItemStack;

/**
 * @author Rafael K.
 * @since 06/08/2023
 */

public interface ChrononStorageItem {

    long chronons(ItemStack itemStack);
    void chronons(ItemStack itemStack, long amount);

    void removeChronons(ItemStack itemStack, long amount);

}
