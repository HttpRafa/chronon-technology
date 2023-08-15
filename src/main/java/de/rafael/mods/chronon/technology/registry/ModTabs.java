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
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class ModTabs {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ChrononTech.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MOD_TAB = TABS.register("chronon_technology",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("category.chronon_technology"))
                    .icon(() -> new ItemStack(ModItems.CHRONON_CORE.get()))
                    .displayItems((parameters, output) -> {
                        // Blocks
                        output.accept(ModBlocks.CHRONON_COLLECTOR.get());

                        // Primary Items
                        output.accept(ModItems.CHRONON_ACCELERATOR.get());
                        output.accept(AcceleratorItem.fullyChargedStack());

                        // Ingredients
                        output.accept(ModItems.CHRONON_CORE.get());

                        // Plating
                        output.accept(ModItems.IRON_PLATING.get());
                        output.accept(ModItems.GOLD_PLATING.get());
                        output.accept(ModItems.DIAMOND_PLATING.get());
                        output.accept(ModItems.NETHERITE_PLATING.get());
                        output.accept(ModItems.DEBUG_PLATING.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        ChrononTech.LOGGER.debug("[REGISTRY] Adding creative tabs");
        TABS.register(eventBus);
    }

}
