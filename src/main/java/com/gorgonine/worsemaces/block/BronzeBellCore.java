package com.gorgonine.worsemaces.block;

import com.gorgonine.worsemaces.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.HeavyCoreBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BronzeBellCore extends HeavyCoreBlock{
    public BronzeBellCore(Settings settings) {
        super(settings);
    }

    Random random = new Random();

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        world.playSound(null,pos, ModSounds.bell_hit, SoundCategory.BLOCKS,1.0F, random.nextFloat(2.0F));
        world.playSound(null,pos, ModSounds.tinnitus, SoundCategory.BLOCKS,0.15F, 1.0F);
        return ActionResult.SUCCESS;
    }
}
