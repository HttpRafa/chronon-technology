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
import de.rafael.mods.chronon.technology.screen.block.CollectorScreenHandler;
import de.rafael.mods.chronon.technology.screen.block.base.BaseContainerMenu;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 09/08/2023
 */

public class ModScreenHandlers {

    public static final MenuType<CollectorScreenHandler> CHRONON_COLLECTOR = registerMenuType("chronon_collector", CollectorScreenHandler::new);

    private static <T extends AbstractContainerMenu> @NotNull MenuType<T> registerMenuType(String id, ExtendedScreenHandlerType.ExtendedFactory<T> factory) {
        MenuType<T> menuType = new ExtendedScreenHandlerType<>(factory);
        Registry.register(BuiltInRegistries.MENU, new ResourceLocation(ChrononTech.MOD_ID, id), menuType);
        return menuType;
    }

    public static void register() {
        ChrononTech.LOGGER.info("[REGISTRY] Adding screen handlers");
    }

}
