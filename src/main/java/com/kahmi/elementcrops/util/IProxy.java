package com.kahmi.elementcrops.util;

import org.apache.logging.log4j.Logger;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public interface IProxy {
	void init(Logger pLogger);
	World getClientWorld();
	PlayerEntity getClientPlayer();
}
