package de.rafael.mods.chronon.technology.utils;

import de.rafael.mods.chronon.technology.ChrononTech;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class Constants {

    /* Items */
    public static final String CHRONONS_KEY = createTag("storedChronons");

    /* Entity */
    public static final String TICKS_LEFT = createTag("ticksLeft");
    public static final String TICK_RATE = createTag("tickRate");
    public static final String TARGET_BLOCK = createTag("targetBlock");

    @Contract(pure = true)
    public static @NotNull String createTag(String id) {
        return ChrononTech.MOD_ID + "_" + id;
    }

}
