package com.alexdgr8r.randomevents.common.event;

import java.util.Random;

import com.alexdgr8r.randomevents.common.util.ModConfig;

public class EventNone extends EventTimed {

	public EventNone(Object[] args) 
	{
		super(args);
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
		int minSecs = ModConfig.instance.get("Minimum amount of seconds between events", 1200, "It will take at least this long before another event occurs.");
		int maxSecs = ModConfig.instance.get("Maximum amount of seconds between events", 3600, "An event WILL occur after this set amount of time, if one already has not started");
		Random rand = new Random();
		return minSecs + rand.nextInt(maxSecs - minSecs);
	}

}
