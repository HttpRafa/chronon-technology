package de.rafael.mods.chronon.technology.client.screen.block;

import com.mojang.blaze3d.systems.RenderSystem;
import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.screen.block.CollectorScreenHandler;
import de.rafael.mods.chronon.technology.utils.helper.TimeHelper;
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

        if(isHovering(13, 77, CollectorScreenHandler.BAR_SIZE, 5, mouseX, mouseY)) {
            long storedTime = TimeHelper.millisFromChronons(this.menu.getChrononAmount());
            guiGraphics.renderTooltip(Minecraft.getInstance().font, Component.translatable("tooltip.chronontech.storage.storedTime")
                    .withStyle(ChatFormatting.GREEN).append(Component.literal(TimeHelper.formatTime(storedTime))
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
