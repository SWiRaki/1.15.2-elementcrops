 package com.kahmi.elementcrops.util;

import org.apache.logging.log4j.Logger;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ServerProxy implements IProxy {
	@Override
	public void init(Logger pLogger) {
        pLogger.info("Initializing Server Proxy");
	}

	@Override
	public World getClientWorld() {
		throw new IllegalStateException("Only run this on the client!");
	}

	@Override
	public PlayerEntity getClientPlayer() {
		throw new IllegalStateException("Only run this on the client!");
	}

}
