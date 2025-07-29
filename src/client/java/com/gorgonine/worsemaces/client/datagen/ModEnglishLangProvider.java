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
        translationBuilder.add("item.worsemaces.gamblers_mace", "Gambler's Mace");
        translationBuilder.add("item.worsemaces.brass_bell_mace", "Brass Bell Mace");
        translationBuilder.add("item.worsemaces.potion_mace", "Potion Mace");
        translationBuilder.add("item.worsemaces.gross_mace", "Gross Mace");
        translationBuilder.add("item.worsemaces.apple_mace", "Apple Mace");
        translationBuilder.add("item.worsemaces.pufferfish_mace", "Pufferfish Mace");

        translationBuilder.add("item.worsemaces.wooden_core", "Wooden Core");
        translationBuilder.add("item.worsemaces.unfinished_core", "Unfinished Core");
        translationBuilder.add("item.worsemaces.gamblers_core", "Gambler's Core");
        translationBuilder.add("item.worsemaces.brass_bell_core", "Bell Core");
        translationBuilder.add("item.worsemaces.potion_core", "Potion Core");
        translationBuilder.add("item.worsemaces.gross_core", "Gross Core");
        translationBuilder.add("item.worsemaces.apple_core", "Apple Core");
        translationBuilder.add("item.worsemaces.pufferfish_core", "Pufferfish Core");

        translationBuilder.add("itemgroup.worsemaces.maces_group", "Maces Group");
        translationBuilder.add("itemgroup.worsemaces.cores_group", "Core Group");

        translationBuilder.add("effect.worsemaces.splinter", "Splinter");

        translationBuilder.add("sounds.worsemaces.pufferfish_noise", "Pufferfish Noise");
        translationBuilder.add("sounds.worsemaces.tinnitus","*Tinnitus*");
        translationBuilder.add("sounds.worsemaces.bell_hit", "Bell Rings");

        translationBuilder.add("item.worsemaces.wooden_mace.tooltip.1","A mace made of wood");
        translationBuilder.add("item.worsemaces.wooden_mace.tooltip.2","Not very powerful or durable");

        translationBuilder.add("item.worsemaces.unfinished_mace.tooltip.1","A mace that has random chances of doing");
        translationBuilder.add("item.worsemaces.unfinished_mace.tooltip.2","any given thing a mace normally does");

        translationBuilder.add("item.worsemaces.gamblers_mace.tooltip.1","A mace that can be twice as good as");
        translationBuilder.add("item.worsemaces.gamblers_mace.tooltip.2","a normal one, but usually isn't");

        translationBuilder.add("item.worsemaces.brass_bell_mace.tooltip.1","A loud mace that stuns enemies");
        translationBuilder.add("item.worsemaces.brass_bell_mace.tooltip.2","and knocks them back far");

        translationBuilder.add("item.worsemaces.potion_mace.tooltip.1","Hitting a mob gives it a random");
        translationBuilder.add("item.worsemaces.potion_mace.tooltip.2","negative potion effect");

        translationBuilder.add("item.worsemaces.gross_mace.tooltip.1","EW! Can turn blocks into slime");
        translationBuilder.add("item.worsemaces.gross_mace.tooltip.2","and summon slimes on a smash attack");

        translationBuilder.add("item.worsemaces.apple_mace.tooltip.1","Creates a tree upon killing a mob");
        translationBuilder.add("item.worsemaces.apple_mace.tooltip.2","Also spawns a few apples when a mob is hit with a smash");

        translationBuilder.add("item.worsemaces.pufferfish_mace.tooltip.1","Creates a cloud of poison when used in a smash attack");
        translationBuilder.add("item.worsemaces.pufferfish_mace.tooltip.2","Also poisons its target and nearby enemies");
    }
}
