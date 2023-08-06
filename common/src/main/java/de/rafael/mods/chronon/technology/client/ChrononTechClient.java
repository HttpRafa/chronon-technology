package de.rafael.mods.chronon.technology.client;

import de.rafael.mods.chronon.technology.client.render.AcceleratorEntityRenderer;
import de.rafael.mods.chronon.technology.registry.ModEntities;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;

/**
 * @author Rafael K.
 * @since 06/08/2023
 */

public class ChrononTechClient {

    public static void init() {
        EntityRendererRegistry.register(ModEntities.ACCELERATOR, AcceleratorEntityRenderer::new);
    }

}
