package de.rafael.mods.chronon.technology.client.fabric;

import de.rafael.mods.chronon.technology.client.ChrononTechClient;
import de.rafael.mods.chronon.technology.client.render.AcceleratorEntityRenderer;
import de.rafael.mods.chronon.technology.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

/**
 * @author Rafael K.
 * @since 06/08/2023
 */

public class ChrononTechFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.ACCELERATOR.get(), AcceleratorEntityRenderer::new);

        ChrononTechClient.init();
    }

}
