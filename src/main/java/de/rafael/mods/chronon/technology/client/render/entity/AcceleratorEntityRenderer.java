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
package de.rafael.mods.chronon.technology.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.client.utils.helper.DrawHelper;
import de.rafael.mods.chronon.technology.entity.AcceleratorEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

/**
 * @author Rafael K.
 * @since 12/08/2023
 */

public class AcceleratorEntityRenderer extends EntityRenderer<AcceleratorEntity> {

    private static final ResourceLocation ARROW_TEXTURE = new ResourceLocation(ChrononTech.MOD_ID, "textures/world/accelerator/arrow.png");

    public AcceleratorEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(@NotNull AcceleratorEntity entity, float yaw, float delta, @NotNull PoseStack poseStack, @NotNull MultiBufferSource source, int i) {
        int rate = entity.getTickRate();
        Component component = Component.literal("x" + rate).withStyle(ChatFormatting.WHITE);
        //Component arrowComponent = Component.literal("<>").withStyle(ChatFormatting.AQUA);
        float offset = 0.11f + (0.08f * (String.valueOf(rate).length() - 1));
        float offsetY = 0f;
        Vector3f scale = new Vector3f(0.02f, -0.02f, 0.02f);

        // entity.stepAnimation(rate / (256 / 2f), 0.55f, -0.2f);

        { // SOUTH
            // DrawHelper.drawTextInWorld(arrowComponent, source, poseStack, new Vector3f(-offset + entity.getAnimationOffset(), 0.064f + offsetY, 0.51f), scale, Axis.YP.rotationDegrees(0));
            DrawHelper.drawTextInWorld(component, source, poseStack, new Vector3f(-offset, 0.064f - offsetY, 0.51f), scale, Axis.YP.rotationDegrees(0));
        }
        { // NORTH
            // DrawHelper.drawTextInWorld(arrowComponent, source, poseStack, new Vector3f(offset - entity.getAnimationOffset(), 0.064f + offsetY, -0.51f), scale, Axis.YP.rotationDegrees(180));
            DrawHelper.drawTextInWorld(component, source, poseStack, new Vector3f(offset, 0.064f - offsetY, -0.51f), scale, Axis.YP.rotationDegrees(180));
        }
        { // EAST
            // DrawHelper.drawTextInWorld(arrowComponent, source, poseStack, new Vector3f(0.51f, 0.064f + offsetY, offset - entity.getAnimationOffset()), scale, Axis.YP.rotationDegrees(90));
            DrawHelper.drawTextInWorld(component, source, poseStack, new Vector3f(0.51f, 0.064f - offsetY, offset), scale, Axis.YP.rotationDegrees(90));
        }
        { // WEST
            // DrawHelper.drawTextInWorld(arrowComponent, source, poseStack, new Vector3f(-0.51f, 0.064f + offsetY, -offset + entity.getAnimationOffset()), scale, Axis.YP.rotationDegrees(-90));
            DrawHelper.drawTextInWorld(component, source, poseStack, new Vector3f(-0.51f, 0.064f - offsetY, -offset), scale, Axis.YP.rotationDegrees(-90));
        }
        { // UP
            // DrawHelper.drawTextInWorld(arrowComponent, source, poseStack, new Vector3f(-offset + entity.getAnimationOffset(), 0.51f, -0.064f - offsetY), scale, Axis.XP.rotationDegrees(90));
            DrawHelper.drawTextInWorld(component, source, poseStack, new Vector3f(-offset, 0.51f, -0.064f + offsetY), scale, Axis.XP.rotationDegrees(90));
        }
        { // DOWN
            // DrawHelper.drawTextInWorld(arrowComponent, source, poseStack, new Vector3f(-offset + entity.getAnimationOffset(), -0.51f, 0.064f + offsetY), scale, Axis.XP.rotationDegrees(-90));
            DrawHelper.drawTextInWorld(component, source, poseStack, new Vector3f(-offset, -0.51f, 0.064f - offsetY), scale, Axis.XP.rotationDegrees(-90));
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(AcceleratorEntity entity) {
        return ARROW_TEXTURE;
    }

}
