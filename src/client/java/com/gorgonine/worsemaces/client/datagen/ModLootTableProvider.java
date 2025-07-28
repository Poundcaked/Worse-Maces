package com.gorgonine.worsemaces.client.datagen;

import com.gorgonine.worsemaces.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.WOODEN_CORE);
        addDrop(ModBlocks.UNFINISHED_CORE);
        addDrop(ModBlocks.GAMBLERS_CORE);
        addDrop(ModBlocks.BRASS_BELL_CORE);
    }
}
