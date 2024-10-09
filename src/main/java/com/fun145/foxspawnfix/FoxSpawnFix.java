package com.fun145.foxspawnfix;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FoxSpawnFix implements ModInitializer {
	public static final String MOD_ID = "foxspawnfix";
	public static final String FOXSPAWNFIX_DATAPACK_PATH ="foxspawnfix";
	public static final ModContainer MOD_CONTAINER = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow();

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		if(!ResourceManagerHelper.registerBuiltinResourcePack(
				new Identifier(MOD_ID, FOXSPAWNFIX_DATAPACK_PATH),
				MOD_CONTAINER,
				ResourcePackActivationType.NORMAL)
		) {
			LOGGER.warn("could not register built-in datapack \""+FOXSPAWNFIX_DATAPACK_PATH+"\" ");
		}
	}
}