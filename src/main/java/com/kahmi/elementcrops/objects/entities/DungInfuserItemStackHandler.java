package com.kahmi.elementcrops.objects.entities;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;

import com.kahmi.elementcrops.util.CropTierList;

public class DungInfuserItemStackHandler implements IItemHandler, IItemHandlerModifiable, INBTSerializable<CompoundNBT>
{
    protected NonNullList<ItemStack> mItemStacks;
	private int mTier = -1;

    public DungInfuserItemStackHandler()
    {
        this(6);
    }

    public DungInfuserItemStackHandler(int pSize)
    {
    	mItemStacks = NonNullList.withSize(pSize, ItemStack.EMPTY);
    }

    public DungInfuserItemStackHandler(NonNullList<ItemStack> pItemStacks)
    {
        this.mItemStacks = pItemStacks;
    }

    public void setSize(int pSize)
    {
    	mItemStacks = NonNullList.withSize(pSize, ItemStack.EMPTY);
    }

    @Override
    public void setStackInSlot(int pSlot, @Nonnull ItemStack pItemStack)
    {
        validateSlotIndex(pSlot);
        this.mItemStacks.set(pSlot, pItemStack);
        onContentsChanged(pSlot);
    }

    @Override
    public int getSlots()
    {
        return this.mItemStacks.size();
    }

    @Override
    @Nonnull
    public ItemStack getStackInSlot(int pSlot)
    {
        validateSlotIndex(pSlot);
        return this.mItemStacks.get(pSlot);
    }

    @Override
    @Nonnull
    public ItemStack insertItem(int pSlot, @Nonnull ItemStack pItemStack, boolean pSimulate)
    {
        if (pItemStack.isEmpty())
            return ItemStack.EMPTY;
            
        if (!isItemValid(pSlot, pItemStack))
            return pItemStack;

        validateSlotIndex(pSlot);

        ItemStack existing = this.mItemStacks.get(pSlot);

        int limit = getStackLimit(pSlot, pItemStack);

        if (!existing.isEmpty())
        {
            if (!ItemHandlerHelper.canItemStacksStack(pItemStack, existing))
                return pItemStack;

            limit -= existing.getCount();
        }

        if (limit <= 0)
            return pItemStack;
        
        if (pSlot > 1 && pSlot < 5 && this.mTier == -1) {
        	if (CropTierList.TIER0.contains(pItemStack.getItem())) {
        		this.mTier = 0;
        	} else if (CropTierList.TIER1.contains(pItemStack.getItem())) {
        		this.mTier = 1;
        	} else if (CropTierList.TIER2.contains(pItemStack.getItem())) {
        		this.mTier = 2;
        	} else {
        		this.mTier = 3;
        	}
        }

        boolean reachedLimit = pItemStack.getCount() > limit;

        if (!pSimulate)
        {
            if (existing.isEmpty())
            {
                this.mItemStacks.set(pSlot, reachedLimit ? ItemHandlerHelper.copyStackWithSize(pItemStack, limit) : pItemStack);
            }
            else
            {
                existing.grow(reachedLimit ? limit : pItemStack.getCount());
            }
            onContentsChanged(pSlot);
        }
        
        return reachedLimit ? ItemHandlerHelper.copyStackWithSize(pItemStack, pItemStack.getCount()- limit) : ItemStack.EMPTY;
    }

    @Override
    @Nonnull
    public ItemStack extractItem(int pSlot, int pAmount, boolean pSimulate)
    {
        if (pAmount == 0 || pSlot != 5)
            return ItemStack.EMPTY;

        validateSlotIndex(pSlot);

        ItemStack existing = this.mItemStacks.get(pSlot);

        if (existing.isEmpty())
            return ItemStack.EMPTY;

        int toExtract = Math.min(pAmount, existing.getMaxStackSize());

        if (existing.getCount() <= toExtract)
        {
            if (!pSimulate)
            {
                this.mItemStacks.set(pSlot, ItemStack.EMPTY);
                onContentsChanged(pSlot);
            }
            return existing;
        }
        else
        {
            if (!pSimulate)
            {
                this.mItemStacks.set(pSlot, ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract));
                onContentsChanged(pSlot);
            }

            return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
        }
    }

    @Override
    public int getSlotLimit(int pSlot)
    {
        return 1;
    }

    protected int getStackLimit(int pSlot, @Nonnull ItemStack pItemStack)
    {
        return Math.min(getSlotLimit(pSlot), pItemStack.getMaxStackSize());
    }

    @Override
    public boolean isItemValid(int pSlot, @Nonnull ItemStack pItemStack)
    {
    	Item item = pItemStack.getItem();
		if (pSlot == 0) {
			return (item == Items.COARSE_DIRT || item == Items.DIRT);
		} else if (pSlot == 1) {
			return (item == Items.BONE_MEAL);
		} else if (pSlot > 1 && pSlot < 5 && mTier == -1) {
			return (CropTierList.TIER0.contains(item) || CropTierList.TIER1.contains(item) || CropTierList.TIER2.contains(item) || CropTierList.TIER3.contains(item));
		} else if (pSlot > 1 && pSlot < 5) {
			if (this.containsTierItem(item)) {
				return false;
			} else if (mTier  == 0) {
				return CropTierList.TIER0.contains(item);
			} else if (mTier == 1) {
				return CropTierList.TIER1.contains(item);
			} else if (mTier == 2) {
				return CropTierList.TIER2.contains(item);
			} else {
				return CropTierList.TIER3.contains(item);
			}
		} else return false;
    }

    @Override
    public CompoundNBT serializeNBT()
    {
        ListNBT nbtTagList = new ListNBT();
        for (int i = 0; i < mItemStacks.size(); i++)
        {
            if (!mItemStacks.get(i).isEmpty())
            {
                CompoundNBT itemTag = new CompoundNBT();
                itemTag.putInt("Slot", i);
                mItemStacks.get(i).write(itemTag);
                nbtTagList.add(itemTag);
            }
        }
        CompoundNBT nbt = new CompoundNBT();
        nbt.put("Items", nbtTagList);
        nbt.putInt("Size", mItemStacks.size());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT pNBT)
    {
        setSize(pNBT.contains("Size", Constants.NBT.TAG_INT) ? pNBT.getInt("Size") : mItemStacks.size());
        ListNBT tagList = pNBT.getList("Items", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < tagList.size(); i++)
        {
            CompoundNBT itemTags = tagList.getCompound(i);
            int slot = itemTags.getInt("Slot");

            if (slot >= 0 && slot < mItemStacks.size())
            {
            	mItemStacks.set(slot, ItemStack.read(itemTags));
            }
        }
        onLoad();
    }

    protected void validateSlotIndex(int pSlot)
    {
        if (pSlot < 0 || pSlot >= mItemStacks.size())
            throw new RuntimeException("Slot " + pSlot + " not in valid range - [0," + mItemStacks.size() + ")");
    }

    protected void onLoad()
    {

    }

    protected void onContentsChanged(int pSlot)
    {

    }
    
    private boolean containsTierItem(Item pItem) {
    	boolean value = false;
    	for (int i = 2; i < this.mItemStacks.size() - 1; i++) {
    		if (this.mItemStacks.get(i).getItem() == pItem) {
    			value = true;
    		}
    	}
    	return value;
    }
}