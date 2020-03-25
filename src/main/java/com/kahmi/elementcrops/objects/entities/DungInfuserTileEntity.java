package com.kahmi.elementcrops.objects.entities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.kahmi.elementcrops.objects.container.DungInfuserBlockContainer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class DungInfuserTileEntity extends TileEntity implements INamedContainerProvider {
	//private DungInfuserItemStackHandler handler = new DungInfuserItemStackHandler();
	private LazyOptional<IItemHandler> mHandler = LazyOptional.of(this::createHandler);
	
	public DungInfuserTileEntity() {
		super(TileEntities.DUNGINFUSERTILEENTITY);
	}
	
	public DungInfuserItemStackHandler createHandler() {
		return new DungInfuserItemStackHandler();
	}
	
	@Override
	public void read(CompoundNBT pCompound) {
		CompoundNBT inventory = pCompound.getCompound("inv");
		this.mHandler.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(inventory));
		super.read(pCompound);
	}

	@Override
	public CompoundNBT write(CompoundNBT pCompound) {
		this.mHandler.ifPresent(h -> {
			CompoundNBT inventory = ((INBTSerializable<CompoundNBT>)h).serializeNBT();
			pCompound.put("inv", inventory);
		});
		return super.write(pCompound);
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> pCapability, @Nullable Direction pSide) {
		if (pCapability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return mHandler.cast();
		}
		return super.getCapability(pCapability, pSide);
	}

	@Nullable
	@Override
	public Container createMenu(int pId, PlayerInventory pInventory, PlayerEntity pPlayer) {
		return new DungInfuserBlockContainer(pId, world, pos, pInventory, pPlayer);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent(getType().getRegistryName().getPath());
	}
}
