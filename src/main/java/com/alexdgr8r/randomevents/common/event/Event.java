package com.alexdgr8r.randomevents.common.event;

import java.util.List;
import java.util.Random;

import com.alexdgr8r.randomevents.common.ModRandomEvents;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.SpawnerAnimals;

public abstract class Event {
	
	public enum EventStage {
		INIT, ACTIVE, ENDING, INACTIVE
	}
	
	private EventStage currStage = EventStage.INIT;
	protected Random random = new Random();
	
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
	
	protected EntityPlayer pickRandomPlayer(MinecraftServer server)
	{
		List players = server.getConfigurationManager().playerEntityList;
		EntityPlayer player = null;
		if (players.size() > 0) 
		{
			player = (EntityPlayer) players.get(random.nextInt(players.size()));
		}
		return player;
	}
	
	public boolean spawnEntityAtDistance(EntityLiving entity, int distance, BlockPos origin)
	{
		final int NUM_OF_ANGLES = 8;	// Adjust how many angles to do per circle. (8 = 45 degree change)
		int x = 0;
		int z = 0;
		float angle = (float)random.nextInt(NUM_OF_ANGLES) / (float)NUM_OF_ANGLES * 360F;	// Starting angle, prevents spawning always coming from one direction
		int yOffset = 0;										// What y level to start at
		int maxTries = distance / 5 * 2;
		for (int tries = 0; tries < maxTries; tries++)
		{
			ModRandomEvents.instance.logger.info("yOffset:" + yOffset);
			for (int i = 0; i < NUM_OF_ANGLES; i++)
			{
				x = (int) (MathHelper.cos(angle * (float)Math.PI / 180.0F) * distance);
				z = (int) (MathHelper.sin(angle * (float)Math.PI / 180.0F) * distance);
				BlockPos newPos = origin.add(x, yOffset, z);
				if (SpawnerAnimals.canCreatureTypeSpawnAtLocation(EntityLiving.SpawnPlacementType.ON_GROUND, entity.getEntityWorld(), newPos) && random.nextInt(10) < 8)
				{
					entity.setPositionAndRotation(newPos.getX(), newPos.getY(), newPos.getZ(), (angle + 180F) % 360F, 0.0F);
					entity.getEntityWorld().spawnEntityInWorld(entity);
					entity.spawnExplosionParticle();
					ModRandomEvents.instance.logger.info("Spawned with x: " + x + " y: " + yOffset + " z: " + z + " tries: " + tries);
					return true;
				}
				angle += 360 / NUM_OF_ANGLES;
			}
			
			if (yOffset >= 0) 
			{
				yOffset++;
			}
			yOffset = -yOffset;
		}
		
		return false;
	}

}
