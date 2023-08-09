package de.rafael.mods.chronon.technology.item;

import de.rafael.mods.chronon.technology.types.PlattingType;
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
public class PlattingItem extends Item {

    private final PlattingType plattingType;

    public PlattingItem(@NotNull PlattingType plattingType) {
        super(plattingType == PlattingType.NETHERITE ? new Item.Properties().rarity(plattingType.getRarity()).fireResistant() : new Item.Properties().rarity(plattingType.getRarity()));
        this.plattingType = plattingType;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, tooltip, tooltipFlag);
        tooltip.add(Component.translatable("tooltip.chronontech.platting.efficiency").withStyle(ChatFormatting.GREEN)
                .append(Component.literal("x" + plattingType.getEfficiency()).withStyle(ChatFormatting.GRAY)));
    }

}
