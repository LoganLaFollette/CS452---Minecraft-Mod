package edu.bradley.llafollette.tutorial.items;

import edu.bradley.llafollette.tutorial.Tutorial;
import net.minecraft.item.Item;

public class ItemBase  extends Item{

	protected String name;
	
	public ItemBase(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	
	public void registerItemModel() {
		Tutorial.proxy.registerItemRenderer(this, 0, name);
	}
}
