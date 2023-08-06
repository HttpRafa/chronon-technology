package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

/**
 * @author Rafael K.
 * @since 06/08/2023
 */

public class ModTabs {

    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(ChrononTech.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> MOD_TAB = TABS.register(
            "chronon_technology",
            () -> CreativeTabRegistry.create(builder -> {
                builder.title(Component.translatable("category.chronon_technology"));
                builder.icon(() -> new ItemStack(ModItems.CHRONON_CORE.get()));
                builder.displayItems((itemDisplayParameters, output) -> {
                    output.accept(ModItems.CHRONON_CORE.get());
                    output.accept(ModItems.CHRONON_ACCELERATOR.get());
                    output.accept(ModItems.CHRONON_COLLECTOR.get());
                });
            })
    );

    public static void register() {
        ChrononTech.LOGGER.info("Registering creative tabs...");
        TABS.register();
    }

}
