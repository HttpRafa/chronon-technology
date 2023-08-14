package de.rafael.mods.chronon.technology.config;

import com.teamresourceful.resourcefulconfig.common.annotations.Category;
import com.teamresourceful.resourcefulconfig.common.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.common.config.EntryType;

/**
 * @author Rafael K.
 * @since 14/08/2023
 */

@Category(id = "accelerator", translation = "text.resourcefulconfig.chronontech.option.accelerator")
public final class AcceleratorConfig {

    @ConfigEntry(
            id = "boostTime",
            type = EntryType.INTEGER,
            translation = "text.resourcefulconfig.chronontech.option.accelerator.boostTime"
    )
    public static int boostTime = 20 * 25; // 25 seconds

    @ConfigEntry(
            id = "maxTickRate",
            type = EntryType.INTEGER,
            translation = "text.resourcefulconfig.chronontech.option.accelerator.maxTickRate"
    )
    public static int maxTickRate = 8; // 2^8

}
