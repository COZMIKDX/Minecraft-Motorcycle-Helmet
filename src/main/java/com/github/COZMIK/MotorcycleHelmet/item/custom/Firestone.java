package com.github.COZMIK.MotorcycleHelmet.item.custom;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;


// Eventually I might move some methods to a utils class or something.

public class Firestone extends Item {
    public Firestone(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getLevel();

        // Run only on server.
        // Good for spawning items or change blocks.
        // This block is where the logic will be called.
        if (!world.isClientSide()) {
            PlayerEntity playerEntity = Objects.requireNonNull(context.getPlayer());
            // A blockstate represents a block itself.
            BlockState clickedBlock = world.getBlockState(context.getClickedPos());

            rightClickOnCertainBlockState(clickedBlock, context, playerEntity);

            // Damage the item being held. An item stack represents an in game item.
            stack.hurtAndBreak(1, playerEntity, player -> player.broadcastBreakEvent(context.getHand()));
        }

        return super.onItemUseFirst(stack, context);
    }

    // This is where most of the logic is.
    private void rightClickOnCertainBlockState(BlockState clickedBlock, ItemUseContext context, PlayerEntity playerEntity) {
        // Functionality: When the item is used, either light the player on fire or check if the player is eligible for
        // receiving fire resistance. If they are not on fire, and they clicked a correct block, they are given the effect.
        // If neither happens, light the ground on fire.
        boolean playerIsNotOnFire = !playerEntity.isOnFire();

        if (random.nextFloat() > 0.5) {
            // light player on fire
            lightEntityOnFire(playerEntity, 10);
        } else if (playerIsNotOnFire && blockGrantsEffect(clickedBlock)) {
            // Give the player fire resistance. Destroy the clicked block.
            gainFireResistanceAndDestroyBlock(playerEntity, context.getLevel(), context.getClickedPos());
        } else {
            // light the ground on fire.
            lightGroundOnFire(context);
        }
    }

    private boolean blockGrantsEffect(BlockState clickedBlock) {
        return clickedBlock.getBlock() == Blocks.OBSIDIAN;
    }

    public static void lightEntityOnFire(Entity entity, int second) {
        entity.setSecondsOnFire(second);
    }

    private void gainFireResistanceAndDestroyBlock(PlayerEntity playerEntity, World world, BlockPos pos) {
        gainFireResistance(playerEntity);
        world.destroyBlock(pos, false);
    }

    public static void gainFireResistance(PlayerEntity playerEntity) {
        playerEntity.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 200));
        //playerEntity.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 200));
    }

    // Flint and steel have this effect. I could yoink a bunch of code from that, removing what I don't need.
    public static void lightGroundOnFire(ItemUseContext context) {
        PlayerEntity playerentity = context.getPlayer();
        World world = context.getLevel();
        BlockPos blockpos = context.getClickedPos().offset(context.getClickedFace().getNormal());

        if (AbstractFireBlock.canBePlacedAt(world, blockpos, context.getHorizontalDirection())) {
            world.playSound(playerentity, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
            BlockState blockstate = AbstractFireBlock.getState(world, blockpos);
            world.setBlock(blockpos, blockstate, 11);
        }

    }
}












