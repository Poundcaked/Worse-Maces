package com.gorgonine.worsemaces.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModEnglishLangProvider extends FabricLanguageProvider {
    public ModEnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("item.worsemaces.wooden_mace", "Wooden Mace");
        translationBuilder.add("item.worsemaces.unfinished_mace", "Unfinished Mace");

        translationBuilder.add("item.worsemaces.wooden_core", "Wooden Core");
        translationBuilder.add("item.worsemaces.unfinished_core", "Unfinished Core");

        translationBuilder.add("itemgroup.worsemaces.maces_group", "Maces Group");
        translationBuilder.add("itemgroup.worsemaces.cores_group", "Core Group");

        translationBuilder.add("effect.worsemaces.splinter", "Splinter");
    }
}
