package com.gorgonine.worsemaces;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup MACES_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(WorseMaces.MOD_ID, "maces_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.WOODEN_MACE))
                    .displayName(Text.translatable("itemgroup.worsemaces.maces_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.WOODEN_MACE);
                        entries.add(ModItems.UNFINISHED_MACE);
                        entries.add(ModItems.GAMBLERS_MACE);
                        entries.add(ModItems.BRASS_BELL_MACE);
                        entries.add(ModItems.POTION_MACE);
                        entries.add(ModItems.GROSS_MACE);
                        entries.add(ModItems.APPLE_MACE);
                        entries.add(ModItems.PUFFERFISH_MACE);
                        entries.add(ModItems.TNT_MACE);
                        entries.add(ModItems.COMMAND_BLOCK_MACE);
                    }).build());

    public static final ItemGroup CORES_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(WorseMaces.MOD_ID, "cores_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.WOODEN_CORE))
                    .displayName(Text.translatable("itemgroup.worsemaces.cores_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.WOODEN_CORE);
                        entries.add(ModBlocks.UNFINISHED_CORE);
                        entries.add(ModBlocks.GAMBLERS_CORE);
                        entries.add(ModBlocks.BRASS_BELL_CORE);
                        entries.add(ModBlocks.POTION_CORE);
                        entries.add(ModBlocks.GROSS_CORE);
                        entries.add(ModBlocks.APPLE_CORE);
                        entries.add(ModBlocks.PUFFERFISH_CORE);
                        entries.add(ModBlocks.TNT_CORE);
                        entries.add(ModBlocks.COMMAND_BLOCK_CORE);
                    }).build());


    public static void registerItemGroups() {
    }
}
