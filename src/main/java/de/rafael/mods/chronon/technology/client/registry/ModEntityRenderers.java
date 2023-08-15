package de.rafael.mods.chronon.technology.client.registry;

import de.rafael.mods.chronon.technology.client.render.entity.AcceleratorEntityRenderer;
import de.rafael.mods.chronon.technology.registry.ModEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class ModEntityRenderers {

    public static void register() {
        EntityRenderers.register(ModEntities.ACCELERATOR.get(), AcceleratorEntityRenderer::new);
    }

}
