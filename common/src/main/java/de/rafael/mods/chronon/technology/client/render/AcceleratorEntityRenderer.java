package de.rafael.mods.chronon.technology.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import de.rafael.mods.chronon.technology.client.utils.DrawUtils;
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
 * @since 06/08/2023
 */

public class AcceleratorEntityRenderer extends EntityRenderer<AcceleratorEntity> {

    public AcceleratorEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(@NotNull AcceleratorEntity entity, float f, float g, PoseStack poseStack, MultiBufferSource source, int i) {
        var tickRate = entity.tickRate() * 2;
        Component text = Component.literal("x" + tickRate).withStyle(ChatFormatting.WHITE);
        float paddingLeftRight = 0.11f;
        if(tickRate >= 10) {
            paddingLeftRight += 0.08f;
        }
        if(tickRate >= 100) {
            paddingLeftRight += 0.08f;
        }
        if(tickRate >= 1000) {
            paddingLeftRight += 0.08f;
        }
        var scale = new Vector3f(0.02f, -0.02f, 0.02f);
        DrawUtils.drawTextInWorld(text, source, poseStack, new Vector3f(-paddingLeftRight, 0.064f, 0.51f), scale, Axis.YP.rotationDegrees(0));
        DrawUtils.drawTextInWorld(text, source, poseStack, new Vector3f(paddingLeftRight, 0.064f, -0.51f), scale, Axis.YP.rotationDegrees(180));
        DrawUtils.drawTextInWorld(text, source, poseStack, new Vector3f(0.51f, 0.064f, paddingLeftRight), scale, Axis.YP.rotationDegrees(90));
        DrawUtils.drawTextInWorld(text, source, poseStack, new Vector3f(-0.51f, 0.064f, -paddingLeftRight), scale, Axis.YP.rotationDegrees(-90));
        DrawUtils.drawTextInWorld(text, source, poseStack, new Vector3f(-paddingLeftRight, 0.51f, -0.064f), scale, Axis.XP.rotationDegrees(90));
        DrawUtils.drawTextInWorld(text, source, poseStack, new Vector3f(-paddingLeftRight, -0.51f, 0.064f), scale, Axis.XP.rotationDegrees(-90));
    }

    @Override
    public ResourceLocation getTextureLocation(AcceleratorEntity entity) {
        return null;
    }

}
