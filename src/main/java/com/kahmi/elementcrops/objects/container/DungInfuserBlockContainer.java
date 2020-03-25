package com.kahmi.elementcrops.objects.container;

import com.kahmi.elementcrops.ElementCrops;
import com.kahmi.elementcrops.objects.blocks.Blocks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(ElementCrops.MOD_ID)
public class DungInfuserBlockContainer extends Container {
	private TileEntity mEntity;
	private PlayerEntity mPlayer;
	private IItemHandler mPlayerInventory;
	
	public DungInfuserBlockContainer(int pId, World pWorld, BlockPos pPos, PlayerInventory pInventory, PlayerEntity pPlayer) {
		super(Containers.DUNGINFUSERBLOCK_CONTAINER, pId);
		this.mEntity = pWorld.getTileEntity(pPos);
		this.mPlayer = pPlayer;
		this.mPlayerInventory = new InvWrapper(mPlayer.inventory);
		this.mEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
			 addSlot(h, 0, 14, 35);
			 addSlot(h, 1, 35, 35);
			 addSlotRange(h, 2, 56, 35, 3, 18);
			 addSlot(h, 5, 142, 35);
		});
		layoutPlayerInventorySlots(8, 84);
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity pPlayerIn) {
		return isWithinUsableDistance(IWorldPosCallable.of(mEntity.getWorld(), mEntity.getPos()), pPlayerIn, Blocks.DUNGINFUSER);
	}
	
	private void addSlot(IItemHandler pItemHandler, int pIndex, int pX, int pY) {
		addSlot(new SlotItemHandler(pItemHandler, pIndex, pX, pY));
	}
	
	private int addSlotRange(IItemHandler pItemHandler, int pIndex, int pX, int pY, int pAmount, int pDistanceX) {
		for (int i = 0; i < pAmount; i++) {
			addSlot(pItemHandler, pIndex, pX, pY);
			pX += pDistanceX;
			pIndex++;
		}
		return pIndex;
	}
	
	private int addSlotBox(IItemHandler pItemHandler, int pIndex, int pX, int pY, int pColumns, int pDistanceX, int pRows, int pDistanceY) {
		for (int i = 0; i < pRows; i++) {
			pIndex = addSlotRange(pItemHandler, pIndex, pX, pY, pColumns, pDistanceX);
			pY += pDistanceY;
		}
		return pIndex;
	}
	
	private void layoutPlayerInventorySlots(int pX, int pY) {
		addSlotBox(this.mPlayerInventory, 9, pX, pY, 9, 18, 3, 18);
		
		pY += 58;
		addSlotRange(this.mPlayerInventory, 0, pX, pY, 9, 18);
	}
}
