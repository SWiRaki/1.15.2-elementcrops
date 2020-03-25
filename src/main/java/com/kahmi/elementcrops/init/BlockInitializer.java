package com.kahmi.elementcrops.init;

import com.kahmi.elementcrops.ElementCrops;
import com.kahmi.elementcrops.objects.blocks.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = ElementCrops.MOD_ID, bus = Bus.MOD)
@ObjectHolder(ElementCrops.MOD_ID)
public class BlockInitializer {
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> pEvent) {
		IForgeRegistry<Block> registry = pEvent.getRegistry();
		registry.register(new CropIronFruit().setRegistryName("crop_ironfruit"));
		registry.register(new CropGoldFruit().setRegistryName("crop_goldfruit"));
		registry.register(new CropDiamondFruit().setRegistryName("crop_diamondfruit"));
		registry.register(new CropCoalFruit().setRegistryName("crop_coalfruit"));
		registry.register(new CropEnderFruit().setRegistryName("crop_enderfruit"));
		registry.register(new CropBlazeFruit().setRegistryName("crop_blazefruit"));
		registry.register(new DungInfuserBlock(Block.Properties.create(Material.IRON, MaterialColor.STONE).hardnessAndResistance(2.0F).notSolid()).setRegistryName("dunginfuser"));
	}
}