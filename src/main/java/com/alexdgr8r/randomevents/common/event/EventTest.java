package com.alexdgr8r.randomevents.common.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import com.alexdgr8r.randomevents.common.ModRandomEvents;
import com.alexdgr8r.randomevents.common.entity.EntityNPC;

public class EventTest extends Event {

	public EventTest(Object[] args) {
		super(args);
		ModRandomEvents.instance.logger.info("Test Event constructed.");
	}

	@Override
	protected void doInit(Object[] args) {
		ModRandomEvents.instance.logger.info("Test Event initialized.");
		
		EntityPlayer player = pickRandomPlayer(((MinecraftServer)args[0]));
		if (player != null) 
		{
			World world = player.getEntityWorld();
			EntityNPC npc = new EntityNPC(world, "AlexDGr8r", "randomevents:textures/entity/AlexDGr8r.png");
			for (int i = 0; i < 3; i++) {
				if (this.spawnEntityAtDistance(npc, 16, new BlockPos(player.getPosition())))
				{
					npc.func_175449_a(new BlockPos(player), 2);
					break;
				}
			}
		}
		
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
		return 1.0F;
	}

	@Override
	public String getID()
	{
		return "TEST";
	}

}
