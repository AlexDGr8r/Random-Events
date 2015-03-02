package com.alexdgr8r.randomevents.common;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

import org.apache.logging.log4j.Logger;

import com.alexdgr8r.randomevents.common.event.RandomEventHandler;
import com.alexdgr8r.randomevents.common.util.ModConfig;

@Mod(modid = ModRandomEvents.MODID, name = ModRandomEvents.MOD_NAME, version = ModRandomEvents.VERSION)
public class ModRandomEvents
{
    public static final String MODID = "randomevents";
    public static final String MOD_NAME = "Random Events";
    public static final String VERSION = "1.0";
    
    @SidedProxy(clientSide="com.alexdgr8r.randomevents.common.ProxyClient", serverSide="com.alexdgr8r.randomevents.common.ProxyCommon")
    public static ProxyCommon proxy;
    
    @Mod.Instance(MODID)
    public static ModRandomEvents instance;
    
    public Logger logger;
    public RandomEventHandler eventHandler;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	logger = event.getModLog();
    	ModConfig.instance.load(event.getSuggestedConfigurationFile());
		proxy.preInit(this, event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		proxy.init(this, event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
		proxy.postInit(this, event);
    }
    
    @EventHandler
    public void onServerStarted(FMLServerStartingEvent event)
    {
    	this.eventHandler = new RandomEventHandler();
    	FMLCommonHandler.instance().bus().register(eventHandler);
    }
    
    @EventHandler
    public void onServerStopping(FMLServerStoppingEvent event)
    {
    	FMLCommonHandler.instance().bus().unregister(eventHandler);
    	eventHandler.stopCurrentEvent(new Object[] { MinecraftServer.getServer() });
    }
    
}
