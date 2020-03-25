package com.kahmi.elementcrops.init;

import com.kahmi.elementcrops.ElementCrops;
import com.kahmi.elementcrops.objects.blocks.Blocks;
import com.kahmi.elementcrops.objects.entities.DungInfuserTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = ElementCrops.MOD_ID, bus = Bus.MOD)
@ObjectHolder(ElementCrops.MOD_ID)
public class TileEntityInitializer {
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<TileEntityType<?>> pEvent) {
		IForgeRegistry<TileEntityType<?>> registry = pEvent.getRegistry();
		registry.register(TileEntityType.Builder.create(DungInfuserTileEntity::new, Blocks.DUNGINFUSER).build(null).setRegistryName("dunginfuser"));
	}
}
