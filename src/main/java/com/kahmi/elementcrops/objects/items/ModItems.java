package com.kahmi.elementcrops.objects.items;

import com.kahmi.elementcrops.ElementCrops;
import com.kahmi.elementcrops.ElementCrops.ELEMENTCROPS;

import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(ElementCrops.MOD_ID)
public class ModItems {
	//Items
	public static final Item IRONFRUIT = null;
	public static final Item COALFRUIT = null;
	public static final Item FLINTFRUIT = null;
	public static final Item LAPISBLOOM = null;
	public static final Item GOLDFRUIT = null;
	public static final Item REDSTONEFRUIT = null;
	public static final Item COTTON = null;
	public static final Item BLAZEFRUIT = null;
	public static final Item ENDERFRUIT = null;
	public static final Item GHASTFRUIT = null;
	public static final Item MAGMAFRUIT = null;
	public static final Item DIAMONDFRUIT = null;
	public static final Item EMERALDFRUIT = null;
	public static final Item QUARTZRUIT = null;
	
	public static final Item IRONFRUIT_SEEDS = null;
	public static final Item COALFRUIT_SEEDS = null;
	public static final Item FLINTFRUIT_SEEDS = null;
	public static final Item LAPISBLOOM_SEEDS = null;
	public static final Item GOLDFRUIT_SEEDS = null;
	public static final Item REDSTONEFRUIT_SEEDS = null;
	public static final Item COTTON_SEEDS = null;
	public static final Item BLAZEFRUIT_SEEDS = null;
	public static final Item ENDERFRUIT_SEEDS = null;
	public static final Item GHASTFRUIT_SEEDS = null;
	public static final Item MAGMAFRUIT_SEEDS = null;
	public static final Item DIAMONDFRUIT_SEEDS = null;
	public static final Item EMERALDFRUIT_SEEDS = null;
	public static final Item QUARTZRUIT_SEEDS = null;
	
	public static final Item DUNGINFUSER = null;
	
	public static final Properties[] GROUPS = { new Item.Properties().group(ELEMENTCROPS.INSTANCE) };
	
	public static Item CreateItem(int pProperties, String pRegistryName) {
		return new ModItem(GROUPS[pProperties], pRegistryName);
	}
	
	public static Item CreateSeedItem(Block pBlock, String pRegistryName) {
		return new SeedModItem((CropsBlock)pBlock, pRegistryName);
	}
	
	public static Item CreateFruitItem(int pProperties, int pTier, String pRegistryName) {
		return new FruitModItem(GROUPS[pProperties], pTier, pRegistryName);
	}
}
