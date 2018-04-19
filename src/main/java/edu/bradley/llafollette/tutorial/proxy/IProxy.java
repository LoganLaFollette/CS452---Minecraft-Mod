package edu.bradley.llafollette.tutorial.proxy;

import net.minecraft.item.Item;

public interface IProxy {

	public void init();

	void registerItemRenderer(Item itemBase, int meta, String name);
	public void registerKeyBindings();
	
}
