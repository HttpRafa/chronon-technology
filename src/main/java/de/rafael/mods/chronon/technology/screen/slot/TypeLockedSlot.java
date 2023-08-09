package de.rafael.mods.chronon.technology.screen.slot;

import lombok.Getter;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author Rafael K.
 * @since 09/08/2023
 */

@Getter
public class TypeLockedSlot extends Slot {

    private final Class<? extends Item>[] items;

    @SafeVarargs
    public TypeLockedSlot(Container container, int index, int x, int y, Class<? extends Item>... items) {
        super(container, index, x, y);
        this.items = items;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack itemStack) {
        return Arrays.stream(items).anyMatch(item -> itemStack.getItem().getClass().isAssignableFrom(item));
    }

}
