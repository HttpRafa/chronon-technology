package de.rafael.mods.chronon.technology.client;

import de.rafael.mods.chronon.technology.client.registry.ModEntityRenderers;
import net.fabricmc.api.ClientModInitializer;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class ChrononTechClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModEntityRenderers.register();
    }

}
