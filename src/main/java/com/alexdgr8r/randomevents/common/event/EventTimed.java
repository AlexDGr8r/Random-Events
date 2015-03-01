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
		MinecraftServer server = (MinecraftServer) args[0];
		long currTime = server.getCurrentTime();
		this.endingTime = currTime + (getLength() * 1000L);
	}

	@Override
	protected void doUpdate(Object[] args)
	{
		MinecraftServer server = (MinecraftServer) args[0];
		long currTime = server.getCurrentTime();

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
