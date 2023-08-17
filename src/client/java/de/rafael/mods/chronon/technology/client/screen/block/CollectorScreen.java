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
package de.rafael.mods.chronon.technology.client.screen.block;

import com.mojang.blaze3d.systems.RenderSystem;
import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.screen.block.CollectorScreenHandler;
import de.rafael.mods.chronon.technology.util.helper.TimeHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 09/08/2023
 */

@Environment(EnvType.CLIENT)
public class CollectorScreen extends AbstractContainerScreen<CollectorScreenHandler> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(ChrononTech.MOD_ID, "textures/gui/chronon_collector.png");
    public static final ResourceLocation BAR_TEXTURE = new ResourceLocation(ChrononTech.MOD_ID, "textures/gui/chronon_bar.png");

    public CollectorScreen(CollectorScreenHandler abstractContainerMenu, Inventory inventory, Component component) {
        super(abstractContainerMenu, inventory, component);
        this.imageWidth = 176;
        this.imageHeight = 186;
        this.inventoryLabelY = 91;
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);

        renderBar(guiGraphics, x, y, mouseX, mouseY);
    }

    private void renderBar(@NotNull GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, BAR_TEXTURE);
        int scaledBar = this.menu.scaledBarSize();
        guiGraphics.blit(BAR_TEXTURE, x + 13, y + 77, 0, 0, scaledBar, 5);
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderTooltip(guiGraphics, mouseX, mouseY);

        if(isHovering(13, 77, CollectorScreenHandler.BAR_SIZE, 5, mouseX, mouseY)) {
            long storedTime = TimeHelper.millisFromChronons(this.menu.getChrononAmount());
            guiGraphics.renderTooltip(Minecraft.getInstance().font, Component.translatable("tooltip.chronontech.storage.storedTime")
                    .withStyle(ChatFormatting.AQUA).append(Component.literal(TimeHelper.formatTime(storedTime))
                            .withStyle(ChatFormatting.GRAY)), mouseX, mouseY);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

}
