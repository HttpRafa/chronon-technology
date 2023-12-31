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
package de.rafael.mods.chronon.technology.config;

import de.rafael.mods.chronon.technology.ChrononTech;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

/**
 * @author Rafael K.
 * @since 14/08/2023
 */

@Config(name = ChrononTech.MOD_ID)
public final class ChrononTechConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public Accelerator accelerator = new Accelerator();

    @ConfigEntry.Gui.CollapsibleObject
    public Gui gui = new Gui();

    public static class Accelerator {

        public int maxTickRate = 8; // 2^8

        public long efficiency = 18;

        public long boostTime = 20 * 25; // 25 seconds
        public long storageSize = 86400;

    }

    public static class Gui {

        public int chrononColor = 0x0D7BCB;

    }

}
