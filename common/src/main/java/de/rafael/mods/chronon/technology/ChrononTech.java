package de.rafael.mods.chronon.technology;

import com.teamresourceful.resourcefulconfig.common.config.Configurator;
import de.rafael.mods.chronon.technology.config.ChrononTechConfig;
import de.rafael.mods.chronon.technology.registry.ModBlocks;
import de.rafael.mods.chronon.technology.registry.ModEntities;
import de.rafael.mods.chronon.technology.registry.ModItems;
import de.rafael.mods.chronon.technology.registry.ModTabs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChrononTech {

    public static final String MOD_ID = "chronontech";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Configurator CONFIGURATOR = new Configurator();

    public static void init() {
        CONFIGURATOR.registerConfig(ChrononTechConfig.class);

        ModBlocks.register();
        ModItems.register();
        ModTabs.register();
        ModEntities.register();
    }

}
