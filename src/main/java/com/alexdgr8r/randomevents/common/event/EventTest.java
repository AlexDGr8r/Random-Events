package com.alexdgr8r.randomevents.common.event;

import com.alexdgr8r.randomevents.common.ModRandomEvents;

public class EventTest extends Event {

	public EventTest(Object[] args) {
		super(args);
		ModRandomEvents.instance.logger.info("Test Event constructed.");
	}

	@Override
	protected void doInit(Object[] args) {
		ModRandomEvents.instance.logger.info("Test Event initialized.");
	}

	@Override
	protected void doUpdate(Object[] args) {
		ModRandomEvents.instance.logger.info("Test Event updated.");
		this.end(args);
	}

	@Override
	protected void doEnd(Object[] args) {
		ModRandomEvents.instance.logger.info("Test Event Ended.");
	}

	@Override
	public float getRarity() {
		return 0.5F;
	}

}
