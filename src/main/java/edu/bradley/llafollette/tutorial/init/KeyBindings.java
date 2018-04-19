package edu.bradley.llafollette.tutorial.init;

import org.lwjgl.input.Keyboard;

import edu.bradley.llafollette.tutorial.Reference;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyBindings {
	
	public static KeyBinding eventKey;
	public static KeyBinding explodeKey;
	
	public static void init() {
		eventKey = new KeyBinding("key." + Reference.MOD_ID + ".event", Keyboard.KEY_G, "key.categories." + Reference.MOD_ID);
		ClientRegistry.registerKeyBinding(eventKey);
		
		explodeKey = new KeyBinding("key." + Reference.MOD_ID + ".explode", Keyboard.KEY_H, "key.categories." + Reference.MOD_ID);
		ClientRegistry.registerKeyBinding(explodeKey);
	}
}
