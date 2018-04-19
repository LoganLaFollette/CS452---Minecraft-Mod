package edu.bradley.llafollette.tutorial.util.handlers;

import edu.bradley.llafollette.tutorial.Reference;
import edu.bradley.llafollette.tutorial.init.KeyBindings;
import edu.bradley.llafollette.tutorial.init.MessageManager;
import edu.bradley.llafollette.tutorial.messages.BlockHitMessage;
import edu.bradley.llafollette.tutorial.messages.ExplosionMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

@EventBusSubscriber(modid=Reference.MOD_ID)
public class InputHandler {
	
	@SubscribeEvent
	public static void onKeyInput(InputEvent.KeyInputEvent event) {
		
		if (KeyBindings.eventKey.isPressed()) {
			if (Minecraft.getMinecraft().objectMouseOver.getBlockPos() != null) { //so the pig doesn't ruin everything
				MessageManager.INSTANCE.sendToServer(new BlockHitMessage());
			}
		}
		
		if (KeyBindings.explodeKey.isPressed()) {
			
//			System.out.println("BOOM!");
			MessageManager.INSTANCE.sendToServer(new ExplosionMessage());
		}
	}
}
