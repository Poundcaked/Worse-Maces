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
        translationBuilder.add("item.worsemaces.tnt_mace", "TNT Mace");
        translationBuilder.add("item.worsemaces.command_block_mace", "Command Block Mace");

        translationBuilder.add("item.worsemaces.wooden_core", "Wooden Core");
        translationBuilder.add("item.worsemaces.unfinished_core", "Unfinished Core");
        translationBuilder.add("item.worsemaces.gamblers_core", "Gambler's Core");
        translationBuilder.add("item.worsemaces.brass_bell_core", "Bell Core");
        translationBuilder.add("item.worsemaces.potion_core", "Potion Core");
        translationBuilder.add("item.worsemaces.gross_core", "Gross Core");
        translationBuilder.add("item.worsemaces.apple_core", "Apple Core");
        translationBuilder.add("item.worsemaces.pufferfish_core", "Pufferfish Core");
        translationBuilder.add("item.worsemaces.tnt_core", "TNT Core");
        translationBuilder.add("item.worsemaces.command_block_core", "Command Block Core");

        translationBuilder.add("itemgroup.worsemaces.maces_group", "Maces");
        translationBuilder.add("itemgroup.worsemaces.cores_group", "Cores");

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

        translationBuilder.add("item.worsemaces.tnt_mace.tooltip.1","Right click on a block to launch upwards using tnt");
        translationBuilder.add("item.worsemaces.tnt_mace.tooltip.2","Landing a smash spawns in TNT and killing an enemy with a smash spawns a giant explosion");

        translationBuilder.add("item.worsemaces.command_block_mace.tooltip.1","Smash an enemy to run a random command");
        translationBuilder.add("item.worsemaces.command_block_mace.tooltip.2","Right click a block to place a random block");
        translationBuilder.add("item.worsemaces.command_block_mace.tooltip.3","Right click the air to launch a random mob");

        translationBuilder.add("advancement.worsemaces.root.title","Worse Maces");
        translationBuilder.add("advancement.worsemaces.root.description","They're not very good");

        translationBuilder.add("advancement.worsemaces.wooden_mace.title","Baby's First Mace");
        translationBuilder.add("advancement.worsemaces.wooden_mace.description","Obtain a Wooden Mace");
        translationBuilder.add("advancement.worsemaces.unfinished_mace.title","Gt n Unfinis Mae");
        translationBuilder.add("advancement.worsemaces.unfinished_mace.description","Smash your heavy core to bits to make it incomplete, then craft it into a mace");
        translationBuilder.add("advancement.worsemaces.gamblers_mace.title","All on Black");
        translationBuilder.add("advancement.worsemaces.gamblers_mace.description","Gamble your wither skeleton skulls away to make a hopeful mace");
        translationBuilder.add("advancement.worsemaces.brass_bell_mace.title","Ringing Ears");
        translationBuilder.add("advancement.worsemaces.brass_bell_mace.description","Use 5 Bells and a heavy core to make a really loud mace");
        translationBuilder.add("advancement.worsemaces.potion_mace.title","Heavy Brewery");
        translationBuilder.add("advancement.worsemaces.potion_mace.description","Use any potion to create an effective mace");
        translationBuilder.add("advancement.worsemaces.gross_mace.title","Chopped Cheese");
        translationBuilder.add("advancement.worsemaces.gross_mace.description","Use the grossest things in Minecraft to make a perfectly good heavy core into a gross mess");
        translationBuilder.add("advancement.worsemaces.apple_mace.title","Keeping the Doctor Away");
        translationBuilder.add("advancement.worsemaces.apple_mace.description","Use your harvest to create the Greenest Mace");
        translationBuilder.add("advancement.worsemaces.pufferfish_mace.title","Blow Up");
        translationBuilder.add("advancement.worsemaces.pufferfish_mace.description","Destroy the pufferfish population to create a poisonous mace");
        translationBuilder.add("advancement.worsemaces.tnt_mace.title","Now I Have Become Death");
        translationBuilder.add("advancement.worsemaces.tnt_mace.description","Create, possibly, the best mace of the worst maces");
        translationBuilder.add("advancement.worsemaces.command_block_mace.title","Zenith");
        translationBuilder.add("advancement.worsemaces.command_block_mace.description","Combine all your maces to create the worst mace");
    }
}
