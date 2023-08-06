package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * @author Rafael K.
 * @since 06/08/2023
 */

public class ModBlocks {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ChrononTech.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> CHRONON_COLLECTOR = BLOCKS.register("chronon_collector", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static void register() {
        ChrononTech.LOGGER.info("Registering blocks...");
        BLOCKS.register();
    }

}
