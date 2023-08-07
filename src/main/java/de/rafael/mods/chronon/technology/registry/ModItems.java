package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.item.AcceleratorItem;
import de.rafael.mods.chronon.technology.item.abstracted.ChrononStorageItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class ModItems {

    public static final Item CHRONON_CORE = registerItem("chronon_core", new ChrononStorageItem(1728000, new Item.Properties()));
    public static final Item CHRONON_ACCELERATOR = registerItem("chronon_accelerator", new AcceleratorItem());

    private static @NotNull Item registerItem(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(ChrononTech.MOD_ID, id), item);
    }

    public static void register() {
        ChrononTech.LOGGER.info("[REGISTRY] Adding items");
    }

}
