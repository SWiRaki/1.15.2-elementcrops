package com.kahmi.elementcrops.init;

import com.kahmi.elementcrops.ElementCrops;
import com.kahmi.elementcrops.objects.blocks.Blocks;
import com.kahmi.elementcrops.objects.items.ModItems;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = ElementCrops.MOD_ID, bus = Bus.MOD)
@ObjectHolder(ElementCrops.MOD_ID)
public class ItemInitializer {
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> pEvent) {
		IForgeRegistry<Item> registry = pEvent.getRegistry();
		registry.register(ModItems.CreateSeedItem(Blocks.CROP_BLAZEFRUIT, "blazefruit_seeds"));
		registry.register(ModItems.CreateFruitItem(0, 3, "blazefruit"));
		registry.register(ModItems.CreateSeedItem(Blocks.CROP_COALFRUIT, "coalfruit_seeds"));
		registry.register(ModItems.CreateFruitItem(0, 1, "coalfruit"));
		registry.register(ModItems.CreateSeedItem(Blocks.CROP_DIAMONDFRUIT, "diamondfruit_seeds"));
		registry.register(ModItems.CreateFruitItem(0, 4, "diamondfruit"));
		registry.register(ModItems.CreateSeedItem(Blocks.CROP_ENDERFRUIT, "enderfruit_seeds"));
		registry.register(ModItems.CreateFruitItem(0, 3, "enderfruit"));
		registry.register(ModItems.CreateSeedItem(Blocks.CROP_GOLDFRUIT, "goldfruit_seeds"));
		registry.register(ModItems.CreateFruitItem(0, 2, "goldfruit"));
		registry.register(ModItems.CreateSeedItem(Blocks.CROP_IRONFRUIT, "ironfruit_seeds"));
		registry.register(ModItems.CreateFruitItem(0, 1, "ironfruit"));
		registry.register(new BlockItem(Blocks.DUNGINFUSER, ModItems.GROUPS[0]).setRegistryName("dunginfuser"));
	}
}