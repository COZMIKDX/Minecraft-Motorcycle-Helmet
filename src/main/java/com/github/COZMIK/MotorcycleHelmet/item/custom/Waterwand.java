package com.github.COZMIK.MotorcycleHelmet.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class Waterwand extends Item {
    public Waterwand(Properties properties) {
        super(properties);
    }
    private final Fluid waterFluid = Fluids.WATER;

    // I tried using onItemUse instead of onItemUseFirst.
    // This function doesn't have an ItemStack argument, but I can get it from the context, which
    // has an ItemStack in it representing the item in the player's hand (the one used).
    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        ItemStack wand = context.getItemInHand(); // Since I'm still getting use to all of this. I will make this method instead of making a utils class.

        if (!world.isClientSide()) {
            PlayerEntity playerEntity = Objects.requireNonNull(context.getPlayer());
            BlockState clickedBlock = world.getBlockState(context.getClickedPos());

            rightClickOnCertainBlockState(world, playerEntity, wand, context);

            wand.hurtAndBreak(1, playerEntity, player -> player.broadcastBreakEvent(context.getHand()));
        }


        return super.useOn(context);
    }

    private void rightClickOnCertainBlockState(World world, PlayerEntity playerEntity, ItemStack wand, ItemUseContext context) {
        // Based on BucketItem.java's onItemRightClick method.
        // BlockState blockState = world.getBlockState(context.getClickedPos());
        BlockPos blockPos = context.getClickedPos().offset(context.getClickedFace().getNormal()); // Trying flint and steel method of placing. //canBlockContainFluid(world, blockPos);
        if (this.tryPlaceContainedLiquid(playerEntity, world, blockPos)) {
            //playerEntity.addStat(Stats.ITEM_USED.get(this));
            //return ActionResult.func_233538_a_(this.)
        }
    }

    private boolean tryPlaceContainedLiquid(PlayerEntity playerEntity, World world, BlockPos blockPos) {
        BlockState blockState = world.getBlockState(blockPos);
        if (!world.setBlock(blockPos, this.waterFluid.defaultFluidState().createLegacyBlock(), 11) && !blockState.getFluidState().isSource()) {
            return false;
        } else {
            this.playWaterPlacementSound(world, playerEntity, blockPos);
            return true;
        }
    }

    private void playWaterPlacementSound(World world, PlayerEntity playerEntity, BlockPos blockPos) {
        SoundEvent soundEvent = this.waterFluid.getAttributes().getEmptySound();
        world.playSound(playerEntity, blockPos, SoundEvents.WATER_AMBIENT, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

//    private boolean canBlockContainFluid(World worldIn, BlockPos posIn, BlockState blockstate)
//    {
//        return blockstate.getBlock() instanceof ILiquidContainer && ((ILiquidContainer)blockstate.getBlock()).canContainFluid(worldIn, posIn, blockstate, this.containedBlock);
//    }
}
