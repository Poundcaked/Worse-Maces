package com.gorgonine.worsemaces.client.datagen;

import com.gorgonine.worsemaces.ModBlocks;
import com.gorgonine.worsemaces.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleState(ModBlocks.WOODEN_CORE);
        blockStateModelGenerator.registerSimpleState(ModBlocks.UNFINISHED_CORE);
        blockStateModelGenerator.registerSimpleState(ModBlocks.GAMBLERS_CORE);
        blockStateModelGenerator.registerSimpleState(ModBlocks.POTION_CORE);
        blockStateModelGenerator.registerSimpleState(ModBlocks.GROSS_CORE);
        blockStateModelGenerator.registerSimpleState(ModBlocks.TNT_CORE);
        blockStateModelGenerator.registerSimpleState(ModBlocks.COMMAND_BLOCK_CORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.WOODEN_MACE, Models.HANDHELD_MACE);
        itemModelGenerator.register(ModItems.UNFINISHED_MACE, Models.HANDHELD_MACE);
        itemModelGenerator.register(ModItems.GAMBLERS_MACE, Models.HANDHELD_MACE);
        itemModelGenerator.register(ModItems.BRASS_BELL_MACE, Models.HANDHELD_MACE);
        itemModelGenerator.register(ModItems.POTION_MACE, Models.HANDHELD_MACE);
        itemModelGenerator.register(ModItems.GROSS_MACE, Models.HANDHELD_MACE);
        itemModelGenerator.register(ModItems.APPLE_MACE, Models.HANDHELD_MACE);
        itemModelGenerator.register(ModItems.PUFFERFISH_MACE, Models.HANDHELD_MACE);
        itemModelGenerator.register(ModItems.TNT_MACE, Models.HANDHELD_MACE);
        itemModelGenerator.register(ModItems.COMMAND_BLOCK_MACE, Models.HANDHELD_MACE);
    }
}
