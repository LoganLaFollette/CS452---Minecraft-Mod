package edu.bradley.llafollette.tutorial.tileentities;

import javax.annotation.Nullable;

import edu.bradley.llafollette.tutorial.inventory.ContainerATM;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityATM extends TileEntity {

	private static final int RING_TICKS = 100;
	private int timeRemaining = 0;
	private boolean inUse = false;
	
	private static final int MATERIAL_SLOT = 0;
	private static final int BITCOIN_SLOT = 1;
	private static final int OUTPUT_SLOT = 1;
	private static final int MAX_SLOTS = 2;
	
	private ItemStackHandler inventory = new ItemStackHandler(MAX_SLOTS);
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing)
	}
	
	@Nullable
	@Override
	public <INTERFACE> INTERFACE getCapability(Capability<INTERFACE> capability, @Nullable EnumFacing facing) {
	
		if (hasCapability(capability, facing)) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
		}
		
		return super.getCapability(capability, facing);
	}
	
	@Override 
	public void readFromNBT(NBTTagCompound compound) {
		inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("inventory", inventory.serializeNBT());
		return super.writeToNBT(compound);
	}
	
	public ContainerATM createContainer(final EntityPlayer player) {
		final IItemHandler playerInventory = (IItemHandler) player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
		return new ContainerATM(playerInventory, inventory, player, this);
	}
}
