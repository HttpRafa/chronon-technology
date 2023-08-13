package de.rafael.mods.chronon.technology.types;

import de.rafael.mods.chronon.technology.ChrononTech;
import lombok.Getter;
import net.minecraft.world.item.Rarity;

/**
 * @author Rafael K.
 * @since 09/08/2023
 */

@Getter
public enum PlattingType {

    IRON(Rarity.COMMON, 0.5f),
    GOLD(Rarity.UNCOMMON, 1f),
    DIAMOND(Rarity.RARE, 2f),
    NETHERITE(Rarity.EPIC, 4f),
    DEBUG(Rarity.EPIC, Float.MAX_VALUE / 4);

    private final Rarity rarity;
    private final float efficiency;
    private final int ticksPerChronon;

    PlattingType(Rarity rarity, float efficiency) {
        this.rarity = rarity;
        this.efficiency = efficiency;
        this.ticksPerChronon = (int) (20 / efficiency);
    }

}
