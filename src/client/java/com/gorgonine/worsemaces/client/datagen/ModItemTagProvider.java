package com.gorgonine.worsemaces.client.datagen;

import com.gorgonine.worsemaces.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(ItemTags.MACE_ENCHANTABLE)
                .add(ModItems.WOODEN_MACE)
                .add(ModItems.UNFINISHED_MACE)
                .add(ModItems.GAMBLERS_MACE)
                .add(ModItems.BRASS_BELL_MACE)
                .add(ModItems.POTION_MACE)
                .add(ModItems.GROSS_MACE)
                .add(ModItems.APPLE_MACE)
                .add(ModItems.PUFFERFISH_MACE)
                .add(ModItems.TNT_MACE)
                .add(ModItems.COMMAND_BLOCK_MACE);
        valueLookupBuilder(ItemTags.FIRE_ASPECT_ENCHANTABLE)
                .add(ModItems.WOODEN_MACE)
                .add(ModItems.UNFINISHED_MACE)
                .add(ModItems.GAMBLERS_MACE)
                .add(ModItems.BRASS_BELL_MACE)
                .add(ModItems.POTION_MACE)
                .add(ModItems.GROSS_MACE)
                .add(ModItems.APPLE_MACE)
                .add(ModItems.PUFFERFISH_MACE)
                .add(ModItems.TNT_MACE)
                .add(ModItems.COMMAND_BLOCK_MACE);
        valueLookupBuilder(ItemTags.WEAPON_ENCHANTABLE)
                .add(ModItems.WOODEN_MACE)
                .add(ModItems.UNFINISHED_MACE)
                .add(ModItems.GAMBLERS_MACE)
                .add(ModItems.BRASS_BELL_MACE)
                .add(ModItems.POTION_MACE)
                .add(ModItems.GROSS_MACE)
                .add(ModItems.APPLE_MACE)
                .add(ModItems.PUFFERFISH_MACE)
                .add(ModItems.TNT_MACE)
                .add(ModItems.COMMAND_BLOCK_MACE);
        valueLookupBuilder(ItemTags.MACE_ENCHANTABLE)
                .add(ModItems.WOODEN_MACE)
                .add(ModItems.UNFINISHED_MACE)
                .add(ModItems.GAMBLERS_MACE)
                .add(ModItems.BRASS_BELL_MACE)
                .add(ModItems.POTION_MACE)
                .add(ModItems.GROSS_MACE)
                .add(ModItems.APPLE_MACE)
                .add(ModItems.PUFFERFISH_MACE)
                .add(ModItems.TNT_MACE)
                .add(ModItems.COMMAND_BLOCK_MACE);
        valueLookupBuilder(ItemTags.BREAKS_DECORATED_POTS)
                .add(ModItems.WOODEN_MACE)
                .add(ModItems.UNFINISHED_MACE)
                .add(ModItems.GAMBLERS_MACE)
                .add(ModItems.BRASS_BELL_MACE)
                .add(ModItems.POTION_MACE)
                .add(ModItems.GROSS_MACE)
                .add(ModItems.APPLE_MACE)
                .add(ModItems.PUFFERFISH_MACE)
                .add(ModItems.TNT_MACE)
                .add(ModItems.COMMAND_BLOCK_MACE);
        valueLookupBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.WOODEN_MACE)
                .add(ModItems.UNFINISHED_MACE)
                .add(ModItems.GAMBLERS_MACE)
                .add(ModItems.BRASS_BELL_MACE)
                .add(ModItems.POTION_MACE)
                .add(ModItems.GROSS_MACE)
                .add(ModItems.APPLE_MACE)
                .add(ModItems.PUFFERFISH_MACE)
                .add(ModItems.TNT_MACE)
                .add(ModItems.COMMAND_BLOCK_MACE);
    }
}
