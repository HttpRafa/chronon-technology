package de.rafael.mods.chronon.technology.client.registry;

import de.rafael.mods.chronon.technology.client.screen.block.CollectorScreen;
import de.rafael.mods.chronon.technology.registry.ModScreenHandlers;
import net.minecraft.client.gui.screens.MenuScreens;

/**
 * @author Rafael K.
 * @since 15/08/2023
 */

public class ModScreens {

    public static void register() {
        MenuScreens.register(ModScreenHandlers.CHRONON_COLLECTOR.get(), CollectorScreen::new);
    }

}
