package com.alexdgr8r.randomevents.common.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;

import com.alexdgr8r.randomevents.common.event.Event.EventStage;

public class RandomEventHandler {
	
	public final static List<Event> events = new ArrayList<Event>();
	private final static Random random = new Random();
	private static float weightedSum = 0.0F;
	
	private Event currentEvent = null;
	
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
						break;
					}
				}
			}
			
		}
		else if (currentEvent.getCurrentStage() == EventStage.ACTIVE)
		{
			// Update Event //
			currentEvent.update(new Object[] { server });
		}
	}
	
	public Event getCurrentEvent() 
	{
		return this.currentEvent;
	}
	
	public static void registerEvent(Event event)
	{
		events.add(event);
		weightedSum += event.getRarity();
	}
	
	static
	{
		registerEvent(new EventNone(null));
		registerEvent(new EventTest(null));
	}

}
