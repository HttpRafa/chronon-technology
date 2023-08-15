package de.rafael.mods.chronon.technology.item;

import de.rafael.mods.chronon.technology.types.PlatingType;
import lombok.Getter;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Rafael K.
 * @since 09/08/2023
 */

@Getter
public class PlatingItem extends Item {

    private final PlatingType platingType;

    public PlatingItem(@NotNull PlatingType platingType) {
        super(platingType == PlatingType.NETHERITE ? new Item.Properties().rarity(platingType.getRarity()).stacksTo(1).fireResistant() : new Item.Properties().rarity(platingType.getRarity()).stacksTo(1));
        this.platingType = platingType;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, tooltip, tooltipFlag);
        tooltip.add(Component.translatable("tooltip.chronontech.plating.efficiency").withStyle(ChatFormatting.AQUA)
                .append(Component.literal((platingType.getEfficiency() >= 0 ? "x" + platingType.getEfficiency() : "∞")).withStyle(ChatFormatting.GRAY)));
    }

}
