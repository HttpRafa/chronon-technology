package de.rafael.mods.chronon.technology;

import com.mojang.logging.LogUtils;
import de.rafael.mods.chronon.technology.client.ChrononTechClient;
import de.rafael.mods.chronon.technology.registry.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

/**
 * @author Rafael K.
 * @since 15/08/2023
 */

@Mod(ChrononTech.MOD_ID)
public class ChrononTech {

    public static final String MOD_ID = "chronontech";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ChrononTech() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Registry
        ModTabs.register(eventBus);
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModEntities.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModScreenHandlers.register(eventBus);

        // Listeners
        eventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

}
