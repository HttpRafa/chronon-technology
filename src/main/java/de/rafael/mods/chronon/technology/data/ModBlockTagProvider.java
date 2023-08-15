package de.rafael.mods.chronon.technology.data;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.registry.ModBlocks;
import de.rafael.mods.chronon.technology.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * @author Rafael K.
 * @since 13/08/2023
 */

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ChrononTech.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.CHRONON_COLLECTOR.get());
        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.CHRONON_COLLECTOR.get());

        tag(ModTags.Blocks.ACCELERATION_BLACKLIST)
                .add(ModBlocks.CHRONON_COLLECTOR.get());
    }

}
