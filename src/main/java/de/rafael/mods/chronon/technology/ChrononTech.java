package de.rafael.mods.chronon.technology;

import com.teamresourceful.resourcefulconfig.common.config.Configurator;
import de.rafael.mods.chronon.technology.config.ChrononTechConfig;
import de.rafael.mods.chronon.technology.registry.*;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class ChrononTech implements ModInitializer {

    public static final String MOD_ID = "chronontech";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Configurator CONFIGURATOR = new Configurator();

    @Override
    public void onInitialize() {
        CONFIGURATOR.registerConfig(ChrononTechConfig.class);

        ModBlocks.register();
        ModItems.register();
        ModTabs.register();

        ModBlockEntities.register();
        ModEntities.register();

        ModScreenHandlers.register();
    }

}
