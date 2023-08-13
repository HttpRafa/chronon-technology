package de.rafael.mods.chronon.technology.client.registry;

import de.rafael.mods.chronon.technology.client.render.entity.AcceleratorEntityRenderer;
import de.rafael.mods.chronon.technology.registry.ModEntities;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class ModEntityRenderers {

    public static void register() {
        EntityRendererRegistry.register(ModEntities.ACCELERATOR, AcceleratorEntityRenderer::new);
    }

}
