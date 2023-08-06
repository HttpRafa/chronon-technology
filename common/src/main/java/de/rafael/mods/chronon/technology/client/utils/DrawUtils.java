package de.rafael.mods.chronon.technology.client.utils;

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
 * @since 06/08/2023
 */

public class DrawUtils {

    public static void drawTextInWorld(@NotNull Component component, MultiBufferSource source, PoseStack poseStack, Vector3f translation, Vector3f scale, Quaternionf rotation) {
        var color = -1;
        if(component.getStyle().getColor() != null) {
            color = component.getStyle().getColor().getValue();
        } else if(ChatFormatting.GRAY.getColor() != null) {
            color = ChatFormatting.GRAY.getColor();
        }
        var font = Minecraft.getInstance().font;
        poseStack.pushPose();
        poseStack.translate(translation.x(), translation.y(), translation.z());
        poseStack.scale(scale.x(), scale.y(), scale.z());
        poseStack.mulPose(rotation);
        font.drawInBatch(component, 0f, 0f, color, false, poseStack.last().pose(), source, Font.DisplayMode.NORMAL, 0, LightTexture.FULL_BRIGHT);
        poseStack.popPose();
    }

}
