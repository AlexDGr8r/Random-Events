package com.alexdgr8r.randomevents.common.event;

import java.util.Random;

import com.alexdgr8r.randomevents.common.ModRandomEvents;
import com.alexdgr8r.randomevents.common.util.ModConfig;

public class EventNone extends EventTimed {
	
	private int minSecs;
	private int maxSecs;

	public EventNone(Object[] args) 
	{
		super(args);
		minSecs = ModConfig.instance.get("Minimum amount of seconds between events", 1200, "It will take at least this long before another event occurs.");
		maxSecs = ModConfig.instance.get("Maximum amount of seconds between events", 3600, "An event WILL occur after this set amount of time, if one already has not started");
		
		if (maxSecs <= minSecs)
		{
			ModRandomEvents.instance.logger.info("'Maximum amount of seconds between events' cannot be less than or equal to 'Minimum amount of seconds between events' in the config file.");
			ModRandomEvents.instance.logger.info("Setting maximum to 1 higher than minimum, but you should still fix it in the config file.");
			maxSecs = minSecs + 1;
		}
	}

	@Override
	protected void doEnd(Object[] args) {}

	@Override
	public float getRarity() 
	{
		return 0.95F;
	}

	@Override
	public int getLength()
	{
		Random rand = new Random();
		int len = minSecs + rand.nextInt(maxSecs - minSecs);
		ModRandomEvents.instance.logger.info(len + " seconds until end of event.");
		return len;
	}

	@Override
	public String getID()
	{
		return "NONE";
	}

}
