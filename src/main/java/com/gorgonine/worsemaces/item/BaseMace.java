package com.gorgonine.worsemaces.item;

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
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public abstract class BaseMace extends Item {
    private static int ATTACK_DAMAGE_MODIFIER_VALUE = 5;
    private static float ATTACK_SPEED_MODIFIER_VALUE = -3.4F;
    private static float HEAVY_SMASH_SOUND_FALL_DISTANCE_THRESHOLD = 5.0F;
    public static  float KNOCKBACK_RANGE = 3.5F;
    private static float KNOCKBACK_POWER = 0.7F;
    private static double ABSOLUTE_MINIMUM_FALL_DISTANCE = 1.5F;
    private static double MAX_MIN_FALL_DISTANCE = 3.0F;
    private static  double MIDDLE_FALL_DISTANCE = 8.0F;

    public BaseMace(Settings settings) {
        super(settings);
    }

//    public static AttributeModifiersComponent createAttributeModifiers() {
//        return AttributeModifiersComponent.builder().add(
//                EntityAttributes.ATTACK_DAMAGE,
//                new EntityAttributeModifier(
//                        BASE_ATTACK_DAMAGE_MODIFIER_ID,
//                        ATTACK_DAMAGE_MODIFIER_VALUE,
//                        EntityAttributeModifier.Operation.ADD_VALUE
//                ),
//                AttributeModifierSlot.MAINHAND
//        )
//                .add(EntityAttributes.ATTACK_SPEED,
//                    new EntityAttributeModifier(
//                            BASE_ATTACK_SPEED_MODIFIER_ID,
//                            ATTACK_SPEED_MODIFIER_VALUE,
//                            EntityAttributeModifier.Operation.ADD_VALUE
//                    ), AttributeModifierSlot.MAINHAND).build();
//    }

    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), 1.0F, 2, false);
    }

    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
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

    Vec3d getCurrentExplosionImpactPos(ServerPlayerEntity player) {
        return player.shouldIgnoreFallDamageFromCurrentExplosion() && player.currentExplosionImpactPos != null && player.currentExplosionImpactPos.y <= player.getPos().y ? player.currentExplosionImpactPos : player.getPos();
    }

    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (shouldDealAdditionalDamage(attacker)) {
            attacker.onLanding();
        }

    }


    public double getFormulaMinimum(double playerFallDistance){
        return 4.0 * playerFallDistance;
    }
    public double getFormulaMiddle(double playerFallDistance){
        return 12.0 + 2.0 * (playerFallDistance - MAX_MIN_FALL_DISTANCE);
    }
    public double getFormulaMax(double playerFallDistance){
        return 22.0 + playerFallDistance - MIDDLE_FALL_DISTANCE;
    }

    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        Entity entity = damageSource.getSource();
        if (entity instanceof LivingEntity player) {
            if (!shouldDealAdditionalDamage(player)) {
                return 0.0F;
            } else {

                double playerFallDistance = player.fallDistance;
                double calculatedFallDamage;
                if (playerFallDistance <= MAX_MIN_FALL_DISTANCE) { //If player fell less than this distance, set their additional damage to 4 * their fall. i.e. if fell 3, damage is 12
                    calculatedFallDamage = getFormulaMinimum(playerFallDistance);
                } else if (playerFallDistance <= MIDDLE_FALL_DISTANCE) { // Between 3 - 8, Damage will be 12 + 2 * (fall distance - 3). i.e. if fell 6, 18
                    calculatedFallDamage = getFormulaMiddle(playerFallDistance);
                } else { //otherwise, do 22+ fall-8. i.e. if fall was 6, damage is 20
                    calculatedFallDamage = getFormulaMax(playerFallDistance);
                }

                World playerWorld = player.getWorld();
                if (playerWorld instanceof ServerWorld) {
                    ServerWorld serverWorld = (ServerWorld) playerWorld;
                    return (float)(calculatedFallDamage + (double) EnchantmentHelper.getSmashDamagePerFallenBlock(serverWorld, player.getWeaponStack(), target, damageSource, 0.0F) * playerFallDistance);
                } else {
                    return (float) calculatedFallDamage;
                }
            }
        } else {
            return 0.0F;
        }
    }

    static void knockbackNearbyEntities(World world, Entity attacker, Entity attacked) {
        world.syncWorldEvent(2013, attacked.getSteppingPos(), 750);
        world.getEntitiesByClass(LivingEntity.class, attacked.getBoundingBox().expand(KNOCKBACK_RANGE), getKnockbackPredicate(attacker, attacked)).forEach((entity) -> {
            Vec3d vec3d = entity.getPos().subtract(attacked.getPos());
            double d = getKnockback(attacker, entity, vec3d);
            Vec3d vec3d2 = vec3d.normalize().multiply(d);
            if (d > 0.0) {
                entity.addVelocity(vec3d2.x, KNOCKBACK_POWER, vec3d2.z);
                if (entity instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)entity;
                    serverPlayerEntity.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(serverPlayerEntity));
                }
            }

        });
    }

    private static Predicate<LivingEntity> getKnockbackPredicate(Entity attacker, Entity attacked) {
        return (entity) -> {
            boolean isNotSpectator;
            boolean isNotAttackerOrVictim;
            boolean isNotTeammate;
            boolean petIsOwnedByAttacker;
            tamedPetOwnershipCheck: {
                isNotSpectator = !entity.isSpectator();
                isNotAttackerOrVictim = entity != attacker && entity != attacked;
                isNotTeammate = !attacker.isTeammate(entity);
                if (entity instanceof TameableEntity tameableEntity) {
                    if (attacked instanceof LivingEntity livingEntity) {
                        if (tameableEntity.isTamed() && tameableEntity.isOwner(livingEntity)) {
                            petIsOwnedByAttacker = true;
                            break tamedPetOwnershipCheck;
                        }
                    }
                }

                petIsOwnedByAttacker = false;
            }

            boolean isNotOwnedTamedPet;
            armorStandMarkerCheck: {
                isNotOwnedTamedPet = !petIsOwnedByAttacker;
                if (entity instanceof ArmorStandEntity armorStandEntity) {
                    if (armorStandEntity.isMarker()) {
                        petIsOwnedByAttacker = false;
                        break armorStandMarkerCheck;
                    }
                }

                petIsOwnedByAttacker = true;
            }

            boolean isValidArmorStand = petIsOwnedByAttacker;
            boolean isInRange = attacked.squaredDistanceTo(entity) <= Math.pow(3.5, 2.0);
            return isNotSpectator && isNotAttackerOrVictim && isNotTeammate && isNotOwnedTamedPet && isValidArmorStand && isInRange;
        };
    }

    private static double getKnockback(Entity attacker, LivingEntity attacked, Vec3d distance) {
        return (3.5 - distance.length()) * 0.699999988079071 * (double)(attacker.fallDistance > 5.0 ? 2 : 1) * (1.0 - attacked.getAttributeValue(EntityAttributes.KNOCKBACK_RESISTANCE));
    }

    public static boolean shouldDealAdditionalDamage(LivingEntity attacker) {
        return attacker.fallDistance > ABSOLUTE_MINIMUM_FALL_DISTANCE && !attacker.isGliding();
    }

    @Nullable
    public DamageSource getDamageSource(LivingEntity user) {
        return shouldDealAdditionalDamage(user) ? user.getDamageSources().maceSmash(user) : super.getDamageSource(user);
    }
}
