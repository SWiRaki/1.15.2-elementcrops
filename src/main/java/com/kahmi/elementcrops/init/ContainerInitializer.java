package com.kahmi.elementcrops.init;

import com.kahmi.elementcrops.ElementCrops;
import com.kahmi.elementcrops.objects.container.Containers;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = ElementCrops.MOD_ID, bus = Bus.MOD)
@ObjectHolder(ElementCrops.MOD_ID)
public class ContainerInitializer {
	@SubscribeEvent
	public static void registerContainer(final RegistryEvent.Register<ContainerType<?>> pEvent) {
		pEvent.getRegistry().register(Containers.DUNGINFUSERBLOCK_CONTAINER);
	}
}
