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
import de.rafael.mods.chronon.technology.item.PlatingItem;
import de.rafael.mods.chronon.technology.item.abstracted.ItemWithDescription;
import de.rafael.mods.chronon.technology.types.PlatingType;
import de.rafael.mods.chronon.technology.util.values.Constants;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChrononTech.MOD_ID);

    // Core
    public static final RegistryObject<Item> CHRONON_CORE = ITEMS.register("chronon_core",
            () -> new ItemWithDescription(new Item.Properties().stacksTo(1), Constants.Components.CORE_DESCRIPTION));
    public static final RegistryObject<Item> CHRONON_ACCELERATOR = ITEMS.register("chronon_accelerator", AcceleratorItem::new);

    // Plating
    public static final RegistryObject<Item> IRON_PLATING = ITEMS.register("iron_plating",
            () -> new PlatingItem(PlatingType.IRON));
    public static final RegistryObject<Item> GOLD_PLATING = ITEMS.register("gold_plating",
            () -> new PlatingItem(PlatingType.GOLD));
    public static final RegistryObject<Item> DIAMOND_PLATING = ITEMS.register("diamond_plating",
            () -> new PlatingItem(PlatingType.DIAMOND));
    public static final RegistryObject<Item> NETHERITE_PLATING = ITEMS.register("netherite_plating",
            () -> new PlatingItem(PlatingType.NETHERITE));

    public static final RegistryObject<Item> DEBUG_PLATING = ITEMS.register("debug_plating",
            () -> new PlatingItem(PlatingType.DEBUG));

    public static void register(IEventBus eventBus) {
        ChrononTech.LOGGER.debug("[REGISTRY] Adding items");
        ITEMS.register(eventBus);
    }

}
