package de.rafael.mods.chronon.technology.client.screen.block;

import com.mojang.blaze3d.systems.RenderSystem;
import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.screen.block.CollectorScreenHandler;
import net.minecraft.client.gui.Gui;
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

public class CollectorScreen extends AbstractContainerScreen<CollectorScreenHandler> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(ChrononTech.MOD_ID, "textures/gui/chronon_collector.png");
    public static final ResourceLocation BAR_TEXTURE = new ResourceLocation(ChrononTech.MOD_ID, "textures/gui/chronon_bar.png");

    public CollectorScreen(CollectorScreenHandler abstractContainerMenu, Inventory inventory, Component component) {
        super(abstractContainerMenu, inventory, component);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);

        renderBar(guiGraphics, x, y);
    }

    private void renderBar(GuiGraphics guiGraphics, int x, int y) {
        if(this.menu.isCollecting()) guiGraphics.blit(BAR_TEXTURE, x + 157, y + 31, 0, 0, 8, this.menu.scaledBarSize());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

}
