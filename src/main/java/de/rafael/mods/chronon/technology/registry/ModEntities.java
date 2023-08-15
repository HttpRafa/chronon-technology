package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.entity.AcceleratorEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ChrononTech.MOD_ID);

    public static final RegistryObject<EntityType<AcceleratorEntity>> ACCELERATOR = ENTITIES.register("accelerator",
            () -> EntityType.Builder.<AcceleratorEntity>of(AcceleratorEntity::new, MobCategory.MISC).sized(0.1f, 0.1f).build("accelerator"));

    public static void register(IEventBus eventBus) {
        ChrononTech.LOGGER.debug("[REGISTRY] Adding entities");
        ENTITIES.register(eventBus);
    }

}
