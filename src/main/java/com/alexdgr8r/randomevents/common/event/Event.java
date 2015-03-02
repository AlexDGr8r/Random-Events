package com.alexdgr8r.randomevents.common.event;

public abstract class Event {
	
	public enum EventStage {
		INIT, ACTIVE, ENDING, INACTIVE
	}
	
	private EventStage currStage = EventStage.INIT;
	
	/** 
	 * Be sure to register any needed EventHandlers here
	 * Do not confuse with init(), there is only one instance
	 * of this event. init() is when starting the event.
	 * 
	 * @param args usually null unless specified
	 */
	public Event(Object[] args) {}
	
	// Implement these methods to handle event
	protected abstract void doInit(Object[] args);
	protected abstract void doUpdate(Object[] args);
	protected abstract void doEnd(Object[] args);
	
	public abstract float getRarity();
	
	public abstract String getID();
	
	public final void init(Object[] args)
	{
		this.currStage = EventStage.INIT;
		doInit(args);
		this.currStage = EventStage.ACTIVE;
	}
	
	public final void update(Object[] args)
	{
		doUpdate(args);
	}
	
	public final void end(Object[] args)
	{
		this.currStage = EventStage.ENDING;
		doEnd(args);
		this.currStage = EventStage.INACTIVE;
	}
	
	public EventStage getCurrentStage()
	{
		return currStage;
	}

}
