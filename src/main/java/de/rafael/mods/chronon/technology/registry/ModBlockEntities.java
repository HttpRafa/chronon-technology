package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.block.entity.CollectorBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

public class ModBlockEntities {

    public static BlockEntityType<CollectorBlockEntity> CHRONON_COLLECTOR = registerEntity("chronon_collector",
            FabricBlockEntityTypeBuilder.create(CollectorBlockEntity::new, ModBlocks.CHRONON_COLLECTOR));

    private static <T extends BlockEntity> @NotNull BlockEntityType<T> registerEntity(String id, @NotNull FabricBlockEntityTypeBuilder<T> builder) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(ChrononTech.MOD_ID, id), builder.build());
    }

    public static void register() {
        ChrononTech.LOGGER.info("[REGISTRY] Adding block entities");
    }

}
