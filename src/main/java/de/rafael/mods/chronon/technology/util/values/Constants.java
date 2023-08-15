/*
 * MIT License
 *
 * Copyright (c) 2023 Rafael
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package de.rafael.mods.chronon.technology.util.values;

import java.util.function.Function;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

/**
 * @author Rafael K.
 * @since 12/08/2023
 */

public class Constants {

    public static class Components {

        public static final Component STORAGE_DESCRIPTION = Component.literal("► ").withStyle(ChatFormatting.DARK_GRAY).append(Component.translatable("tooltip.chronontech.storage.description").withStyle(ChatFormatting.GRAY));
        public static final Component CORE_DESCRIPTION = Component.literal("► ").withStyle(ChatFormatting.DARK_GRAY).append(Component.translatable("tooltip.chronontech.core.description").withStyle(ChatFormatting.RED));

    }

    public static class Sounds {

        public static final Function<Integer, Float> SOUND_SUPPLIER = rate ->
                (float) Math.pow(2, (-12 + ((Math.log(rate) / Math.log(2)) - 1)) / 12d); // Website: https://minecraft.fandom.com/wiki/Note_Block#Playing_music

    }

    public static class NbtKeys {

        public static final String TICK_RATE = "chronontech_tickRate";
        public static final String TICKS_LEFT = "chronontech_ticksLeft";
        public static final String BLOCK_POS = "chronontech_blockPos";

    }

}
