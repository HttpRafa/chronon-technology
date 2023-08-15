package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.item.AcceleratorItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class ModTabs {

    public static final CreativeModeTab MOD_TAB = registerTab("chronon_technology", FabricItemGroup.builder()
            .title(Component.translatable("category.chronon_technology"))
            .icon(() -> new ItemStack(ModItems.CHRONON_CORE))
            .displayItems((parameters, output) -> {
                /* Blocks */
                output.accept(ModBlocks.CHRONON_COLLECTOR);

                /* Primary Items */
                output.accept(ModItems.CHRONON_ACCELERATOR);
                output.accept(AcceleratorItem.fullyChargedStack());

                /* Ingredients */
                output.accept(ModItems.CHRONON_CORE);

                /* Plating */
                output.accept(ModItems.IRON_PLATING);
                output.accept(ModItems.GOLD_PLATING);
                output.accept(ModItems.DIAMOND_PLATING);
                output.accept(ModItems.NETHERITE_PLATING);
                output.accept(ModItems.DEBUG_PLATING);
            }).build());

    private static @NotNull CreativeModeTab registerTab(String id, CreativeModeTab tab) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(ChrononTech.MOD_ID, id), tab);
    }

    public static void register() {
        ChrononTech.LOGGER.info("[REGISTRY] Adding creative tabs");
    }

}
