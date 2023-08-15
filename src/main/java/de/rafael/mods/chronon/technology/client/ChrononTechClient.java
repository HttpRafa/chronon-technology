package de.rafael.mods.chronon.technology.client;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.client.registry.ModEntityRenderers;
import de.rafael.mods.chronon.technology.client.registry.ModScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

@Mod.EventBusSubscriber(modid = ChrononTech.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ChrononTechClient {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ModEntityRenderers.register();
        ModScreens.register();
    }

}
