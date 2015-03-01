package com.alexdgr8r.randomevents.common.event;

import net.minecraft.server.MinecraftServer;

public abstract class EventTimed extends Event
{

	private long endingTime;

	public EventTimed(Object[] args)
	{
		super(args);
	}

	@Override
	protected void doInit(Object[] args)
	{
		long currTime = MinecraftServer.getCurrentTimeMillis();
		this.endingTime = currTime + (getLength() * 1000L);
	}

	@Override
	protected void doUpdate(Object[] args)
	{
		long currTime = MinecraftServer.getCurrentTimeMillis();

		if (currTime >= endingTime)
		{
			this.end(args);
		}
	}

	/**
	 * The number of seconds this event will last.
	 * @return seconds
	 */
	public abstract int getLength();

}
