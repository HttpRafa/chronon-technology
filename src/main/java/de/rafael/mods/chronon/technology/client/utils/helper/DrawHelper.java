package de.rafael.mods.chronon.technology.client.utils.helper;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;

/**
 * @author Rafael K.
 * @since 14/08/2023
 */

public class DrawHelper {

    public static void drawTextInWorld(@NotNull Component component, MultiBufferSource source, PoseStack poseStack, Vector3f translation, Vector3f scale, Quaternionf rotation) {
        int color = component.getStyle().getColor() != null ? component.getStyle().getColor().getValue() : ChatFormatting.GRAY.getColor();
        drawWithTSR(poseStack, translation, scale, rotation, () -> Minecraft.getInstance().font.drawInBatch(component, 0f, 0f, color, false, poseStack.last().pose(), source, Font.DisplayMode.NORMAL, 0, LightTexture.FULL_BRIGHT));
    }

    public static void drawWithTSR(@NotNull PoseStack poseStack, @NotNull Vector3f translation, @NotNull Vector3f scale, Quaternionf rotation, @NotNull Runnable renderer) {
        poseStack.pushPose();
        poseStack.translate(translation.x(), translation.y(), translation.z());
        poseStack.scale(scale.x(), scale.y(), scale.z());
        poseStack.mulPose(rotation);
        renderer.run();
        poseStack.popPose();
    }

}
