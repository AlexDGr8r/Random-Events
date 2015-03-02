package com.alexdgr8r.randomevents.common.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;

import com.alexdgr8r.randomevents.common.ModRandomEvents;
import com.alexdgr8r.randomevents.common.event.Event.EventStage;
import com.alexdgr8r.randomevents.common.util.ModConfig;

public class RandomEventHandler {
	
	private final static List<Event> events = new ArrayList<Event>();
	private static float weightedSum = 0.0F;
	
	private Event currentEvent = null;
	private Random random = new Random();
	
	@SubscribeEvent
	public void onServerTick(ServerTickEvent event)
	{
		if (event.phase == Phase.END)
			doTick(MinecraftServer.getServer());
	}
	
	private void doTick(MinecraftServer server) 
	{
		if (currentEvent == null || currentEvent.getCurrentStage() == EventStage.INACTIVE)
		{
			// Obtain new event //
			if (!(currentEvent instanceof EventNone))
			{
				this.currentEvent = new EventNone(null);
				this.currentEvent.init(new Object[] { server });
				ModRandomEvents.instance.logger.info("Event set to EventNone");
			}
			else
			{
				float r = random.nextFloat() * weightedSum;
				float sum = 0.0F;
				Iterator<Event> iter = events.iterator();
				
				while (iter.hasNext()) {
					Event event = iter.next();
					sum += event.getRarity();
					
					if (r <= sum)
					{
						this.currentEvent = event;
						event.init(new Object[] { server });
						ModRandomEvents.instance.logger.info("Event set to " + event.toString());
						break;
					}
				}
			}
			
		}
		else if (currentEvent.getCurrentStage() == EventStage.ACTIVE)
		{
			// Update Event //
			currentEvent.update(new Object[] { server });
			//ModRandomEvents.instance.logger.info("Updated Current Event.");
		}
	}
	
	public void stopCurrentEvent(Object[] args)
	{
		ModRandomEvents.instance.logger.info("Stopping current event...");
		
		if (this.currentEvent != null)
		{
			this.currentEvent.end(args);
			this.currentEvent = null;
		}
		
		ModRandomEvents.instance.logger.info("Current event stopped.");
	}
	
	public Event getCurrentEvent() 
	{
		return this.currentEvent;
	}
	/**
	 * Registers the event if it is enabled in the config file.
	 * Config file uses the event's ID to see if it can be registered.
	 * @param event
	 */
	public static void registerEvent(Event event)
	{
		// Check if enabled via config
		if (event instanceof EventNone || ModConfig.instance.get("Event " + event.getID() + " enabled", true, "Set to true to enable event with the ID: " + event.getID()))
		{
			events.add(event);
			weightedSum += event.getRarity();
		}
	}
	
	/**
	 * Unregisters the event if found.
	 * @param id Event ID
	 * @return true if removed
	 */
	public static boolean unregisterEvent(String id)
	{
		if (!id.equalsIgnoreCase("NONE"))
		{
			Iterator<Event> iter = events.iterator();
			
			while (iter.hasNext()) {
				Event event = iter.next();
				if (event.getID().equalsIgnoreCase(id))
				{
					iter.remove();
					weightedSum -= event.getRarity();
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Use this function to retrieve the current instance of an Event.
	 * @param id Event ID
	 * @return The Event if found, otherwise null
	 */
	public static Event getRegisteredEvent(String id)
	{
		Iterator<Event> iter = events.iterator();
		
		while (iter.hasNext()) {
			Event event = iter.next();
			if (event.getID().equalsIgnoreCase(id))
			{
				return event;
			}
		}
		return null;
	}
	
	static
	{
		registerEvent(new EventNone(null));
		registerEvent(new EventTest(null));
	}

}
