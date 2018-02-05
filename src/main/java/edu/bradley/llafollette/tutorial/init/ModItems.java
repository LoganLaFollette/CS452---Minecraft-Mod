package edu.bradley.llafollette.tutorial.init;

import edu.bradley.llafollette.tutorial.Reference;
import edu.bradley.llafollette.tutorial.items.ItemBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid=Reference.MOD_ID)
public class ModItems {
	
	public static ItemBase cheese = new ItemBase("cheese");
	
	public static Item[] items = {
			cheese,
	};

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		
		IForgeRegistry<Item> registry = event.getRegistry();
		
		for (Item item : items) {
			System.out.println("attempt to register new item " + item.getUnlocalizedName());
			registry.register(item);
		}
	}
	
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
		System.out.println("attempt to register render for new item " + cheese.getUnlocalizedName());
		registerRender(cheese);
	}
	
	public static void registerRender(ItemBase item) {
		item.registerItemModel();
	}
	
}
