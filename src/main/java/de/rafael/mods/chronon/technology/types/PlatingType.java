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
package de.rafael.mods.chronon.technology.types;

import lombok.Getter;
import net.minecraft.world.item.Rarity;

/**
 * @author Rafael K.
 * @since 09/08/2023
 */

@Getter
public enum PlatingType {

    IRON(Rarity.COMMON, 0.5f),
    GOLD(Rarity.UNCOMMON, 1f),
    DIAMOND(Rarity.RARE, 2f),
    NETHERITE(Rarity.EPIC, 4f),
    DEBUG(Rarity.EPIC, -1f);

    private final Rarity rarity;
    private final float efficiency;
    private final int ticksPerChronon;

    PlatingType(Rarity rarity, float efficiency) {
        this.rarity = rarity;
        this.efficiency = efficiency;
        this.ticksPerChronon = (int) (20 / efficiency);
    }

}
