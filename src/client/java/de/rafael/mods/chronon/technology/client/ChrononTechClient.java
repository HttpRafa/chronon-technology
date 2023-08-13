package de.rafael.mods.chronon.technology.client;

import de.rafael.mods.chronon.technology.client.registry.ModEntityRenderers;
import de.rafael.mods.chronon.technology.client.screen.block.CollectorScreen;
import de.rafael.mods.chronon.technology.registry.ModBlocks;
import de.rafael.mods.chronon.technology.registry.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class ChrononTechClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModEntityRenderers.register();

        MenuScreens.register(ModScreenHandlers.CHRONON_COLLECTOR, CollectorScreen::new);
    }

}
