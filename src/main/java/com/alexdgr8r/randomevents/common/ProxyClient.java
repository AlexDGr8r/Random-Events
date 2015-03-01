package com.alexdgr8r.randomevents.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ProxyClient extends ProxyCommon {
	
	@Override
	public void preInit(ModRandomEvents mod, FMLPreInitializationEvent event)
	{
		super.preInit(mod, event);
	}
	
	@Override
	public void init(ModRandomEvents mod, FMLInitializationEvent event)
	{
		super.init(mod, event);
	}
	
	@Override
	public void postInit(ModRandomEvents mod, FMLPostInitializationEvent event)
	{
		super.postInit(mod, event);
	}

}
