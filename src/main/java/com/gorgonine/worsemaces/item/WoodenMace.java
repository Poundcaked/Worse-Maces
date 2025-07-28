package com.gorgonine.worsemaces.item;

import com.gorgonine.worsemaces.ModEffects;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Direction;

public class WoodenMace extends BaseMace {
    private static final int ATTACK_DAMAGE_MODIFIER_VALUE = 2;
    private static final float ATTACK_SPEED_MODIFIER_VALUE = -3.1F;
    private static final float HEAVY_SMASH_SOUND_FALL_DISTANCE_THRESHOLD = 5.0F;
    private static final float KNOCKBACK_RANGE = 3.5F;
    private static final float KNOCKBACK_POWER = 0.7F;
    private static final double ABSOLUTE_MINIMUM_FALL_DISTANCE = 1.5F;
    private static final double MAX_MIN_FALL_DISTANCE = 3.0F;
    private static final double MIDDLE_FALL_DISTANCE = 8.0F;

    public WoodenMace(Item.Settings settings) {
        super(settings);
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder().add(
                        EntityAttributes.ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                ATTACK_DAMAGE_MODIFIER_VALUE,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(EntityAttributes.ATTACK_SPEED,
                        new EntityAttributeModifier(
                                BASE_ATTACK_SPEED_MODIFIER_ID,
                                ATTACK_SPEED_MODIFIER_VALUE,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ), AttributeModifierSlot.MAINHAND).build();
    }

    @Override
    public double getFormulaMinimum(double playerFallDistance) {
        return playerFallDistance/2; //DEFAULT: 4.0 * playerFallDistance
    }

    @Override
    public double getFormulaMiddle(double playerFallDistance) {
        return playerFallDistance; //DEFAULT: 12.0 + 2.0 * (playerFallDistance - MAX_MIN_FALL_DISTANCE)
    }

    @Override
    public double getFormulaMax(double playerFallDistance) {
        return playerFallDistance * 1.5; //DEFAULT: 22.0 + playerFallDistance - MIDDLE_FALL_DISTANCE
    }

    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        var instance = new StatusEffectInstance(ModEffects.SPLINTER, 4 * 20, 0, false, true, true);
        target.addStatusEffect(instance);

        if (shouldDealAdditionalDamage(attacker)) {
            ServerWorld serverWorld = (ServerWorld)attacker.getWorld();
            attacker.setVelocity(attacker.getVelocity().withAxis(Direction.Axis.Y, 0.009999999776482582));
            ServerPlayerEntity serverPlayerEntity;

            if (attacker instanceof ServerPlayerEntity) {
                serverPlayerEntity = (ServerPlayerEntity)attacker;
                serverPlayerEntity.currentExplosionImpactPos = this.getCurrentExplosionImpactPos(serverPlayerEntity);
                serverPlayerEntity.setIgnoreFallDamageFromCurrentExplosion(true);
                serverPlayerEntity.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(serverPlayerEntity));
            }

            if (target.isOnGround()) {
                if (attacker instanceof ServerPlayerEntity) {
                    serverPlayerEntity = (ServerPlayerEntity)attacker;
                    serverPlayerEntity.setSpawnExtraParticlesOnFall(true);
                }

                SoundEvent soundEvent = attacker.fallDistance > HEAVY_SMASH_SOUND_FALL_DISTANCE_THRESHOLD ? SoundEvents.ITEM_MACE_SMASH_GROUND_HEAVY : SoundEvents.ITEM_MACE_SMASH_GROUND;
                serverWorld.playSound((Entity)null, attacker.getX(), attacker.getY(), attacker.getZ(), soundEvent, attacker.getSoundCategory(), 1.0F, 1.0F);
            } else {
                serverWorld.playSound((Entity)null, attacker.getX(), attacker.getY(), attacker.getZ(), SoundEvents.ITEM_MACE_SMASH_AIR, attacker.getSoundCategory(), 1.0F, 1.0F);
            }

            knockbackNearbyEntities(serverWorld, attacker, target);
        }

    }
}
