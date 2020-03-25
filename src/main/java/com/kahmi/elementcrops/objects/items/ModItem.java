package com.kahmi.elementcrops.objects.items;

import net.minecraft.item.Item;

public class ModItem extends Item {
	public ModItem(Properties pProperties, String pRegistryName) {
		super(pProperties);
		this.setRegistryName(pRegistryName);
	}
}
