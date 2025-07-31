package com.gorgonine.worsemaces.client.datagen;

import com.gorgonine.worsemaces.ModItems;
import com.gorgonine.worsemaces.WorseMaces;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry root = Advancement.Builder.create()
                .display(
                        Items.MACE, // The display icon
                        Text.translatable("advancement.worsemaces.root.title"), // The title
                        Text.translatable("advancement.worsemaces.root.description"), // The description
                        Identifier.of(WorseMaces.MOD_ID,"gui/advancements/backgrounds/mace"), // Background image for the tab in the advancements page, if this is a root advancement (has no parent)
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        false, // Show the toast when completing it
                        false, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                // name referenced by other advancements when they want to have "requirements."
                .criterion("root", TickCriterion.Conditions.createTick())
                // Give the advancement an id
                .build(consumer, getNameId("worsemaces/root"));
        AdvancementEntry getWoodenMace = Advancement.Builder.create()
                .parent(root)
                .display(
                        ModItems.WOODEN_MACE, // The display icon
                        Text.translatable("advancement.worsemaces.wooden_mace.title"), // The title
                        Text.translatable("advancement.worsemaces.wooden_mace.description"), // The description
                        null, // Background image for the tab in the advancements page, if this is a root advancement (has no parent)
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                // name referenced by other advancements when they want to have "requirements."
                .criterion("get_wooden_mace", InventoryChangedCriterion.Conditions.items(ModItems.WOODEN_MACE))
                // Give the advancement an id
                .build(consumer, getNameId("worsemaces/get_wooden_mace"));

        AdvancementEntry getUnfinishedMace = Advancement.Builder.create()
                .parent(getWoodenMace)
                .display(
                        ModItems.UNFINISHED_MACE, // The display icon
                        Text.translatable("advancement.worsemaces.unfinished_mace.title"), // The title
                        Text.translatable("advancement.worsemaces.unfinished_mace.description"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                // name referenced by other advancements when they want to have "requirements."
                .criterion("get_unfinished_mace", InventoryChangedCriterion.Conditions.items(ModItems.UNFINISHED_MACE))
                // Give the advancement an id
                .build(consumer, getNameId("worsemaces/get_unfinished_mace"));
        AdvancementEntry getGamblersMace = Advancement.Builder.create()
                .parent(getUnfinishedMace)
                .display(
                        ModItems.GAMBLERS_MACE, // The display icon
                        Text.translatable("advancement.worsemaces.gamblers_mace.title"), // The title
                        Text.translatable("advancement.worsemaces.gamblers_mace.description"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                // name referenced by other advancements when they want to have "requirements."
                .criterion("get_gamblers_mace", InventoryChangedCriterion.Conditions.items(ModItems.GAMBLERS_MACE))
                // Give the advancement an id
                .build(consumer, getNameId("worsemaces/get_gamblers_mace"));
        AdvancementEntry getBrassBellMace = Advancement.Builder.create()
                .parent(getGamblersMace)
                .display(
                        ModItems.BRASS_BELL_MACE, // The display icon
                        Text.translatable("advancement.worsemaces.brass_bell_mace.title"), // The title
                        Text.translatable("advancement.worsemaces.brass_bell_mace.description"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                // name referenced by other advancements when they want to have "requirements."
                .criterion("get_brass_bell_mace", InventoryChangedCriterion.Conditions.items(ModItems.BRASS_BELL_MACE))
                // Give the advancement an id
                .build(consumer, getNameId("worsemaces/get_brass_bell_mace"));
        AdvancementEntry getPotionMace = Advancement.Builder.create()
                .parent(getBrassBellMace)
                .display(
                        ModItems.POTION_MACE, // The display icon
                        Text.translatable("advancement.worsemaces.potion_mace.title"), // The title
                        Text.translatable("advancement.worsemaces.potion_mace.description"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                // name referenced by other advancements when they want to have "requirements."
                .criterion("get_potion_mace", InventoryChangedCriterion.Conditions.items(ModItems.POTION_MACE))
                // Give the advancement an id
                .build(consumer, getNameId("worsemaces/get_potion_mace"));
        AdvancementEntry getGrossMace = Advancement.Builder.create()
                .parent(getPotionMace)
                .display(
                        ModItems.GROSS_MACE, // The display icon
                        Text.translatable("advancement.worsemaces.gross_mace.title"), // The title
                        Text.translatable("advancement.worsemaces.gross_mace.description"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                // name referenced by other advancements when they want to have "requirements."
                .criterion("get_gross_mace", InventoryChangedCriterion.Conditions.items(ModItems.GROSS_MACE))
                // Give the advancement an id
                .build(consumer, getNameId("worsemaces/get_gross_mace"));
        AdvancementEntry getAppleMace = Advancement.Builder.create()
                .parent(getGrossMace)
                .display(
                        ModItems.APPLE_MACE, // The display icon
                        Text.translatable("advancement.worsemaces.apple_mace.title"), // The title
                        Text.translatable("advancement.worsemaces.apple_mace.description"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                // name referenced by other advancements when they want to have "requirements."
                .criterion("get_apple_mace", InventoryChangedCriterion.Conditions.items(ModItems.APPLE_MACE))
                // Give the advancement an id
                .build(consumer, getNameId("worsemaces/get_apple_mace"));
        AdvancementEntry getPufferfishMace = Advancement.Builder.create()
                .parent(getAppleMace)
                .display(
                        ModItems.PUFFERFISH_MACE, // The display icon
                        Text.translatable("advancement.worsemaces.pufferfish_mace.title"), // The title
                        Text.translatable("advancement.worsemaces.pufferfish_mace.description"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                // name referenced by other advancements when they want to have "requirements."
                .criterion("get_pufferfish_mace", InventoryChangedCriterion.Conditions.items(ModItems.PUFFERFISH_MACE))
                // Give the advancement an id
                .build(consumer, getNameId("worsemaces/get_pufferfish_mace"));
        AdvancementEntry getTNTMace = Advancement.Builder.create()
                .parent(getPufferfishMace)
                .display(
                        ModItems.TNT_MACE, // The display icon
                        Text.translatable("advancement.worsemaces.tnt_mace.title"), // The title
                        Text.translatable("advancement.worsemaces.tnt_mace.description"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                // name referenced by other advancements when they want to have "requirements."
                .criterion("get_tnt_mace", InventoryChangedCriterion.Conditions.items(ModItems.TNT_MACE))
                // Give the advancement an id
                .build(consumer, getNameId("worsemaces/get_tnt_mace"));
        AdvancementEntry getCommandBlockMace = Advancement.Builder.create()
                .parent(getTNTMace)
                .display(
                        ModItems.COMMAND_BLOCK_MACE, // The display icon
                        Text.translatable("advancement.worsemaces.command_block_mace.title"), // The title
                        Text.translatable("advancement.worsemaces.command_block_mace.description"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                // name referenced by other advancements when they want to have "requirements."
                .criterion("get_command_block_mace", InventoryChangedCriterion.Conditions.items(ModItems.COMMAND_BLOCK_MACE))
                // Give the advancement an id
                .build(consumer, getNameId("worsemaces/get_command_block_mace"));
    }

    private String getNameId(String id) {
        return WorseMaces.MOD_ID + ":" + id;
    }
}
