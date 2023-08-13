package de.rafael.mods.chronon.technology.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import de.rafael.mods.chronon.technology.entity.AcceleratorEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

/**
 * @author Rafael K.
 * @since 12/08/2023
 */

public class AcceleratorEntityRenderer extends EntityRenderer<AcceleratorEntity> {

    public AcceleratorEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(AcceleratorEntity entity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        super.render(entity, f, g, poseStack, multiBufferSource, i);
    }

    @Override
    public ResourceLocation getTextureLocation(AcceleratorEntity entity) {
        return null;
    }

}
