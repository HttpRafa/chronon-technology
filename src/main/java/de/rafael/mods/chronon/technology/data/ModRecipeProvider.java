package de.rafael.mods.chronon.technology.data;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.registry.ModBlocks;
import de.rafael.mods.chronon.technology.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * @author Rafael K.
 * @since 08/08/2023
 */

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModItems.CHRONON_CORE.get())
                .group("chronon")
                .pattern("OSO").pattern("SES").pattern("OSO")
                .define('O', Items.OBSIDIAN)
                .define('S', Items.ECHO_SHARD)
                .define('E', Items.ENDER_EYE)
                .unlockedBy(getHasName(Items.OBSIDIAN), has(Items.OBSIDIAN))
                .unlockedBy(getHasName(Items.ECHO_SHARD), has(Items.ECHO_SHARD))
                .unlockedBy(getHasName(Items.ENDER_EYE), has(Items.ENDER_EYE))
                .save(writer);
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModItems.CHRONON_ACCELERATOR.get())
                .group("chronon")
                .pattern("OSO").pattern("REC").pattern("IBI")
                .define('O', Items.OBSIDIAN)
                .define('S', Items.OBSERVER)
                .define('C', Items.COMPARATOR)
                .define('R', Items.REPEATER)
                .define('I', Items.IRON_BLOCK)
                .define('B', Items.REDSTONE_BLOCK)
                .define('E', ModItems.CHRONON_CORE.get())
                .unlockedBy(getHasName(Items.OBSIDIAN), has(Items.OBSIDIAN))
                .unlockedBy(getHasName(Items.OBSERVER), has(Items.OBSERVER))
                .unlockedBy(getHasName(Items.COMPARATOR), has(Items.COMPARATOR))
                .unlockedBy(getHasName(Items.REPEATER), has(Items.REPEATER))
                .unlockedBy(getHasName(Items.IRON_BLOCK), has(Items.IRON_BLOCK))
                .unlockedBy(getHasName(Items.REDSTONE_BLOCK), has(Items.REDSTONE_BLOCK))
                .unlockedBy(getHasName(ModItems.CHRONON_CORE.get()), has(ModItems.CHRONON_CORE.get()))
                .save(writer);
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.CHRONON_COLLECTOR.get())
                .group("chronon")
                .pattern("OGO").pattern("CER").pattern("OBO")
                .define('O', Items.OBSIDIAN)
                .define('G', Items.TINTED_GLASS)
                .define('C', Items.COMPARATOR)
                .define('B', Items.REDSTONE_BLOCK)
                .define('R', Items.REPEATER)
                .define('E', ModItems.CHRONON_CORE.get())
                .unlockedBy(getHasName(Items.OBSIDIAN), has(Items.OBSIDIAN))
                .unlockedBy(getHasName(Items.TINTED_GLASS), has(Items.TINTED_GLASS))
                .unlockedBy(getHasName(Items.COMPARATOR), has(Items.COMPARATOR))
                .unlockedBy(getHasName(Items.REDSTONE_BLOCK), has(Items.REDSTONE_BLOCK))
                .unlockedBy(getHasName(Items.REPEATER), has(Items.REPEATER))
                .unlockedBy(getHasName(ModItems.CHRONON_CORE.get()), has(ModItems.CHRONON_CORE.get()))
                .save(writer);

        // Plating
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModItems.IRON_PLATING.get())
                .group("chronon")
                .pattern("BIB").pattern("IGI").pattern("BIB")
                .define('B', Items.IRON_BARS)
                .define('I', Items.IRON_INGOT)
                .define('G', Items.TINTED_GLASS)
                .unlockedBy(getHasName(Items.IRON_BARS), has(Items.IRON_BARS))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .unlockedBy(getHasName(Items.TINTED_GLASS), has(Items.TINTED_GLASS))
                .save(writer);
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModItems.GOLD_PLATING.get())
                .group("chronon")
                .pattern("BIB").pattern("IPI").pattern("BIB")
                .define('B', Items.IRON_BARS)
                .define('I', Items.GOLD_INGOT)
                .define('P', ModItems.IRON_PLATING.get())
                .unlockedBy(getHasName(Items.IRON_BARS), has(Items.IRON_BARS))
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .unlockedBy(getHasName(ModItems.IRON_PLATING.get()), has(ModItems.IRON_PLATING.get()))
                .save(writer);
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModItems.DIAMOND_PLATING.get())
                .group("chronon")
                .pattern("BIB").pattern("IPI").pattern("BIB")
                .define('B', Items.IRON_BARS)
                .define('I', Items.DIAMOND)
                .define('P', ModItems.GOLD_PLATING.get())
                .unlockedBy(getHasName(Items.IRON_BARS), has(Items.IRON_BARS))
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .unlockedBy(getHasName(ModItems.GOLD_PLATING.get()), has(ModItems.GOLD_PLATING.get()))
                .save(writer);
        netheriteSmithing(writer, ModItems.DIAMOND_PLATING.get(), RecipeCategory.REDSTONE, ModItems.NETHERITE_PLATING.get());
    }

    protected static void netheriteSmithing(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer, @NotNull Item pIngredientItem, @NotNull RecipeCategory pCategory, Item pResultItem) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(pIngredientItem), Ingredient.of(Items.NETHERITE_INGOT), pCategory, pResultItem).unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT)).save(pFinishedRecipeConsumer, ChrononTech.MOD_ID + ":" + getItemName(pResultItem) + "_smithing");
    }

}
