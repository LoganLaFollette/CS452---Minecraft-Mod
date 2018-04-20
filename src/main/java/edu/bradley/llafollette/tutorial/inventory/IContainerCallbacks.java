package edu.bradley.llafollette.tutorial.inventory;

import net.minecraft.entity.player.EntityPlayer;

public interface IContainerCallbacks {
	
	void onContainerOpened(EntityPlayer player);
	void onContainerClosed(EntityPlayer player);
	boolean isUsableByPlayer(EntityPlayer player);
	void update();
}
