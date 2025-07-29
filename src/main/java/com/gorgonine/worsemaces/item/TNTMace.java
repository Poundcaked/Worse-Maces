package com.gorgonine.worsemaces.item;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class TNTMace extends Item {
    private static int ATTACK_DAMAGE_MODIFIER_VALUE = 5;
    private static float ATTACK_SPEED_MODIFIER_VALUE = -3.4F;
    private static float HEAVY_SMASH_SOUND_FALL_DISTANCE_THRESHOLD = 5.0F;
    public static  float KNOCKBACK_RANGE = 4.5F;
    private static float KNOCKBACK_POWER = 2.5F;
    private static double ABSOLUTE_MINIMUM_FALL_DISTANCE = 1.5F;
    private static double MAX_MIN_FALL_DISTANCE = 3.0F;
    private static  double MIDDLE_FALL_DISTANCE = 8.0F;

    public TNTMace(Settings settings) {
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

    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), 1.0F, 2, false);
    }

    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (shouldDealAdditionalDamage(attacker)) {
            ServerWorld serverWorld = (ServerWorld)attacker.getWorld();

            TntEntity tnt = new TntEntity(EntityType.TNT,serverWorld);
            serverWorld.spawnEntity(tnt);
            tnt.setPosition(target.getPos());
            tnt.setFuse(30);
            tnt.setVelocity(0,0.1,0);

            attacker.setVelocity(attacker.getVelocity().withAxis(Direction.Axis.Y, 0.009999999776482582));
            attacker.fallDistance = 0;
            ServerPlayerEntity serverPlayerEntity;
            if (attacker instanceof ServerPlayerEntity) {
                serverPlayerEntity = (ServerPlayerEntity)attacker;
                serverPlayerEntity.currentExplosionImpactPos = this.getCurrentExplosionImpactPos(serverPlayerEntity);
                serverPlayerEntity.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(serverPlayerEntity));
            }

            if (target.isOnGround()) {
                if (attacker instanceof ServerPlayerEntity) {
                    serverPlayerEntity = (ServerPlayerEntity)attacker;
                    serverPlayerEntity.setSpawnExtraParticlesOnFall(true);
                }
                serverWorld.playSound((Entity)null, attacker.getX(), attacker.getY(), attacker.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, attacker.getSoundCategory(), 1.0F, 1.0F);
            } else {
                serverWorld.playSound((Entity)null, attacker.getX(), attacker.getY(), attacker.getZ(), SoundEvents.ENTITY_TNT_PRIMED, attacker.getSoundCategory(), 1.0F, 1.0F);
            }

            knockbackNearbyEntities(serverWorld, attacker, target);

            if(!target.isAlive()){
                StatusEffectInstance resistance = new StatusEffectInstance(StatusEffects.RESISTANCE,5,4,false,true,true);
                attacker.addStatusEffect(resistance);
                serverWorld.createExplosion(
                        null,
                        null,
                        new ExplosionBehavior(),
                        target.getX(),
                        target.getY(),
                        target.getZ(),
                        80F,
                        false,
                        World.ExplosionSourceType.TNT,
                        ParticleTypes.EXPLOSION,
                        ParticleTypes.EXPLOSION,
                        SoundEvents.ENTITY_GENERIC_EXPLODE
                );
            }
        }

    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();

        final ExplosionBehavior EXPLOSION_BEHAVIOR = new AdvancedExplosionBehavior(
                true, false, Optional.of(1.22F), Registries.BLOCK.getOptional(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())
        );

        if(!world.isClient){
            ItemCooldownManager itemCooldownManager = context.getPlayer().getItemCooldownManager();

            world.createExplosion(
                    null,
                    null,
                    EXPLOSION_BEHAVIOR,
                    context.getBlockPos().getX(),
                    (context.getBlockPos().getY()) + 1,
                    context.getBlockPos().getZ(),
                    22.2F,
                    false,
                    World.ExplosionSourceType.TRIGGER,
                    ParticleTypes.EXPLOSION,
                    ParticleTypes.EXPLOSION,
                    SoundEvents.ENTITY_GENERIC_EXPLODE
            );

            if(!context.getPlayer().isInCreativeMode()){
                ItemStack itemStack = context.getPlayer().getStackInHand(context.getHand());
                itemStack.damage(1,context.getPlayer());
            }
            itemCooldownManager.set(context.getStack(),30);
        }


        return ActionResult.SUCCESS;
    }

    Vec3d getCurrentExplosionImpactPos(ServerPlayerEntity player) {
        return player.shouldIgnoreFallDamageFromCurrentExplosion() && player.currentExplosionImpactPos != null && player.currentExplosionImpactPos.y <= player.getPos().y ? player.currentExplosionImpactPos : player.getPos();
    }

    public double getFormulaMinimum(double playerFallDistance){
        return 2.0 * playerFallDistance;
    }
    public double getFormulaMiddle(double playerFallDistance){
        return 10.0 + 1.8 * (playerFallDistance - MAX_MIN_FALL_DISTANCE);
    }
    public double getFormulaMax(double playerFallDistance){
        return 20.0 + playerFallDistance - MIDDLE_FALL_DISTANCE;
    }

    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        Entity entity = damageSource.getSource();
        if (entity instanceof LivingEntity player) {
            if (!shouldDealAdditionalDamage(player)) {
                return 0.0F;
            } else {

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
