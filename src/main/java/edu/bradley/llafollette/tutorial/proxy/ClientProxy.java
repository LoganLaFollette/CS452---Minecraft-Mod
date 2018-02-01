package edu.bradley.llafollette.tutorial.proxy;

import edu.bradley.llafollette.tutorial.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy implements CommonProxy {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerItemRenderer(Item item, int metadata, String name) {
		
		ModelResourceLocation location = new ModelResourceLocation(Reference.MOD_ID + ":" + name, "inventory");
		System.out.println("setting resource location for model " + item.getUnlocalizedName());
		ModelLoader.setCustomModelResourceLocation(item, metadata, location);
		
	}

}
