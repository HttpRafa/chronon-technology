package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.entity.AcceleratorEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class ModEntities {

    public static final EntityType<AcceleratorEntity> ACCELERATOR = registerEntity("accelerator", EntityType.Builder.<AcceleratorEntity>of(AcceleratorEntity::new, MobCategory.MISC).sized(0.1f, 0.1f));

    private static <T extends Entity> @NotNull EntityType<T> registerEntity(String id, EntityType.@NotNull Builder<T> entityType) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(ChrononTech.MOD_ID, id), entityType.build(id));
    }

    public static void register() {

    }

}
