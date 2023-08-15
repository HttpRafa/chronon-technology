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
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChrononTech.MOD_ID);

    // Core
    public static final RegistryObject<Item> CHRONON_CORE = ITEMS.register("chronon_core",
            () -> new ItemWithDescription(new Item.Properties().stacksTo(1), Constants.Components.CORE_DESCRIPTION));
    public static final RegistryObject<Item> CHRONON_ACCELERATOR = ITEMS.register("chronon_accelerator", AcceleratorItem::new);

    // Plating
    public static final RegistryObject<Item> IRON_PLATING = ITEMS.register("iron_plating",
            () -> new PlatingItem(PlatingType.IRON));
    public static final RegistryObject<Item> GOLD_PLATING = ITEMS.register("gold_plating",
            () -> new PlatingItem(PlatingType.GOLD));
    public static final RegistryObject<Item> DIAMOND_PLATING = ITEMS.register("diamond_plating",
            () -> new PlatingItem(PlatingType.DIAMOND));
    public static final RegistryObject<Item> NETHERITE_PLATING = ITEMS.register("netherite_plating",
            () -> new PlatingItem(PlatingType.NETHERITE));

    public static final RegistryObject<Item> DEBUG_PLATING = ITEMS.register("debug_plating",
            () -> new PlatingItem(PlatingType.DEBUG));

    public static void register(IEventBus eventBus) {
        ChrononTech.LOGGER.debug("[REGISTRY] Adding items");
        ITEMS.register(eventBus);
    }

}
