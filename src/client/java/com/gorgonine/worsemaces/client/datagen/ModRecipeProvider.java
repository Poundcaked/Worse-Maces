package com.gorgonine.worsemaces.client.datagen;

import com.gorgonine.worsemaces.ModBlocks;
import com.gorgonine.worsemaces.ModItems;
import com.gorgonine.worsemaces.WorseMaces;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import java.util.concurrent.CompletableFuture;


public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup,recipeExporter) {
            @Override
            public void generate() {
                //MACES
                createShaped(RecipeCategory.COMBAT, ModItems.WOODEN_MACE)
                        .pattern(" C ")
                        .pattern(" s ")
                        .pattern("   ")
                        .input('C', ModBlocks.WOODEN_CORE)
                        .input('s', Items.STICK)
                        .criterion(hasItem(ModBlocks.WOODEN_CORE), conditionsFromItem(ModBlocks.WOODEN_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "wooden_mace_crafting_recipe_1")));
                createShaped(RecipeCategory.COMBAT, ModItems.UNFINISHED_MACE)
                        .pattern(" C ")
                        .pattern(" s ")
                        .pattern("   ")
                        .input('C', ModBlocks.UNFINISHED_CORE)
                        .input('s', Items.STICK)
                        .criterion(hasItem(ModBlocks.UNFINISHED_CORE), conditionsFromItem(ModBlocks.UNFINISHED_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "unfinished_mace_crafting_recipe_1")));
                createShaped(RecipeCategory.COMBAT, ModItems.GAMBLERS_MACE)
                        .pattern(" C ")
                        .pattern(" s ")
                        .pattern("   ")
                        .input('C', ModBlocks.GAMBLERS_CORE)
                        .input('s', Items.BREEZE_ROD)
                        .criterion(hasItem(ModBlocks.GAMBLERS_CORE), conditionsFromItem(ModBlocks.GAMBLERS_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "gamblers_mace_crafting_recipe_1")));
                createShaped(RecipeCategory.COMBAT, ModItems.BRASS_BELL_MACE)
                        .pattern(" C ")
                        .pattern(" s ")
                        .pattern("   ")
                        .input('C', ModBlocks.BRASS_BELL_CORE)
                        .input('s', Items.GOLD_INGOT)
                        .criterion(hasItem(ModBlocks.BRASS_BELL_CORE), conditionsFromItem(ModBlocks.BRASS_BELL_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "brass_bell_mace_crafting_recipe_1")));
                createShaped(RecipeCategory.COMBAT, ModItems.POTION_MACE)
                        .pattern(" C ")
                        .pattern(" s ")
                        .pattern("   ")
                        .input('C', ModBlocks.POTION_CORE)
                        .input('s', Items.GLASS)
                        .criterion(hasItem(ModBlocks.POTION_CORE), conditionsFromItem(ModBlocks.POTION_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "potion_mace_crafting_recipe_1")));
                createShaped(RecipeCategory.COMBAT, ModItems.GROSS_MACE)
                        .pattern(" C ")
                        .pattern(" s ")
                        .pattern("   ")
                        .input('C', ModBlocks.GROSS_CORE)
                        .input('s', Items.SLIME_BALL)
                        .criterion(hasItem(ModBlocks.GROSS_CORE), conditionsFromItem(ModBlocks.GROSS_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "gross_mace_crafting_recipe_1")));
                createShaped(RecipeCategory.COMBAT, ModItems.APPLE_MACE)
                        .pattern(" C ")
                        .pattern(" s ")
                        .pattern("   ")
                        .input('C', ModBlocks.APPLE_CORE)
                        .input('s', Items.STICK)
                        .criterion(hasItem(ModBlocks.APPLE_CORE), conditionsFromItem(ModBlocks.APPLE_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "apple_mace_crafting_recipe_1")));
                createShaped(RecipeCategory.COMBAT, ModItems.PUFFERFISH_MACE)
                        .pattern(" C ")
                        .pattern(" s ")
                        .pattern("   ")
                        .input('C', ModBlocks.PUFFERFISH_CORE)
                        .input('s', Items.COD)
                        .criterion(hasItem(ModBlocks.PUFFERFISH_CORE), conditionsFromItem(ModBlocks.PUFFERFISH_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "pufferfish_mace_crafting_recipe_1")));
                createShaped(RecipeCategory.COMBAT, ModItems.TNT_MACE)
                        .pattern(" C ")
                        .pattern(" s ")
                        .pattern("   ")
                        .input('C', ModBlocks.TNT_CORE)
                        .input('s', Items.CHAIN)
                        .criterion(hasItem(ModBlocks.TNT_CORE), conditionsFromItem(ModBlocks.TNT_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "tnt_mace_crafting_recipe_1")));
                createShaped(RecipeCategory.COMBAT, ModItems.COMMAND_BLOCK_MACE)
                        .pattern(" C ")
                        .pattern(" i ")
                        .pattern(" d ")
                        .input('C', ModBlocks.COMMAND_BLOCK_CORE)
                        .input('i', Items.IRON_INGOT)
                        .input('d', Items.DIAMOND)
                        .criterion(hasItem(ModBlocks.COMMAND_BLOCK_CORE), conditionsFromItem(ModBlocks.COMMAND_BLOCK_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "command_block_mace_crafting_recipe_1")));

                //CORES
                createShaped(RecipeCategory.MISC, ModBlocks.WOODEN_CORE)
                        .pattern("WWW")
                        .pattern("WWW")
                        .pattern("WWW")
                        .input('W', ItemTags.LOGS)
                        .criterion(hasItem(Items.OAK_LOG), conditionsFromItem(Items.OAK_LOG))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "wooden_core_crafting_recipe")));

                createShaped(RecipeCategory.MISC, ModBlocks.GAMBLERS_CORE)
                        .pattern("sWs")
                        .pattern("WsW")
                        .pattern("sWs")
                        .input('W', Items.WHITE_CONCRETE)
                        .input('s', Items.WITHER_SKELETON_SKULL)
                        .criterion(hasItem(Items.WITHER_SKELETON_SKULL), conditionsFromItem(Items.WITHER_SKELETON_SKULL))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "gamblers_core_crafting_recipe")));

                createShaped(RecipeCategory.MISC, ModBlocks.UNFINISHED_CORE)
                        .pattern("ppp")
                        .pattern("psp")
                        .pattern("ppp")
                        .input('p', Items.IRON_PICKAXE)
                        .input('s', Items.HEAVY_CORE)
                        .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(Items.HEAVY_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "unfinished_core_crafting_recipe")));
                createShaped(RecipeCategory.MISC, ModBlocks.BRASS_BELL_CORE)
                        .pattern(" W ")
                        .pattern("WeW")
                        .pattern("W W")
                        .input('W', Items.BELL)
                        .input('e', Items.HEAVY_CORE)
                        .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(Items.HEAVY_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "brass_bell_core_crafting_recipe")));
                createShaped(RecipeCategory.MISC, ModBlocks.POTION_CORE)
                        .pattern("WWW")
                        .pattern("ses")
                        .pattern("WWW")
                        .input('W', Items.POTION)
                        .input('s', Items.GLASS)
                        .input('e', Items.HEAVY_CORE)
                        .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(Items.HEAVY_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "potion_core_crafting_recipe")));
                createShaped(RecipeCategory.MISC, ModBlocks.GROSS_CORE)
                        .pattern("WeW")
                        .pattern("ese")
                        .pattern("WeW")
                        .input('W', Items.SLIME_BALL)
                        .input('s', Items.HEAVY_CORE)
                        .input('e', Items.COARSE_DIRT)
                        .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(Items.HEAVY_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "gross_core_crafting_recipe")));
                createShaped(RecipeCategory.MISC, ModBlocks.APPLE_CORE)
                        .pattern("WWW")
                        .pattern("WsW")
                        .pattern("WWW")
                        .input('W', Items.APPLE)
                        .input('s', Items.HEAVY_CORE)
                        .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(Items.HEAVY_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "apple_core_crafting_recipe")));
                createShaped(RecipeCategory.MISC, ModBlocks.PUFFERFISH_CORE)
                        .pattern("WWW")
                        .pattern("WsW")
                        .pattern("WWW")
                        .input('W', Items.PUFFERFISH)
                        .input('s', Items.HEAVY_CORE)
                        .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(Items.HEAVY_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "pufferfish_core_crafting_recipe")));
                createShaped(RecipeCategory.MISC, ModBlocks.TNT_CORE)
                        .pattern("WWW")
                        .pattern("WsW")
                        .pattern("WWW")
                        .input('W', Items.TNT)
                        .input('s', Items.HEAVY_CORE)
                        .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(Items.HEAVY_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "tnt_core_crafting_recipe")));
                createShapeless(RecipeCategory.MISC, ModBlocks.COMMAND_BLOCK_CORE)
                        .input(ModItems.WOODEN_MACE)
                        .input(ModItems.UNFINISHED_MACE)
                        .input(ModItems.GAMBLERS_MACE)
                        .input(ModItems.BRASS_BELL_MACE)
                        .input(ModItems.POTION_MACE)
                        .input(ModItems.GROSS_MACE)
                        .input(ModItems.APPLE_MACE)
                        .input(ModItems.PUFFERFISH_MACE)
                        .input(ModItems.TNT_MACE)
                        .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(Items.HEAVY_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "command_block_core_crafting_recipe")));
            }
        };
    }

    @Override
    public String getName() {
        return "Worse Maces Recipes";
    }
}