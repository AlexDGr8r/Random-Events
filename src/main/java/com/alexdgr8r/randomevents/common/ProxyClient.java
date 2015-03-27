package com.alexdgr8r.randomevents.common;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.alexdgr8r.randomevents.client.renderer.RenderNPC;
import com.alexdgr8r.randomevents.common.entity.EntityNPC;

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
		
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		RenderingRegistry.registerEntityRenderingHandler(EntityNPC.class, new RenderNPC(renderManager));
	}
	
	@Override
	public void postInit(ModRandomEvents mod, FMLPostInitializationEvent event)
	{
		super.postInit(mod, event);
	}

}
