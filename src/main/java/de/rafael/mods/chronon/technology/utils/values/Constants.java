package de.rafael.mods.chronon.technology.utils.values;

import java.util.function.Function;

/**
 * @author Rafael K.
 * @since 12/08/2023
 */

public class Constants {

    public static class Sounds {

        public static final Function<Integer, Float> SOUND_SUPPLIER = rate ->
                (float) Math.pow(2, (-12 + ((Math.log(rate) / Math.log(2)) - 1)) / 12d); // Website: https://minecraft.fandom.com/wiki/Note_Block#Playing_music

    }

    public static class Numbers {

        public static final int BOOST_TIME = 20 * 25; // 25 seconds
        public static final int MAX_TICK_RATE = 8; // 2^8

    }

    public static class NbtKeys {

        public static final String TICK_RATE = "chronontech_tickRate";
        public static final String TICKS_LEFT = "chronontech_ticksLeft";
        public static final String BLOCK_POS = "chronontech_blockPos";

    }

}
