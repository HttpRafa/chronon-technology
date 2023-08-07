package de.rafael.mods.chronon.technology.registry;

import com.mojang.datafixers.util.Pair;
import de.rafael.mods.chronon.technology.ChrononTech;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class ModBlocks {

    public static final Block CHRONON_COLLECTOR = registerBlock("chronon_collector",
            new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    @Contract("_, _ -> new")
    private static @NotNull Block registerBlock(String id, Block block) {
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(ChrononTech.MOD_ID, id), new BlockItem(block, new Item.Properties()));
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(ChrononTech.MOD_ID, id), block);
    }

    public static void register() {
        ChrononTech.LOGGER.info("[REGISTRY] Adding blocks");
    }

}
