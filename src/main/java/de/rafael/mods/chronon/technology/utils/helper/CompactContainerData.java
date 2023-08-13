package de.rafael.mods.chronon.technology.utils.helper;

import lombok.AllArgsConstructor;
import net.minecraft.world.inventory.ContainerData;

/**
 * @author Rafael K.
 * @since 09/08/2023
 */

@AllArgsConstructor
public abstract class CompactContainerData implements ContainerData {

    private final int amount;

    public abstract int get(int i);

    public abstract void set(int i, int j);

    @Override
    public int getCount() {
        return this.amount;
    }

}
