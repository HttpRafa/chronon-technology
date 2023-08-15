package de.rafael.mods.chronon.technology.screen.slot;

import lombok.Getter;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author Rafael K.
 * @since 09/08/2023
 */

@Getter
public class TypeLockedSlot extends SlotItemHandler {

    private final Class<? extends Item>[] items;

    @SafeVarargs
    public TypeLockedSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, Class<? extends Item>... items) {
        super(itemHandler, index, xPosition, yPosition);
        this.items = items;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack itemStack) {
        return Arrays.stream(items).anyMatch(item -> itemStack.getItem().getClass().equals(item));
    }

}
