package com.kahmi.elementcrops.objects.items;

import net.minecraft.block.CropsBlock;
import net.minecraft.item.BlockNamedItem;

public class SeedModItem extends BlockNamedItem {
	public SeedModItem(CropsBlock pBlock, String pRegistryName) {
		super(pBlock, ModItems.GROUPS[0]);
		this.setRegistryName(pRegistryName);
	}

}
