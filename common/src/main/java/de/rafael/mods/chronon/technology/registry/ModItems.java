package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.item.AcceleratorItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 06/08/2023
 */

public class ModItems {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ChrononTech.MOD_ID, Registries.ITEM);

    /* Items */
    public static final RegistrySupplier<Item> CHRONON_CORE = ITEMS.register("chronon_core", () ->
            new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> CHRONON_ACCELERATOR = ITEMS.register("chronon_accelerator", AcceleratorItem::new);

    /* Block Items */
    public static final RegistrySupplier<Item> CHRONON_COLLECTOR = ITEMS.register("chronon_collector", () ->
            new BlockItem(ModBlocks.CHRONON_COLLECTOR.get(), new Item.Properties().rarity(Rarity.RARE)));

    @Contract("_ -> new")
    public static @NotNull ResourceLocation createLocation(String resource) {
        return new ResourceLocation(ChrononTech.MOD_ID, resource);
    }

    public static void register() {
        ChrononTech.LOGGER.info("Registering items...");
        ITEMS.register();
    }

}
