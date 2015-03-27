package com.alexdgr8r.randomevents.client.renderer;

import java.util.HashMap;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import com.alexdgr8r.randomevents.common.entity.EntityNPC;

public class RenderNPC extends RenderBiped
{

	private static final HashMap<String, ResourceLocation> textures = new HashMap<String, ResourceLocation>();
	
	public RenderNPC(RenderManager renderManager)
	{
		super(renderManager, new ModelPlayer(0.0F, false), 0.5F, 1.0F);
		this.addLayer(new LayerBipedArmor(this));
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerArrow(this));
        ModelPlayer model = (ModelPlayer) this.getMainModel();
        this.addLayer(new LayerCustomHead(model.bipedHead));
	}
	
	private ResourceLocation getNPCTexture(EntityNPC npc)
	{
		String texStr = npc.getTexture();
		if (textures.containsKey(texStr))
		{
			return textures.get(texStr);
		}
		
		ResourceLocation texture = new ResourceLocation(texStr);
		textures.put(texStr, texture);
		return texture;
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityLiving entity)
    {
        return this.getNPCTexture((EntityNPC)entity);
    }
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getNPCTexture((EntityNPC)entity);
    }
	
	@Override
	public void func_82422_c()
    {
        GlStateManager.translate(0.0F, 0.1875F, 0.0F);
    }
	
	@Override
	protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
		float f1 = 0.9375F;
        GlStateManager.scale(f1, f1, f1);
    }

}
