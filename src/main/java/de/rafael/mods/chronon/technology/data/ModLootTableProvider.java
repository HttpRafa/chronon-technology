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

import de.rafael.mods.chronon.technology.attribute.AttributeHolder;
import de.rafael.mods.chronon.technology.block.base.interfaces.BlockEntityHolder;
import de.rafael.mods.chronon.technology.registry.ModBlocks;
import de.rafael.mods.chronon.technology.util.values.NbtKey;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

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
        dropWithData(ModBlocks.CHRONON_COLLECTOR);
    }

    public void dropWithData(Block block) {
        if(block instanceof BlockEntityHolder<?> holder) {
            CopyNbtFunction.Builder builder = CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY);
            BlockEntity dummyEntity = holder.createDummyEntity();
            if(dummyEntity instanceof AttributeHolder attributeHolder) {
                attributeHolder.getNbtKeys().forEach(nbtKey -> builder.copy(nbtKey.getKey(), NbtKey.BLOCK_ENTITY_TAG + "." + nbtKey.getKey()));
            }
            add(block, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f)).add(LootItem.lootTableItem(block).apply(builder))));
        }
    }

}
