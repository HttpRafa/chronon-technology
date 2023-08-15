package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.block.CollectorBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ChrononTech.MOD_ID);

    public static final RegistryObject<Block> CHRONON_COLLECTOR = registerBlock("chronon_collector", CollectorBlock::new);

    private static <T extends Block> RegistryObject<T> registerBlock(String id, Supplier<T> block) {
        RegistryObject<T> registered = BLOCKS.register(id, block);
        ModItems.ITEMS.register(id, () -> new BlockItem(registered.get(), new Item.Properties()));
        return registered;
    }

    public static void register(IEventBus eventBus) {
        ChrononTech.LOGGER.debug("[REGISTRY] Adding blocks");
        BLOCKS.register(eventBus);
    }

}
