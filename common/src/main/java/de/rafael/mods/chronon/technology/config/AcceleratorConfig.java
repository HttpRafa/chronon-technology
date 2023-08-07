package de.rafael.mods.chronon.technology.config;

import com.teamresourceful.resourcefulconfig.common.annotations.Category;
import com.teamresourceful.resourcefulconfig.common.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.common.config.EntryType;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

@Category(id = "accelerator", translation = "text.resourcefulconfig.chronontech.option.accelerator")
public final class AcceleratorConfig {

    @ConfigEntry(
            id = "eachUseTicks",
            type = EntryType.INTEGER,
            translation = "text.resourcefulconfig.chronontech.option.eachUseTicks"
    )
    public static int eachUseTicks = 20 * 30;

    @ConfigEntry(
            id = "maxTickRate",
            type = EntryType.INTEGER,
            translation = "text.resourcefulconfig.chronontech.option.maxTickRate"
    )
    public static int maxTickRate = 8;

}
