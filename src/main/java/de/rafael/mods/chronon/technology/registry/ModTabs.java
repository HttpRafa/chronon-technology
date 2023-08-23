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
package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.item.AcceleratorItem;
import de.rafael.mods.chronon.technology.item.TimeChangerItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class ModTabs {

    public static final CreativeModeTab MOD_TAB = registerTab("chronon_technology", FabricItemGroup.builder()
            .title(Component.translatable("category.chronon_technology"))
            .icon(() -> new ItemStack(ModItems.CHRONON_CORE))
            .displayItems((parameters, output) -> {
                /* Blocks */
                output.accept(ModBlocks.CHRONON_COLLECTOR);

                /* Primary Items */
                output.accept(ModItems.CHRONON_ACCELERATOR);
                output.accept(AcceleratorItem.fullyChargedStack());
                output.accept(ModItems.TIME_CHANGER);
                output.accept(TimeChangerItem.fullyChargedStack());

                /* Ingredients */
                output.accept(ModItems.CHRONON_CORE);

                /* Plating */
                output.accept(ModItems.IRON_PLATING);
                output.accept(ModItems.GOLD_PLATING);
                output.accept(ModItems.DIAMOND_PLATING);
                output.accept(ModItems.NETHERITE_PLATING);
                output.accept(ModItems.DEBUG_PLATING);
            }).build());

    private static @NotNull CreativeModeTab registerTab(String id, CreativeModeTab tab) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(ChrononTech.MOD_ID, id), tab);
    }

    public static void register() {
        ChrononTech.LOGGER.info("[REGISTRY] Adding creative tabs");
    }

}
