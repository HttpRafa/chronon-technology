package de.rafael.mods.chronon.technology.item;

import de.rafael.mods.chronon.technology.types.PlattingType;
import lombok.Getter;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 09/08/2023
 */

@Getter
public class PlattingItem extends Item {

    private final PlattingType plattingType;

    public PlattingItem(@NotNull PlattingType plattingType) {
        super(plattingType == PlattingType.NETHERITE ? new Item.Properties().rarity(plattingType.getRarity()).fireResistant() : new Item.Properties().rarity(plattingType.getRarity()));
        this.plattingType = plattingType;
    }

}
