package com.alexdgr8r.randomevents.common.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityNPC extends EntityCreature implements IEntityAdditionalSpawnData
{
	private String name = "NPC";
	private String texture = "textures/entity/steve.png";

	public EntityNPC(World worldIn)
	{
		super(worldIn);
		this.setAlwaysRenderNameTag(true);
		//((PathNavigateGround)this.getNavigator()).func_179688_b(true);
        //((PathNavigateGround)this.getNavigator()).func_179690_a(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIWatchClosest2(this, EntityPlayer.class, 8.0F, 1.0F));
        this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(4, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
	}
	
	public EntityNPC(World worldIn, String name, String texture) 
	{
		this(worldIn);
		this.name = name;
		this.texture = texture;
	}
	
	@Override
	protected boolean canDespawn()
    {
        return false;
    }
	
	@Override
	public IChatComponent getDisplayName()
    {
		ChatComponentText text = new ChatComponentText(this.name);	// TODO Localize
		text.getChatStyle().setColor(EnumChatFormatting.WHITE); 	// TODO change color based on enemy, neutral, or friendly NPC
		return text;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean getAlwaysRenderNameTagForRender()
    {
		return true;
    }
	
	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setString("NPCName", this.name);
        tagCompound.setString("NPCTexture", this.texture);
    }
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompound)
    {
        super.readEntityFromNBT(tagCompound);
        this.name = tagCompound.getString("NPCName");
        this.texture = tagCompound.getString("NPCTexture");
    }
	
	public String getTexture()
	{
		return this.texture;
	}

	@Override
	public void writeSpawnData(ByteBuf buffer)
	{
		ByteBufUtils.writeUTF8String(buffer, this.name);
		ByteBufUtils.writeUTF8String(buffer, this.texture);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData)
	{
		this.name = ByteBufUtils.readUTF8String(additionalData);
		this.texture = ByteBufUtils.readUTF8String(additionalData);
	}
	
}
