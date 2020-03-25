package com.kahmi.elementcrops.objects.items;

public class FruitModItem extends ModItem {
	public final int TIER;
	public FruitModItem(Properties pProperties, int pTier, String pRegistryName) {
		super(pProperties, pRegistryName);
		TIER = pTier;
	}

}
