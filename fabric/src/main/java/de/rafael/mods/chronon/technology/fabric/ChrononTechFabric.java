package de.rafael.mods.chronon.technology.fabric;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.client.render.AcceleratorEntityRenderer;
import de.rafael.mods.chronon.technology.registry.ModEntities;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */
public class ChrononTechFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ChrononTech.init();
    }

}
