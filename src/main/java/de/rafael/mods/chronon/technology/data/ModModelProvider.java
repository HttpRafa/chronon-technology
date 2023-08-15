package de.rafael.mods.chronon.technology.data;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.registry.ModBlocks;
import de.rafael.mods.chronon.technology.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

public class ModModelProvider {

    public static class ModBlockStateProvider extends BlockStateProvider {

        public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
            super(output, ChrononTech.MOD_ID, exFileHelper);
        }

        @Override
        protected void registerStatesAndModels() {
            simpleBlockWithItem(ModBlocks.CHRONON_COLLECTOR.get(),
                    new ModelFile.UncheckedModelFile(new ResourceLocation(ChrononTech.MOD_ID, "block/chronon_collector")));
        }

    }

    public static class ModItemModelProvider extends ItemModelProvider {

        public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
            super(output, ChrononTech.MOD_ID, existingFileHelper);
        }

        @Override
        protected void registerModels() {
            basicItem(ModItems.CHRONON_CORE.get());
            basicItem(ModItems.CHRONON_ACCELERATOR.get());

            basicItem(ModItems.IRON_PLATING.get());
            basicItem(ModItems.GOLD_PLATING.get());
            basicItem(ModItems.DIAMOND_PLATING.get());
            basicItem(ModItems.NETHERITE_PLATING.get());
            basicItem(ModItems.DEBUG_PLATING.get());
        }

    }

}
