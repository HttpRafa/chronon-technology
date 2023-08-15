package de.rafael.mods.chronon.technology.config;

import com.teamresourceful.resourcefulconfig.common.annotations.Category;
import com.teamresourceful.resourcefulconfig.common.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.common.config.EntryType;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

@Category(id = "gui", translation = "text.resourcefulconfig.chronontech.option.gui")
public final class GuiConfig {

    @ConfigEntry(
            id = "chrononColor",
            type = EntryType.INTEGER,
            translation = "text.resourcefulconfig.chronontech.option.gui.chrononColor"
    )
    public static int chrononColor = 0x0D7BCB;

}
