package com.gorgonine.worsemaces.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.*;
import java.util.function.Predicate;

public class CommandBlockMace extends Item {
    private static int ATTACK_DAMAGE_MODIFIER_VALUE = 5;
    private static float ATTACK_SPEED_MODIFIER_VALUE = -3.4F;
    private static float HEAVY_SMASH_SOUND_FALL_DISTANCE_THRESHOLD = 5.0F;
    public static  float KNOCKBACK_RANGE = 3.5F;
    private static float KNOCKBACK_POWER = 0.7F;
    private static double ABSOLUTE_MINIMUM_FALL_DISTANCE = 1.5F;
    private static double MAX_MIN_FALL_DISTANCE = 3.0F;
    private static  double MIDDLE_FALL_DISTANCE = 8.0F;
    public static int age = 0;

    Random random = new Random();

    public CommandBlockMace(Settings settings) {
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

            String[] commands = {
                    "execute at @r run summon hoglin",
                    "give @a stick 2",
                    "title @a title \"Plob!!!\"",
                    "tellraw @a {\"color\":\"yellow\",\"text\":\"Pisrats joined the game\"}",
                    "tellraw @a \"<Creeper> stop the killings brah\"",
                    "rotate @r facing entity @e[sort=random,limit=1]",
                    "execute at @a run summon lightning_bolt",
                    "kill @e[sort=random,limit=1]",
                    "say \"Pisrat is banned\"",
                    "tick sprint 1000",
                    "tellraw @a {\"text\":\"steve_buildingyt whispers to you: i love crosshair x\",\"italic\":true,\"color\":\"gray\"}",
                    "give @r written_book{pages:['{\"text\":\"Who here smells?\\\\n\\\\nRead Page 52 for the answer\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\"}','{\"text\":\"\\\\n\\\\n\\\\n\\\\n\\\\n\\\\n\\\\n\\\\n\\\\n\\\\nI don\\'t know\"}','{\"text\":\"Brah passed\"}'],title:\"INFO.. IMPORTANT!\",author:Notch}",
                    "place structure minecraft:village_taiga",
                    "execute at @r run fill ~2 ~2 ~2 ~-2 ~-2 ~-2 glass",
                    "execute at @a run playsound minecraft:entity.wither.death master @a ~ ~ ~",
                    "execute at @r run clone ~-10 ~-10 ~-10 ~10 ~10 ~10 ~-10 ~11 ~-10",
                    "execute at @r run summon bogged ~ ~ ~ {NoAI:1b,PersistenceRequired:1b,Silent:1b,active_effects:[{id:resistance,duration:9999999,amplifier:5,show_particles:0b}]}",
                    "execute at @r run fill ~5 ~-1 ~5 ~-5 ~-1 ~-5 minecraft:dried_ghast",
                    "title @a title {\"text\":\"\\ud83d\\ude28 Behind you!\",\"bold\":true,\"color\":\"dark_red\"}",
                    "effect give @a minecraft:levitation 3 5 true",
                    "weather thunder 100s",
                    "time add 15000t",
                    "damage @r 10 minecraft:thrown by @e[sort=random,limit=1]",
                    "give @r iron_nugget 1",
                    "loot give Player755 mine ^ ^ ^1 minecraft:netherite_pickaxe",
                    "execute at @r run tp @p ^ ^ ^5",
                    "execute at @r run place structure minecraft:igloo ~ ~ ~",
                    "item replace entity @r armor.head with carved_pumpkin[custom_name=[{\"text\":\"Bratwurst Bill\",\"italic\":false}],rarity=uncommon,tooltip_display={hide_tooltip:true},enchantment_glint_override=false,enchantments={binding_curse:1}]",
                    "effect give @a minecraft:speed 3 255 true",
                    "give @r cocoa_beans[custom_name=[{\"text\":\"Hog Scat\",\"italic\":false}],rarity=uncommon,attribute_modifiers=[{type:gravity,amount:0.04,operation:add_value,id:\"1753834238348\"}],can_place_on=[{blocks:acacia_leaves}],tooltip_display={hidden_components:[attribute_modifiers,can_break,can_place_on]}] 2304",
                    "execute at @r run fill ~1 ~ ~1 ~-1 ~-64 ~-1 air",
                    "execute at @r run particle minecraft:explosion ~ ~ ~ ~ ~ ~ 151 999999 force",
                    "execute at @e run summon cod",
                    "execute at @r run summon squid ^ ^1.5 ^1",
                    "execute at @r run summon glow_squid ^ ^1.5 ^1",
                    "loot give @r loot minecraft:chests/ancient_city_ice_box",
                    "tellraw @a [{\"text\":\"<\"},{\"selector\":\"@r\"},{\"text\":\"> I eat dung\"}]",
                    "tellraw @a [{\"text\":\"<\"},{\"selector\":\"@r\"},{\"text\":\"> I eat dung\"}]",
                    "tellraw @a [{\"text\":\"<\"},{\"selector\":\"@r\"},{\"text\":\"> hey guys. i am broke. can anyone give me a lot of free things. i am broke\"}]",
                    "title @a title [{\"selector\":\"@s\",\"color\":\"green\"},{\"text\":\" has won.\",\"color\":\"green\"}]",
                    "tellraw @a [{\"text\":\"POP QUIZ\",\"underlined\":true,\"color\":\"yellow\",\"bold\":true},{\"text\":\": Who was Ea-Nasir?\",\"underlined\":true},\"\\n\\n\",{\"text\":\"A)\",\"bold\":true,\"color\":\"red\",\"click_event\":{\"action\":\"run_command\",\"command\":\"I am a big smelly ape-like individual who thinks that Steve has a brother \uD83E\uDD23\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\"}},{\"text\":\" Steve's Brother\",\"click_event\":{\"action\":\"run_command\",\"command\":\"I am a big smelly ape-like individual who thinks that Steve has a brother \uD83E\uDD23\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\"}},\"  | \",{\"text\":\"B)\",\"color\":\"green\",\"bold\":true,\"click_event\":{\"action\":\"run_command\",\"command\":\"say I am a giant foolish individual and I like to eat plobs and also I am a fool\"}},{\"text\":\" A Nigerian Backflip Master\",\"click_event\":{\"action\":\"run_command\",\"command\":\"say I am a giant foolish individual and I like to eat plobs and also I am a fool\"}},\"\\n-------------------------------------------------------\\n\",{\"text\":\"C)\",\"bold\":true,\"color\":\"yellow\",\"click_event\":{\"action\":\"run_command\",\"command\":\"/say I am the trivia God!\"}},{\"text\":\" A copper merchant\",\"click_event\":{\"action\":\"run_command\",\"command\":\"/say I am the trivia God!\"}},\"| \",{\"text\":\"D)\",\"bold\":true,\"color\":\"dark_blue\",\"click_event\":{\"action\":\"run_command\",\"command\":\"say I am a huge FOOL and I did not know that https://en.wikipedia.org/wiki/Shiva\"}},{\"text\":\" The Hindu Destroyer of Worlds\",\"click_event\":{\"action\":\"run_command\",\"command\":\"say I am a huge FOOL and I did not know that https://en.wikipedia.org/wiki/Shiva\"}},\"\\n \"]",
                    "summon bat ~ ~ ~ {Invulnerable:1b,Silent:1b,active_effects:[{id:resistance,duration:99999,amplifier:5,show_particles:0b}],Passengers:[{id:ravager,CustomName:[{text:\"Joandre Olmeda\",color:blue},{text:\", The Beast!\",color:blue}],Glowing:1b,PersistenceRequired:1b,active_effects:[{id:resistance,duration:999999,amplifier:5,show_particles:0b},{id:strength,duration:999999,amplifier:1,show_particles:0b},{id:speed,duration:999999,amplifier:4,show_particles:0b},{id:conduit_power,duration:999999,amplifier:4,show_particles:0b},{id:fire_resistance,duration:999999,amplifier:4,show_particles:0b},{id:oozing,duration:999999,amplifier:4,show_particles:0b},{id:infested,duration:999999,amplifier:4,show_particles:0b},{id:slow_falling,duration:999999,amplifier:1,show_particles:0b}],PatrolLeader:1b,equipment:{mainhand:{id:netherite_axe,count:1},offhand:{id:netherite_axe,count:1},head:{id:netherite_helmet,count:1},chest:{id:netherite_chestplate,count:1},legs:{id:netherite_leggings,count:1},feet:{id:netherite_boots,count:1}},drop_chances:{mainhand:0f,offhand:0f,head:0f,chest:0f,legs:0f,feet:0f}}]}",
                    "title @a title [{\"text\":\"\\\"\",\"bold\":true},\"I \",{\"text\":\"hate\",\"bold\":true,\"color\":\"dark_red\"},{\"text\":\" \",\"bold\":true},\"Sacramento\",{\"text\":\"\\\"\",\"bold\":true},\" \",{\"text\":\"-\",\"color\":\"aqua\"},{\"text\":\"Olmeda\",\"color\":\"blue\"},{\"text\":\",\",\"color\":\"dark_blue\"},{\"text\":\" Joandre\",\"color\":\"dark_aqua\"},\" \",{\"text\":\"5/28/25\",\"italic\":true}]",
                    "give @p minecraft:player_head[minecraft:custom_name={\"text\":\"Heavy Core\",\"color\":\"light_purple\",\"underlined\":false,\"bold\":false,\"italic\":false},minecraft:lore=[{\"text\":\"Ingredients\",\"color\":\"blue\",\"italic\":false}],profile={id:[I;1495133147,-1925232252,-1844803383,1289169941],properties:[{name:\"textures\",value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmI2ZjRlNzBjYzZiOTA3NDAxMDg2MmM3OGFmYmYxMzE1MDc0YzliMDAwZjhiMjI3YWUxMzM3ZWI3ZmIwZDYwZCJ9fX0=\"}]}] 1"

            };

            String command = commands[random.nextInt(commands.length)];
            PlayerEntity player = (PlayerEntity) attacker;
            CommandManager commandManager = Objects.requireNonNull(player.getServer()).getCommandManager();
            ServerCommandSource commandSource = player.getServer().getCommandSource();
            commandManager.executeWithPrefix(commandSource, command);

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

    private ArrayList<Block> getAllBlocks(){
        ArrayList<Block> blocks = new ArrayList<Block>();

        Block[] disallowedBlocks = {
                Blocks.AIR,
                Blocks.CAVE_AIR,
                Blocks.VOID_AIR,
                Blocks.ACACIA_BUTTON,
                Blocks.BIRCH_BUTTON,
                Blocks.BAMBOO_BUTTON,
                Blocks.CHERRY_BUTTON,
                Blocks.CRIMSON_BUTTON,
                Blocks.DARK_OAK_BUTTON,
                Blocks.JUNGLE_BUTTON,
                Blocks.SPRUCE_BUTTON,
                Blocks.STONE_BUTTON,
                Blocks.WARPED_BUTTON,
                Blocks.MANGROVE_BUTTON,
                Blocks.OAK_BUTTON,
                Blocks.POLISHED_BLACKSTONE_BUTTON,
                Blocks.PALE_OAK_BUTTON,
                Blocks.KELP_PLANT,
                Blocks.STRUCTURE_VOID,
                Blocks.HEAVY_CORE,
                Blocks.ATTACHED_PUMPKIN_STEM,
                Blocks.ATTACHED_MELON_STEM,
                Blocks.WHEAT,
                Blocks.CARROTS,
                Blocks.BEETROOTS,
                Blocks.POTATOES,
                Blocks.KELP_PLANT,
                Blocks.KELP,
                Blocks.NETHERITE_BLOCK,
                Blocks.GOLD_BLOCK,
                Blocks.DIAMOND_BLOCK,
                Blocks.IRON_BLOCK,
                Blocks.DRAGON_EGG,
                Blocks.ANCIENT_DEBRIS,
                Blocks.WITHER_SKELETON_SKULL,
                Blocks.WITHER_SKELETON_WALL_SKULL,
                Blocks.BEACON
        };

        for(Block block : Registries.BLOCK){
            if(!List.of(disallowedBlocks).contains(block)){
                blocks.add(block);
            }
        }

        return blocks;
    }

    private ArrayList<EntityType> getAllMobs(){
        ArrayList<EntityType> entities = new ArrayList<EntityType>();

        EntityType[] disallowedEntities = {
                EntityType.WITHER,
                EntityType.ENDER_DRAGON,
                EntityType.BREEZE_WIND_CHARGE,
                EntityType.WITHER_SKULL,
                EntityType.FALLING_BLOCK,
                EntityType.EGG,
                EntityType.SNOWBALL,
                EntityType.EVOKER_FANGS,
                EntityType.GLOW_ITEM_FRAME,
                EntityType.ITEM_DISPLAY,
                EntityType.ITEM,
                EntityType.ITEM_FRAME,
                EntityType.TRIDENT,
                EntityType.WARDEN,
                EntityType.LLAMA_SPIT,
                EntityType.PLAYER,
                EntityType.SHULKER_BULLET,
                EntityType.CREAKING,
                EntityType.SPLASH_POTION
        };

        for(int i = 0; i < Registries.ENTITY_TYPE.size(); i++){
            if(!List.of(disallowedEntities).contains(Registries.ENTITY_TYPE.get(i))){
                entities.add(Registries.ENTITY_TYPE.get(i));
            }
        }

        return entities;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient){
            if(!user.getOffHandStack().isOf(Items.WIND_CHARGE)){
                ItemCooldownManager itemCooldownManager = user.getItemCooldownManager();
                ArrayList<EntityType> entityTypes = getAllMobs();
                EntityType entityType = entityTypes.get(random.nextInt(entityTypes.size()));
                Entity entity = entityType.spawn((ServerWorld) world, user.getBlockPos(), SpawnReason.NATURAL);
                entity.setVelocity(user.getRotationVector().normalize().multiply(2.5d));
                world.playSound(null,user.getX(),user.getY(),user.getZ(),SoundEvents.BLOCK_BEACON_DEACTIVATE,SoundCategory.PLAYERS,1.0F,1.0F);

                user.swingHand(hand);

                itemCooldownManager.set(user.getMainHandStack(), 1 * 20);

                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;

        }
        return ActionResult.FAIL;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getPlayer().getOffHandStack().isOf(Items.WIND_CHARGE)){
            World world = context.getWorld();
            if(!context.getWorld().isClient){
                HitResult hit = context.getPlayer().raycast(20, 0, false); // 20 is distance used by the DebugHud for "looking at block", false means ignore fluids
                if (hit.getType() == HitResult.Type.BLOCK) {
                    BlockHitResult blockHit = (BlockHitResult) hit;
                    Direction direction = blockHit.getSide();
                    ItemCooldownManager itemCooldownManager = context.getPlayer().getItemCooldownManager();

                    ArrayList<Block> blocks = getAllBlocks();
                    BlockState randomBlockState = blocks.get(random.nextInt(getAllBlocks().size())).getPlacementState(new ItemPlacementContext(context));

                    if(!context.getBlockPos().add(direction.getVector()).equals(context.getPlayer().getBlockPos()) || context.getBlockPos().add(direction.getVector()).add(0,1,0).equals(context.getPlayer().getBlockPos())){
                        world.setBlockState(context.getBlockPos().add(direction.getVector()), randomBlockState);

                        world.playSound(null,context.getBlockPos().add(direction.getVector()),randomBlockState.getSoundGroup().getPlaceSound(), SoundCategory.BLOCKS,1.0F,1.0F);

                        itemCooldownManager.set(context.getStack(), 8 * 20);

                        return ActionResult.SUCCESS;
                    }else{
                        return ActionResult.FAIL;
                    }
                }
            }
        }
        return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
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
