package com.kahmi.elementcrops.objects.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class CropElement_0_3 extends CropsBlock {
	public static final IntegerProperty CROPAGE = BlockStateProperties.AGE_0_3;
	private static final VoxelShape[] SHAPE = new VoxelShape[]{Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D)};
	
	private IItemProvider mSeedItem;
	
	public CropElement_0_3(IItemProvider pSeedItem) {
		super(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.CROP));
		this.mSeedItem = pSeedItem;
	}

	public IntegerProperty getAgeProperty() {
		return CROPAGE;
	}

	public int getMaxAge() {
		return 3;
	}

	protected IItemProvider getSeedsItem() {
		return mSeedItem;
	}

	public void tick(BlockState pState, ServerWorld pWorldIn, BlockPos pPos, Random pRandom) {
		if (pRandom.nextInt(3) != 0) {
			super.tick(pState, pWorldIn, pPos, pRandom);
		}
	}

	protected int getBonemealAgeIncrease(World pWorldIn) {
		return super.getBonemealAgeIncrease(pWorldIn) / 3;
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(BlockStateProperties.AGE_0_3);
	}

	public VoxelShape getShape(BlockState pState, IBlockReader pWorldIn, BlockPos pPos, ISelectionContext pContext) {
		return SHAPE[pState.get(this.getAgeProperty())];
	}
}