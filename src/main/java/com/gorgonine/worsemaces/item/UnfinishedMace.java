package com.gorgonine.worsemaces.item;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Random;

public class UnfinishedMace extends BaseMace {
    private static final int ATTACK_DAMAGE_MODIFIER_VALUE = 5;
    private static final float ATTACK_SPEED_MODIFIER_VALUE = -3.4F;
    private static final float HEAVY_SMASH_SOUND_FALL_DISTANCE_THRESHOLD = 5.0F;
    private static final float KNOCKBACK_RANGE = 12.5F;
    private static final float KNOCKBACK_POWER = 4.7F;
    private static final double ABSOLUTE_MINIMUM_FALL_DISTANCE = 1.5F;
    private static final double MAX_MIN_FALL_DISTANCE = 3.0F;
    private static final double MIDDLE_FALL_DISTANCE = 8.0F;

    Random random = new Random();

    public UnfinishedMace(Settings settings) {
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
        return 4.0 * playerFallDistance; //DEFAULT: 4.0 * playerFallDistance
    }

    @Override
    public double getFormulaMiddle(double playerFallDistance) {
        return 12.0 + 2.0 * (playerFallDistance - MAX_MIN_FALL_DISTANCE); //DEFAULT: 12.0 + 2.0 * (playerFallDistance - MAX_MIN_FALL_DISTANCE)
    }

    @Override
    public double getFormulaMax(double playerFallDistance) {
        return 22.0 + playerFallDistance - MIDDLE_FALL_DISTANCE ; //DEFAULT: 22.0 + playerFallDistance - MIDDLE_FALL_DISTANCE
    }

    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        boolean cancelledByRandom = false;
        if (random.nextBoolean()) { //50% chance to not give bonus damage
            cancelledByRandom = true;
        }

        Entity attacker = damageSource.getSource();
        if (attacker instanceof LivingEntity player) {
            if (!shouldDealAdditionalDamage(player) || cancelledByRandom) {
                return 0.0F;
            }else{

                double playerFallDistance = player.fallDistance;
                double calculatedFallDamage;
                if (playerFallDistance <= MAX_MIN_FALL_DISTANCE) {
                    calculatedFallDamage = getFormulaMinimum(playerFallDistance);
                } else if (playerFallDistance <= MIDDLE_FALL_DISTANCE) {
                    calculatedFallDamage = getFormulaMiddle(playerFallDistance);
                } else {
                    calculatedFallDamage = getFormulaMax(playerFallDistance);
                }

                World playerWorld = player.getWorld();
                if (playerWorld instanceof ServerWorld) {
                    ServerWorld serverWorld = (ServerWorld) playerWorld;
                    return (float) (calculatedFallDamage + (double) EnchantmentHelper.getSmashDamagePerFallenBlock(serverWorld, player.getWeaponStack(), target, damageSource, 0.0F) * playerFallDistance);
                } else {
                    return (float) calculatedFallDamage;
                }
            }
        } else {
            return 0.0F;
        }
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {

    }

    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (shouldDealAdditionalDamage(attacker)) {
            ServerWorld serverWorld = (ServerWorld) attacker.getWorld();
            if(random.nextInt(100) <10){
                LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT,serverWorld);
                serverWorld.spawnEntity(lightning);
            }

            if (random.nextBoolean()){
                attacker.setVelocity(attacker.getVelocity().withAxis(Direction.Axis.Y, 0.009999999776482582));
            }else {
                attacker.setVelocity(attacker.getVelocity().withAxis(Direction.Axis.Y, random.nextFloat(-5,5)));
            }

            ServerPlayerEntity serverPlayerEntity;
            if (attacker instanceof ServerPlayerEntity) {
                serverPlayerEntity = (ServerPlayerEntity) attacker;
                serverPlayerEntity.currentExplosionImpactPos = this.getCurrentExplosionImpactPos(serverPlayerEntity);

                boolean b = random.nextBoolean();
                serverPlayerEntity.setIgnoreFallDamageFromCurrentExplosion(b);

                serverPlayerEntity.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(serverPlayerEntity));
            }

            if (target.isOnGround()) {
                if (attacker instanceof ServerPlayerEntity) {
                    serverPlayerEntity = (ServerPlayerEntity) attacker;
                    boolean b = random.nextBoolean();
                    serverPlayerEntity.setSpawnExtraParticlesOnFall(b);
                }
                if (random.nextBoolean()) {
                    SoundEvent soundEvent = attacker.fallDistance > HEAVY_SMASH_SOUND_FALL_DISTANCE_THRESHOLD ? SoundEvents.ITEM_MACE_SMASH_GROUND_HEAVY : SoundEvents.ITEM_MACE_SMASH_GROUND;
                    serverWorld.playSound((Entity) null, attacker.getX(), attacker.getY(), attacker.getZ(), soundEvent, attacker.getSoundCategory(), random.nextFloat(3.0F), random.nextFloat(10.0F));
                }
            } else {
                if (random.nextBoolean()) {
                    serverWorld.playSound((Entity) null, attacker.getX(), attacker.getY(), attacker.getZ(), SoundEvents.ITEM_MACE_SMASH_AIR, attacker.getSoundCategory(), random.nextFloat(3.0F), random.nextFloat(10.0F));
                }
            }
                if (random.nextBoolean()){
                    knockbackNearbyEntities(serverWorld, attacker, target);
                }
            }
    }

}
