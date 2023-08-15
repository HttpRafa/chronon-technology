package de.rafael.mods.chronon.technology.data;

import de.rafael.mods.chronon.technology.registry.ModBlocks;
import de.rafael.mods.chronon.technology.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NotNull BlockModelGenerators generator) {
        generator.createNonTemplateModelBlock(ModBlocks.CHRONON_COLLECTOR);
    }

    @Override
    public void generateItemModels(@NotNull ItemModelGenerators generator) {
        generator.generateFlatItem(ModItems.CHRONON_CORE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.CHRONON_ACCELERATOR, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(ModItems.IRON_PLATING, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.GOLD_PLATING, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.DIAMOND_PLATING, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.NETHERITE_PLATING, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.DEBUG_PLATING, ModelTemplates.FLAT_ITEM);
    }

}
