package com.alexdgr8r.randomevents.common;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ProxyCommon {
	
	public void preInit(ModRandomEvents mod, FMLPreInitializationEvent event)
	{
		
	}
	
	public void init(ModRandomEvents mod, FMLInitializationEvent event)
	{
		FMLCommonHandler.instance().bus().register(mod.eventHandler);
	}
	
	public void postInit(ModRandomEvents mod, FMLPostInitializationEvent event)
	{
		
	}

}
