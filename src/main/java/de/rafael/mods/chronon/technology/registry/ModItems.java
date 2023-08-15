package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.item.AcceleratorItem;
import de.rafael.mods.chronon.technology.item.PlatingItem;
import de.rafael.mods.chronon.technology.item.abstracted.ItemWithDescription;
import de.rafael.mods.chronon.technology.types.PlatingType;
import de.rafael.mods.chronon.technology.util.values.Constants;
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
            new ItemWithDescription(new Item.Properties().stacksTo(1), Constants.Components.CORE_DESCRIPTION));
    public static final Item CHRONON_ACCELERATOR = registerItem("chronon_accelerator",
            new AcceleratorItem());

    /* Plating */
    public static final Item IRON_PLATING = registerItem("iron_plating",
            new PlatingItem(PlatingType.IRON));
    public static final Item GOLD_PLATING = registerItem("gold_plating",
            new PlatingItem(PlatingType.GOLD));
    public static final Item DIAMOND_PLATING = registerItem("diamond_plating",
            new PlatingItem(PlatingType.DIAMOND));
    public static final Item NETHERITE_PLATING = registerItem("netherite_plating",
            new PlatingItem(PlatingType.NETHERITE));

    public static final Item DEBUG_PLATING = registerItem("debug_plating",
            new PlatingItem(PlatingType.DEBUG));

    private static @NotNull Item registerItem(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(ChrononTech.MOD_ID, id), item);
    }

    public static void register() {
        ChrononTech.LOGGER.info("[REGISTRY] Adding items");
    }

}
