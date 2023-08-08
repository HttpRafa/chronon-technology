package de.rafael.mods.chronon.technology.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.world.item.Rarity;

/**
 * @author Rafael K.
 * @since 09/08/2023
 */

@Getter
@AllArgsConstructor
public enum PlattingType {

    IRON(Rarity.COMMON, 0.5f),
    GOLD(Rarity.UNCOMMON, 1f),
    DIAMOND(Rarity.RARE, 2f),
    NETHERITE(Rarity.EPIC, 4f);

    private final Rarity rarity;
    private final float efficiency;

}
