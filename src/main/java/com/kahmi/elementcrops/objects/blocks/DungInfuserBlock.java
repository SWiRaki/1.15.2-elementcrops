package com.kahmi.elementcrops.objects.blocks;

import javax.annotation.Nullable;

import com.kahmi.elementcrops.objects.entities.DungInfuserTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class DungInfuserBlock extends Block {
	public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 5);
	private static final VoxelShape INSIDE = makeCuboidShape(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	private static final VoxelShape SHAPE = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), VoxelShapes.or(makeCuboidShape(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D), makeCuboidShape(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D), makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D), INSIDE), IBooleanFunction.ONLY_FIRST);
	
	public DungInfuserBlock(Properties pProperties) {
	   super(pProperties);
	   this.setDefaultState(this.stateContainer.getBaseState().with(LEVEL, Integer.valueOf(0)));
	}
	
	@Override
	public boolean hasTileEntity(BlockState pState) {
		return true;
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState pState, IBlockReader pWorld) {
		return new DungInfuserTileEntity();
	}
	
	@Override
	public VoxelShape getShape(BlockState pState, IBlockReader pWorldIn, BlockPos pPos, ISelectionContext pContext) {
		return SHAPE;
	}
	
	@Override
	public VoxelShape getRaytraceShape(BlockState pState, IBlockReader pWworldIn, BlockPos pPos) {
		return INSIDE;
	}

	@Override
	public ActionResultType onBlockActivated(BlockState pState, World pWorldIn, BlockPos pPos, PlayerEntity pPlayer, Hand pHandIn, BlockRayTraceResult pResult) {
		if (!pWorldIn.isRemote) {
			TileEntity entity = pWorldIn.getTileEntity(pPos);
			if (entity instanceof INamedContainerProvider) {
				NetworkHooks.openGui((ServerPlayerEntity)pPlayer, (INamedContainerProvider)entity, entity.getPos());
			} else {
				throw new IllegalStateException("Our named container provider is missing!");
			}
			return ActionResultType.SUCCESS;
		}
		return super.onBlockActivated(pState, pWorldIn, pPos, pPlayer, pHandIn, pResult);
	}

	
	public void setContentLevel(World pWorldIn, BlockPos pPos, BlockState pState, int pLevel) {
		pWorldIn.setBlockState(pPos, pState.with(LEVEL, Integer.valueOf(MathHelper.clamp(pLevel, 0, 5))), 2);
		pWorldIn.updateComparatorOutputLevel(pPos, this);
	}

	/**
	* @deprecated call via {@link IBlockState#hasComparatorInputOverride()} whenever possible. Implementing/overriding
	* is fine.
	*/
	public boolean hasComparatorInputOverride(BlockState pState) {
		return true;
	}

	/**
	* @deprecated call via {@link IBlockState#getComparatorInputOverride(World,BlockPos)} whenever possible.
	* Implementing/overriding is fine.
	*/
	public int getComparatorInputOverride(BlockState pBlockState, World pWorldIn, BlockPos pPos) {
		return pBlockState.get(LEVEL);
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(LEVEL);
	}

	public boolean allowsMovement(BlockState pState, IBlockReader pWorldIn, BlockPos pPos, PathType pType) {
		return false;
	}
}
