package de.rafael.mods.chronon.technology.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
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

    public AcceleratorEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(@NotNull AcceleratorEntity entity, float f, float g, PoseStack poseStack, MultiBufferSource source, int i) {
        int rate = entity.getTickRate();
        Component component = Component.literal("x" + rate).withStyle(ChatFormatting.WHITE);
        float padding = 0.11f + (0.08f * (String.valueOf(rate).length() - 1));
        Vector3f scale = new Vector3f(0.02f, -0.02f, 0.02f);

        // Render | Is the same as Time in a Bottle
        DrawHelper.drawTextInWorld(component, source, poseStack, new Vector3f(-padding, 0.064f, 0.51f), scale, Axis.YP.rotationDegrees(0));
        DrawHelper.drawTextInWorld(component, source, poseStack, new Vector3f(padding, 0.064f, -0.51f), scale, Axis.YP.rotationDegrees(180));
        DrawHelper.drawTextInWorld(component, source, poseStack, new Vector3f(0.51f, 0.064f, padding), scale, Axis.YP.rotationDegrees(90));
        DrawHelper.drawTextInWorld(component, source, poseStack, new Vector3f(-0.51f, 0.064f, -padding), scale, Axis.YP.rotationDegrees(-90));
        DrawHelper.drawTextInWorld(component, source, poseStack, new Vector3f(-padding, 0.51f, -0.064f), scale, Axis.XP.rotationDegrees(90));
        DrawHelper.drawTextInWorld(component, source, poseStack, new Vector3f(-padding, -0.51f, 0.064f), scale, Axis.XP.rotationDegrees(-90));
    }

    @Override
    public ResourceLocation getTextureLocation(AcceleratorEntity entity) {
        return null;
    }

}
