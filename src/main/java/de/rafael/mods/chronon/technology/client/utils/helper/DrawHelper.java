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
package de.rafael.mods.chronon.technology.client.utils.helper;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 14/08/2023
 */

public class DrawHelper {

    public static void drawTextInWorld(@NotNull Component component, MultiBufferSource source, PoseStack poseStack, Vector3f translation, Vector3f scale, Quaternion rotation) {
        int color = component.getStyle().getColor() != null ? component.getStyle().getColor().getValue() : ChatFormatting.GRAY.getColor();
        drawWithTSR(poseStack, translation, scale, rotation, () ->
                Minecraft.getInstance().font.draw(poseStack, component, 0, 0, color));
    }

    public static void drawWithTSR(@NotNull PoseStack poseStack, @NotNull Vector3f translation, @NotNull Vector3f scale, Quaternion rotation, @NotNull Runnable renderer) {
        poseStack.pushPose();
        poseStack.translate(translation.x(), translation.y(), translation.z());
        poseStack.scale(scale.x(), scale.y(), scale.z());
        poseStack.mulPose(rotation);
        renderer.run();
        poseStack.popPose();
    }

}
