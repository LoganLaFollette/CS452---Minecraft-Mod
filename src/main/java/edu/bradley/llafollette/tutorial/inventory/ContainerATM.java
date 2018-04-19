package edu.bradley.llafollette.tutorial.inventory;

import edu.bradley.llafollette.tutorial.tileentities.TileEntityATM;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ContainerATM extends Container {

	public ContainerATM(IItemHandler playerInventory, ItemStackHandler inventory, EntityPlayer player,
			TileEntityATM tileEntityATM) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return false;
	}

}
