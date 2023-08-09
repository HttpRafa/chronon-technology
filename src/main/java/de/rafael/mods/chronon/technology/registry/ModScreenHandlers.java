package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.screen.block.CollectorScreenHandler;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

/**
 * @author Rafael K.
 * @since 09/08/2023
 */

public class ModScreenHandlers {

    public static final MenuType<CollectorScreenHandler> CHRONON_COLLECTOR = new MenuType<>(CollectorScreenHandler::new, FeatureFlags.VANILLA_SET);

    public static void register() {
        ChrononTech.LOGGER.info("[REGISTRY] Adding screen handlers");
    }

}
