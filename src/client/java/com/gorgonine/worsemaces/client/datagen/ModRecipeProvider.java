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
                createShaped(RecipeCategory.MISC, ModItems.WOODEN_MACE)
                        .pattern(" C ")
                        .pattern(" s ")
                        .pattern("   ")
                        .input('C', ModBlocks.WOODEN_CORE)
                        .input('s', Items.STICK)
                        .criterion(hasItem(ModBlocks.WOODEN_CORE), conditionsFromItem(ModBlocks.WOODEN_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "wooden_mace_crafting_recipe_1")));
                createShaped(RecipeCategory.MISC, ModItems.UNFINISHED_MACE)
                        .pattern(" C ")
                        .pattern(" s ")
                        .pattern("   ")
                        .input('C', ModBlocks.UNFINISHED_CORE)
                        .input('s', Items.STICK)
                        .criterion(hasItem(ModBlocks.UNFINISHED_CORE), conditionsFromItem(ModBlocks.UNFINISHED_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "unfinished_mace_crafting_recipe_1")));
                createShaped(RecipeCategory.MISC, ModItems.GAMBLERS_MACE)
                        .pattern(" C ")
                        .pattern(" s ")
                        .pattern("   ")
                        .input('C', ModBlocks.GAMBLERS_CORE)
                        .input('s', Items.BREEZE_ROD)
                        .criterion(hasItem(ModBlocks.GAMBLERS_CORE), conditionsFromItem(ModBlocks.GAMBLERS_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "gamblers_mace_crafting_recipe_1")));
                createShaped(RecipeCategory.MISC, ModItems.BRASS_BELL_MACE)
                        .pattern(" C ")
                        .pattern(" s ")
                        .pattern("   ")
                        .input('C', ModBlocks.BRASS_BELL_CORE)
                        .input('s', Items.IRON_INGOT)
                        .criterion(hasItem(ModBlocks.BRASS_BELL_CORE), conditionsFromItem(ModBlocks.BRASS_BELL_CORE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "brass_bell_mace_crafting_recipe_1")));

                //CORES
                createShaped(RecipeCategory.MISC, ModBlocks.WOODEN_CORE)
                        .pattern("WWW")
                        .pattern("WWW")
                        .pattern("WWW")
                        .input('W', ItemTags.LOGS)
                        .criterion(hasItem(Items.OAK_LOG), conditionsFromItem(Items.OAK_LOG))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "wooden_core_crafting_recipe")));

                createShaped(RecipeCategory.MISC, ModBlocks.WOODEN_CORE)
                        .pattern("sWs")
                        .pattern("WsW")
                        .pattern("sWs")
                        .input('W', Items.WHITE_CONCRETE)
                        .input('s', Items.WITHER_SKELETON_SKULL)
                        .criterion(hasItem(Items.WITHER_SKELETON_SKULL), conditionsFromItem(Items.WITHER_SKELETON_SKULL))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(WorseMaces.MOD_ID, "gamblers_core_crafting_recipe")));
            }
        };
    }

    @Override
    public String getName() {
        return "Worse Maces Recipes";
    }
}