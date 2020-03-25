package com.kahmi.elementcrops.util;

import org.apache.logging.log4j.Logger;

import com.kahmi.elementcrops.objects.container.Containers;
import com.kahmi.elementcrops.objects.screens.DungInfuserBlockScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {
	
	@Override
	public void init(Logger pLogger) {
		ScreenManager.registerFactory(Containers.DUNGINFUSERBLOCK_CONTAINER,  DungInfuserBlockScreen::new);
	}

	@Override
	public World getClientWorld() {
		return Minecraft.getInstance().world;
	}

	@Override
	public PlayerEntity getClientPlayer() {
		return Minecraft.getInstance().player;
	}
}
