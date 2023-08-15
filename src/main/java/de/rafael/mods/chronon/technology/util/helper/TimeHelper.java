package de.rafael.mods.chronon.technology.util.helper;

import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 12/08/2023
 */

public class TimeHelper {

    public static long millisFromChronons(int chronons) {
        return (chronons * 20L) * (1000 / 20);
    }

    public static @NotNull String formatTime(long millis) {
        return (String.format("%d hr %d min, %d sec",
                millis / (1000 * 60 * 60),
                (millis % (1000 * 60 * 60)) / (1000 * 60),
                ((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000));
    }

}
