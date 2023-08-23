/*
 * MIT License
 *
 * Copyright (c) 2023 Rafael
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
        generator.generateFlatItem(ModItems.TIME_CHANGER, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(ModItems.IRON_PLATING, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.GOLD_PLATING, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.DIAMOND_PLATING, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.NETHERITE_PLATING, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.DEBUG_PLATING, ModelTemplates.FLAT_ITEM);
    }

}
