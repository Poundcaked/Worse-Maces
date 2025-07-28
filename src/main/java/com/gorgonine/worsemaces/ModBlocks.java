package com.gorgonine.worsemaces;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.HeavyCoreBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.spongepowered.include.com.google.common.base.Function;

public class ModBlocks {

    public static final Block WOODEN_CORE = register(
            "wooden_core",
            HeavyCoreBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.OAK_TAN)
                    .instrument(NoteBlockInstrument.BASS)
                    .sounds(BlockSoundGroup.WOOD)
                    .strength(2.0F)
                    .pistonBehavior(PistonBehavior.NORMAL)
                    .burnable(),
            true
    );
    public static final Block UNFINISHED_CORE = register(
            "unfinished_core",
            HeavyCoreBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.IRON_GRAY)
                    .instrument(NoteBlockInstrument.SNARE)
                    .sounds(BlockSoundGroup.HEAVY_CORE)
                    .strength(10.0F)
                    .pistonBehavior(PistonBehavior.NORMAL)
                    .requiresTool()
                    .resistance(1200.0F),
            true
    );
    public static final Block GAMBLERS_CORE = register(
            "gamblers_core",
            HeavyCoreBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.WHITE)
                    .instrument(NoteBlockInstrument.SNARE)
                    .sounds(BlockSoundGroup.HEAVY_CORE)
                    .strength(10.0F)
                    .pistonBehavior(PistonBehavior.NORMAL)
                    .requiresTool()
                    .resistance(1200.0F),
            true
    );
    public static final Block BRASS_BELL_CORE = register(
            "brass_bell_core",
            HeavyCoreBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.GOLD)
                    .instrument(NoteBlockInstrument.BELL)
                    .sounds(BlockSoundGroup.HEAVY_CORE)
                    .strength(10.0F)
                    .pistonBehavior(PistonBehavior.NORMAL)
                    .requiresTool()
                    .resistance(1200.0F),
            true
    );
    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));
        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }
    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(WorseMaces.MOD_ID, name));
    }
    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WorseMaces.MOD_ID, name));
    }

    public static void registerModBlocks() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.WOODEN_CORE);
            entries.add(ModBlocks.UNFINISHED_CORE);
            entries.add(ModBlocks.GAMBLERS_CORE);
        });
    }

}
