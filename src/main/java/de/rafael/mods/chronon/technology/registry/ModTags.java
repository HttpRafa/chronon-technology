package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 14/08/2023
 */

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> ACCELERATION_BLACKLIST = createTag("blacklist/acceleration");

        private static @NotNull TagKey<Block> createTag(String id) {
            return BlockTags.create(new ResourceLocation(ChrononTech.MOD_ID, id));
        }

    }

}
