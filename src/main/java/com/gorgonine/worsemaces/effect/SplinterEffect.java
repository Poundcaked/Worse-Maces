package com.gorgonine.worsemaces.effect;

import com.gorgonine.worsemaces.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class SplinterEffect extends StatusEffect {
    public SplinterEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if(!entity.getMovement().equals(Vec3d.ZERO)){
            entity.damage(world, entity.getDamageSources().generic(), 1.0F);
            return true;
        }

        return super.applyUpdateEffect(world, entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
