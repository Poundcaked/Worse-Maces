package com.gorgonine.worsemaces.client;

import com.gorgonine.worsemaces.ModBlocks;
import com.gorgonine.worsemaces.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.text.Text;

public class WorseMacesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(ModBlocks.POTION_CORE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.APPLE_CORE, BlockRenderLayer.CUTOUT);

        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
            if (itemStack.isOf(ModItems.WOODEN_MACE)) {
                list.add(1,Text.literal(""));
                list.add(2,Text.translatable("item.worsemaces.wooden_mace.tooltip.1"));
                list.add(3,Text.translatable("item.worsemaces.wooden_mace.tooltip.2"));
            }
            if (itemStack.isOf(ModItems.UNFINISHED_MACE)) {
                list.add(1,Text.literal(""));
                list.add(2,Text.translatable("item.worsemaces.unfinished_mace.tooltip.1"));
                list.add(3,Text.translatable("item.worsemaces.unfinished_mace.tooltip.2"));
            }
            if (itemStack.isOf(ModItems.GAMBLERS_MACE)) {
                list.add(1,Text.literal(""));
                list.add(2,Text.translatable("item.worsemaces.gamblers_mace.tooltip.1"));
                list.add(3,Text.translatable("item.worsemaces.gamblers_mace.tooltip.2"));
            }
            if (itemStack.isOf(ModItems.BRASS_BELL_MACE)) {
                list.add(1,Text.literal(""));
                list.add(2,Text.translatable("item.worsemaces.brass_bell_mace.tooltip.1"));
                list.add(3,Text.translatable("item.worsemaces.brass_bell_mace.tooltip.2"));
            }
            if (itemStack.isOf(ModItems.POTION_MACE)) {
                list.add(1,Text.literal(""));
                list.add(2,Text.translatable("item.worsemaces.potion_mace.tooltip.1"));
                list.add(3,Text.translatable("item.worsemaces.potion_mace.tooltip.2"));
            }
            if (itemStack.isOf(ModItems.GROSS_MACE)) {
                list.add(1,Text.literal(""));
                list.add(2,Text.translatable("item.worsemaces.gross_mace.tooltip.1"));
                list.add(3,Text.translatable("item.worsemaces.gross_mace.tooltip.2"));
            }
            if (itemStack.isOf(ModItems.APPLE_MACE)) {
                list.add(1,Text.literal(""));
                list.add(2,Text.translatable("item.worsemaces.apple_mace.tooltip.1"));
                list.add(3,Text.translatable("item.worsemaces.apple_mace.tooltip.2"));
            }
            if (itemStack.isOf(ModItems.PUFFERFISH_MACE)) {
                list.add(1,Text.literal(""));
                list.add(2,Text.translatable("item.worsemaces.pufferfish_mace.tooltip.1"));
                list.add(3,Text.translatable("item.worsemaces.pufferfish_mace.tooltip.2"));
            }


        });
    }
}
