package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.item.AcceleratorItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class ModTabs {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ChrononTech.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MOD_TAB = TABS.register("chronon_technology",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("category.chronon_technology"))
                    .icon(() -> new ItemStack(ModItems.CHRONON_CORE.get()))
                    .displayItems((parameters, output) -> {
                        // Blocks
                        output.accept(ModBlocks.CHRONON_COLLECTOR.get());

                        // Primary Items
                        output.accept(ModItems.CHRONON_ACCELERATOR.get());
                        output.accept(AcceleratorItem.fullyChargedStack());

                        // Ingredients
                        output.accept(ModItems.CHRONON_CORE.get());

                        // Plating
                        output.accept(ModItems.IRON_PLATING.get());
                        output.accept(ModItems.GOLD_PLATING.get());
                        output.accept(ModItems.DIAMOND_PLATING.get());
                        output.accept(ModItems.NETHERITE_PLATING.get());
                        output.accept(ModItems.DEBUG_PLATING.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        ChrononTech.LOGGER.debug("[REGISTRY] Adding creative tabs");
        TABS.register(eventBus);
    }

}
