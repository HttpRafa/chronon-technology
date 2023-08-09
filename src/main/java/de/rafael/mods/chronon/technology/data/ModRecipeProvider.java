package de.rafael.mods.chronon.technology.data;

import com.terraformersmc.modmenu.util.mod.Mod;
import de.rafael.mods.chronon.technology.registry.ModBlocks;
import de.rafael.mods.chronon.technology.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> exporter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModItems.CHRONON_CORE)
                .group("chronon")
                .pattern("OSO").pattern("SES").pattern("OSO")
                .define('O', Items.OBSIDIAN)
                .define('S', Items.ECHO_SHARD)
                .define('E', Items.ENDER_EYE)
                .unlockedBy(FabricRecipeProvider.getHasName(Items.OBSIDIAN), FabricRecipeProvider.has(Items.OBSIDIAN))
                .unlockedBy(FabricRecipeProvider.getHasName(Items.ECHO_SHARD), FabricRecipeProvider.has(Items.ECHO_SHARD))
                .unlockedBy(FabricRecipeProvider.getHasName(Items.ENDER_EYE), FabricRecipeProvider.has(Items.ENDER_EYE))
                .save(exporter, new ResourceLocation(FabricRecipeProvider.getSimpleRecipeName(ModItems.CHRONON_CORE)));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModItems.CHRONON_ACCELERATOR)
                .group("chronon")
                .pattern("OSO").pattern("REC").pattern("IBI")
                .define('O', Items.OBSIDIAN)
                .define('S', Items.OBSERVER)
                .define('C', Items.COMPARATOR)
                .define('R', Items.REPEATER)
                .define('I', Items.IRON_BLOCK)
                .define('B', Items.REDSTONE_BLOCK)
                .define('E', ModItems.CHRONON_CORE)
                .unlockedBy(FabricRecipeProvider.getHasName(Items.OBSIDIAN), FabricRecipeProvider.has(Items.OBSIDIAN))
                .unlockedBy(FabricRecipeProvider.getHasName(Items.OBSERVER), FabricRecipeProvider.has(Items.OBSERVER))
                .unlockedBy(FabricRecipeProvider.getHasName(Items.COMPARATOR), FabricRecipeProvider.has(Items.COMPARATOR))
                .unlockedBy(FabricRecipeProvider.getHasName(Items.REPEATER), FabricRecipeProvider.has(Items.REPEATER))
                .unlockedBy(FabricRecipeProvider.getHasName(Items.IRON_BLOCK), FabricRecipeProvider.has(Items.IRON_BLOCK))
                .unlockedBy(FabricRecipeProvider.getHasName(Items.REDSTONE_BLOCK), FabricRecipeProvider.has(Items.REDSTONE_BLOCK))
                .unlockedBy(FabricRecipeProvider.getHasName(ModItems.CHRONON_CORE), FabricRecipeProvider.has(ModItems.CHRONON_CORE))
                .save(exporter, new ResourceLocation(FabricRecipeProvider.getSimpleRecipeName(ModItems.CHRONON_ACCELERATOR)));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.CHRONON_COLLECTOR)
                .group("chronon")
                .pattern("OGO").pattern("CER").pattern("OBO")
                .define('O', Items.OBSIDIAN)
                .define('G', Items.TINTED_GLASS)
                .define('C', Items.COMPARATOR)
                .define('B', Items.REDSTONE_BLOCK)
                .define('R', Items.REPEATER)
                .define('E', ModItems.CHRONON_CORE)
                .unlockedBy(FabricRecipeProvider.getHasName(Items.OBSIDIAN), FabricRecipeProvider.has(Items.OBSIDIAN))
                .unlockedBy(FabricRecipeProvider.getHasName(Items.TINTED_GLASS), FabricRecipeProvider.has(Items.TINTED_GLASS))
                .unlockedBy(FabricRecipeProvider.getHasName(Items.COMPARATOR), FabricRecipeProvider.has(Items.COMPARATOR))
                .unlockedBy(FabricRecipeProvider.getHasName(Items.REDSTONE_BLOCK), FabricRecipeProvider.has(Items.REDSTONE_BLOCK))
                .unlockedBy(FabricRecipeProvider.getHasName(Items.REPEATER), FabricRecipeProvider.has(Items.REPEATER))
                .unlockedBy(FabricRecipeProvider.getHasName(ModItems.CHRONON_CORE), FabricRecipeProvider.has(ModItems.CHRONON_CORE))
                .save(exporter, new ResourceLocation(FabricRecipeProvider.getSimpleRecipeName(ModBlocks.CHRONON_COLLECTOR)));

        /* Platting */
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModItems.IRON_PLATTING)
                .group("chronon")
                .pattern("BIB").pattern("IGI").pattern("BIB")
                .define('B', Items.IRON_BARS)
                .define('I', Items.IRON_INGOT)
                .define('G', Items.TINTED_GLASS)
                .unlockedBy(FabricRecipeProvider.getHasName(Items.IRON_BARS), FabricRecipeProvider.has(Items.IRON_BARS))
                .unlockedBy(FabricRecipeProvider.getHasName(Items.IRON_INGOT), FabricRecipeProvider.has(Items.IRON_INGOT))
                .unlockedBy(FabricRecipeProvider.getHasName(Items.TINTED_GLASS), FabricRecipeProvider.has(Items.TINTED_GLASS))
                .save(exporter, new ResourceLocation(FabricRecipeProvider.getSimpleRecipeName(ModItems.IRON_PLATTING)));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModItems.GOLD_PLATTING)
                .group("chronon")
                .pattern("BIB").pattern("IPI").pattern("BIB")
                .define('B', Items.IRON_BARS)
                .define('I', Items.GOLD_INGOT)
                .define('P', ModItems.IRON_PLATTING)
                .unlockedBy(FabricRecipeProvider.getHasName(Items.IRON_BARS), FabricRecipeProvider.has(Items.IRON_BARS))
                .unlockedBy(FabricRecipeProvider.getHasName(Items.GOLD_INGOT), FabricRecipeProvider.has(Items.GOLD_INGOT))
                .unlockedBy(FabricRecipeProvider.getHasName(ModItems.IRON_PLATTING), FabricRecipeProvider.has(ModItems.IRON_PLATTING))
                .save(exporter, new ResourceLocation(FabricRecipeProvider.getSimpleRecipeName(ModItems.GOLD_PLATTING)));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModItems.DIAMOND_PLATTING)
                .group("chronon")
                .pattern("BIB").pattern("IPI").pattern("BIB")
                .define('B', Items.IRON_BARS)
                .define('I', Items.DIAMOND)
                .define('P', ModItems.GOLD_PLATTING)
                .unlockedBy(FabricRecipeProvider.getHasName(Items.IRON_BARS), FabricRecipeProvider.has(Items.IRON_BARS))
                .unlockedBy(FabricRecipeProvider.getHasName(Items.DIAMOND), FabricRecipeProvider.has(Items.DIAMOND))
                .unlockedBy(FabricRecipeProvider.getHasName(ModItems.GOLD_PLATTING), FabricRecipeProvider.has(ModItems.GOLD_PLATTING))
                .save(exporter, new ResourceLocation(FabricRecipeProvider.getSimpleRecipeName(ModItems.DIAMOND_PLATTING)));
        netheriteSmithing(exporter, ModItems.DIAMOND_PLATTING, RecipeCategory.REDSTONE, ModItems.NETHERITE_PLATTING);
    }

}
