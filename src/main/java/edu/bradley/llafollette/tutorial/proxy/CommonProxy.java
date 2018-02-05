package edu.bradley.llafollette.tutorial.proxy;

import net.minecraft.item.Item;

public interface CommonProxy {

	public void init();

	void registerItemRenderer(Item itemBase, int i, String name);
	
}
