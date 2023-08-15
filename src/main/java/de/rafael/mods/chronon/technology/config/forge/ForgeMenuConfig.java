package de.rafael.mods.chronon.technology.config.forge;

import com.teamresourceful.resourcefulconfig.client.ConfigScreen;
import com.teamresourceful.resourcefulconfig.common.config.ResourcefulConfig;
import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.config.ChrononTechConfig;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;

/**
 * @author Rafael K.
 * @since 15/08/2023
 */

public class ForgeMenuConfig {

    public static void register() {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((minecraft, screen) -> {
                    ResourcefulConfig config = ChrononTech.CONFIGURATOR.getConfig(ChrononTechConfig.class);
                    if(config == null) {
                        return null;
                    }
                    return new ConfigScreen(null, config);
                })
        );
    }

}
