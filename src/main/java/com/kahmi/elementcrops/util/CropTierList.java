package com.kahmi.elementcrops.util;

import com.kahmi.elementcrops.objects.items.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class CropTierList {
	public static final CropTierList TIER0;
	public static final CropTierList TIER1;
	public static final CropTierList TIER2;
	public static final CropTierList TIER3;
	public static final CropTierList TIER4;
	
	static {
		TIER0 = new CropTierList(new Item[] {
				Items.WHEAT,
				Items.CARROT,
				Items.POTATO,
				Items.BEETROOT,
				Items.PUMPKIN,
				Items.MELON_SLICE,
				Items.COCOA_BEANS
		});
		TIER1 = new CropTierList(new Item[] {
				ModItems.IRONFRUIT,
				ModItems.COALFRUIT,
				ModItems.FLINTFRUIT,
				ModItems.LAPISBLOOM
		});
		TIER2 = new CropTierList(new Item[] {
				ModItems.GOLDFRUIT,
				ModItems.REDSTONEFRUIT,
				ModItems.COTTON
		});
		TIER3 = new CropTierList(new Item[] {
				ModItems.BLAZEFRUIT,
				ModItems.ENDERFRUIT,
				ModItems.GHASTFRUIT,
				ModItems.MAGMAFRUIT
		});
		TIER4 = new CropTierList(new Item[] {
				ModItems.DIAMONDFRUIT,
				ModItems.EMERALDFRUIT,
				ModItems.QUARTZRUIT
		});
	}

	private Item[] mItems;
	
	public CropTierList(Item[] pItems) {
		this.mItems = pItems;
	}
	
	public boolean contains(Item pItem) {
		boolean value = false;
		for (int i = 0; i < this.mItems.length; i++) {
			if (this.mItems[i] == pItem) {
				value = true;
			}
		}
		return value;
	}
}
