package com.kahmi.elementcrops.objects.container;

import com.kahmi.elementcrops.ElementCrops;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(ElementCrops.MOD_ID)
public class Containers {
	public static ContainerType<DungInfuserBlockContainer> DUNGINFUSERBLOCK_CONTAINER = (ContainerType<DungInfuserBlockContainer>) IForgeContainerType.create((pWindowID, pInventory, pData) -> {
		BlockPos pos = pData.readBlockPos();
		return new DungInfuserBlockContainer(pWindowID, ElementCrops.PROXY.getClientWorld(), pos, pInventory, ElementCrops.PROXY.getClientPlayer());
	}).setRegistryName("dunginfuser");
}
