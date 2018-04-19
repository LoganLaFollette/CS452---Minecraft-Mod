package edu.bradley.llafollette.tutorial.items;

import edu.bradley.llafollette.tutorial.Tutorial;
import edu.bradley.llafollette.tutorial.init.IHasModel;
import edu.bradley.llafollette.tutorial.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase  extends Item implements IHasModel {

	protected String name;
	
	public ItemBase(String name) {
		
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name); //takes module id
		
		setCreativeTab(CreativeTabs.MATERIALS);
		ModItems.ITEMS.add(this); //automatically adds itself to array
	}

	@Override
	public void registerModels() {
		//items register themselves in the inventory so this model shows in the inventory
		System.out.println("ItemBase requests registration of model " + name);
		Tutorial.proxy.registerItemRenderer(this, 0, name);
	}
	
}
