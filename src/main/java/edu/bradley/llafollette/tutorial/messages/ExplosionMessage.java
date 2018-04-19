package edu.bradley.llafollette.tutorial.messages;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ExplosionMessage implements IMessage { 
	
	@Override 
	public void fromBytes(ByteBuf buf) {
		
	}
	
	@Override 
	public void toBytes(ByteBuf buf) {
		
	}
	
	public static class Handler implements IMessageHandler<ExplosionMessage, IMessage> 
	{
		@Override
		public IMessage onMessage(ExplosionMessage message, MessageContext ctx) {
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message,ctx));
			return null;
		}
	}
	
	public static void handle(ExplosionMessage message, MessageContext ctx) {
		
		EntityPlayerMP player = ctx.getServerHandler().player;
		World world = player.getEntityWorld();
		boolean SMOKING = true;
		
		if (! world.isRemote) {
			player.world.createExplosion(player, player.posX, player.posY, player.posZ, 4.0f, SMOKING);
		}
	}
}
