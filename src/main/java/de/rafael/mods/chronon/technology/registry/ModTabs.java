package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
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
            .icon(() -> new ItemStack(ModItems.CHRONON_ACCELERATOR))
            .displayItems((parameters, output) -> {
                output.accept(ModItems.CHRONON_CORE);
                output.accept(ModItems.CHRONON_ACCELERATOR);
                output.accept(ModBlocks.CHRONON_COLLECTOR);

                output.accept(ModItems.IRON_PLATTING);
                output.accept(ModItems.GOLD_PLATTING);
                output.accept(ModItems.DIAMOND_PLATTING);
                output.accept(ModItems.NETHERITE_PLATTING);
            }).build());

    private static @NotNull CreativeModeTab registerTab(String id, CreativeModeTab tab) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(ChrononTech.MOD_ID, id), tab);
    }

    public static void register() {
        ChrononTech.LOGGER.info("[REGISTRY] Adding creative tabs");
    }

}
