package com.alexdgr8r.randomevents.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import com.alexdgr8r.randomevents.common.entity.EntityNPC;

public class ProxyCommon {
	
	public void preInit(ModRandomEvents mod, FMLPreInitializationEvent event)
	{
		
	}
	
	public void init(ModRandomEvents mod, FMLInitializationEvent event)
	{
		EntityRegistry.registerModEntity(EntityNPC.class, "EntityRandomNPC", 0, mod, 80, 3, true);
	}
	
	public void postInit(ModRandomEvents mod, FMLPostInitializationEvent event)
	{
		
	}

}
