package de.rafael.mods.chronon.technology;

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

    @Override
    public void onInitialize() {
        ModBlocks.register();
        ModItems.register();
        ModTabs.register();

        ModBlockEntities.register();
        ModEntities.register();

        ModScreenHandlers.register();
    }

}