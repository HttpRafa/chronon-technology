package de.rafael.mods.chronon.technology.item.abstracted;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafael K.
 * @since 12/08/2023
 */

public class ItemWithDescription extends Item {

    private final List<Component> description;

    public ItemWithDescription(Properties properties, Component... description) {
        super(properties);
        this.description = List.of(description);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, @NotNull List<Component> list, TooltipFlag tooltipFlag) {
        list.addAll(description);
        super.appendHoverText(itemStack, level, list, tooltipFlag);
    }

}
