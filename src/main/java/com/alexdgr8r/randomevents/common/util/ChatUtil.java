package com.alexdgr8r.randomevents.common.util;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ChatUtil {
	
	public ChatComponentText formatForNPC(String name, String message)
	{
		ChatComponentText text = new ChatComponentText(name);
		text.getChatStyle().setColor(EnumChatFormatting.WHITE);
		text.appendText(": " + message);
		return text;
	}

}
