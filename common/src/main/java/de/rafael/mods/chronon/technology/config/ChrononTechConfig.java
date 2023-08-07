package de.rafael.mods.chronon.technology.config;

import com.teamresourceful.resourcefulconfig.common.annotations.Config;
import com.teamresourceful.resourcefulconfig.common.annotations.InlineCategory;

/**
 * @author Rafael K.
 * @since 06/08/2023
 */

@Config("chronon_technology")
public final class ChrononTechConfig {

    @InlineCategory
    public static AcceleratorConfig acceleratorConfig;

}
