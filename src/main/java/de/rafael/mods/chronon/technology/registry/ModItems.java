package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.item.AcceleratorItem;
import de.rafael.mods.chronon.technology.item.PlattingItem;
import de.rafael.mods.chronon.technology.item.abstracted.ChrononStorageItem;
import de.rafael.mods.chronon.technology.types.PlattingType;
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

    /* Core */
    public static final Item CHRONON_CORE = registerItem("chronon_core",
            new ChrononStorageItem(ChrononStorageItem.CORE_MAX_STORAGE_SIZE, new Item.Properties()));
    public static final Item CHRONON_ACCELERATOR = registerItem("chronon_accelerator",
            new AcceleratorItem());

    /* Platting */
    public static final Item IRON_PLATTING = registerItem("iron_platting",
            new PlattingItem(PlattingType.IRON));
    public static final Item GOLD_PLATTING = registerItem("gold_platting",
            new PlattingItem(PlattingType.GOLD));
    public static final Item DIAMOND_PLATTING = registerItem("diamond_platting",
            new PlattingItem(PlattingType.DIAMOND));
    public static final Item NETHERITE_PLATTING = registerItem("netherite_platting",
            new PlattingItem(PlattingType.NETHERITE));

    private static @NotNull Item registerItem(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(ChrononTech.MOD_ID, id), item);
    }

    public static void register() {
        ChrononTech.LOGGER.info("[REGISTRY] Adding items");
    }

}
