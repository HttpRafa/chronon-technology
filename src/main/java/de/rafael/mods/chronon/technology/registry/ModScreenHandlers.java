package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.screen.block.CollectorScreenHandler;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

/**
 * @author Rafael K.
 * @since 09/08/2023
 */

public class ModScreenHandlers {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ChrononTech.MOD_ID);


    public static final RegistryObject<MenuType<CollectorScreenHandler>> CHRONON_COLLECTOR =
            registerMenuType("chronon_collector", CollectorScreenHandler::new);

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String id, IContainerFactory<T> factory) {
        return MENUS.register(id, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        ChrononTech.LOGGER.debug("[REGISTRY] Adding screen handlers");
        MENUS.register(eventBus);
    }

}
