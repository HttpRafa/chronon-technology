package de.rafael.mods.chronon.technology;

import de.rafael.mods.chronon.technology.registry.ModBlocks;
import de.rafael.mods.chronon.technology.registry.ModEntities;
import de.rafael.mods.chronon.technology.registry.ModItems;
import de.rafael.mods.chronon.technology.registry.ModTabs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChrononTech {

    public static final String MOD_ID = "chronontech";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        ModBlocks.register();
        ModItems.register();
        ModTabs.register();
        ModEntities.register();
    }

}
