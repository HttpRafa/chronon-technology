package de.rafael.mods.chronon.technology.data;

import de.rafael.mods.chronon.technology.registry.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    protected ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        /* Chronon Collector */
        dropSelf(ModBlocks.CHRONON_COLLECTOR);
    }

}
