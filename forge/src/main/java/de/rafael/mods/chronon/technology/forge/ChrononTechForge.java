package de.rafael.mods.chronon.technology.forge;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.client.ChrononTechClient;
import de.rafael.mods.chronon.technology.client.render.AcceleratorEntityRenderer;
import de.rafael.mods.chronon.technology.registry.ModEntities;
import dev.architectury.platform.forge.EventBuses;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

@Mod(ChrononTech.MOD_ID)
public class ChrononTechForge {

    public ChrononTechForge() {
        EventBuses.registerModEventBus(ChrononTech.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        ChrononTech.init();

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(ChrononTechForge::onClientSetup);
    }

    private static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.ACCELERATOR.get(), AcceleratorEntityRenderer::new);

        ChrononTechClient.init();
    }

}
