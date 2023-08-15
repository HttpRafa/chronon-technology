package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.block.entity.CollectorBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES,
            ChrononTech.MOD_ID);

    public static final RegistryObject<BlockEntityType<CollectorBlockEntity>> CHRONON_COLLECTOR =
            BLOCK_ENTITIES.register("chronon_collector",
                    () -> BlockEntityType.Builder.of(CollectorBlockEntity::new, ModBlocks.CHRONON_COLLECTOR.get()).build(null));

    public static void register(IEventBus eventBus) {
        ChrononTech.LOGGER.debug("[REGISTRY] Adding block entities");
        BLOCK_ENTITIES.register(eventBus);
    }

}
