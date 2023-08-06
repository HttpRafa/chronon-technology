package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.entity.AcceleratorEntity;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

/**
 * @author Rafael K.
 * @since 06/08/2023
 */

public class ModEntities {

    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ChrononTech.MOD_ID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<? extends AcceleratorEntity>> ACCELERATOR = createEntity("accelerator",
            EntityType.Builder.<AcceleratorEntity>of(AcceleratorEntity::new, MobCategory.MISC).sized(0.1f, 0.1f));

    public static <T extends Entity> RegistrySupplier<EntityType<? extends T>> createEntity(String id, EntityType.Builder<T> builder) {
        return ENTITIES.register(id, () -> builder.build(id));
    }

    public static void register() {
        ChrononTech.LOGGER.info("Registering blocks...");
        ENTITIES.register();
    }

}
