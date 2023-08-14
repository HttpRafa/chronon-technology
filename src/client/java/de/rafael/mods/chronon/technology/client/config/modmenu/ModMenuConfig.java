package de.rafael.mods.chronon.technology.client.config.modmenu;

import com.teamresourceful.resourcefulconfig.client.ConfigScreen;
import com.teamresourceful.resourcefulconfig.common.config.ResourcefulConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.config.ChrononTechConfig;

/**
 * @author Rafael K.
 * @since 14/08/2023
 */

public class ModMenuConfig implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> {
            ResourcefulConfig config = ChrononTech.CONFIGURATOR.getConfig(ChrononTechConfig.class);
            if(config == null) {
                ChrononTech.LOGGER.error("Config is null. Failed to get config");
                return null;
            }
            return new ConfigScreen(null, config);
        };
    }

}
