package edu.bradley.llafollette.tutorial.tileentities;

import javax.annotation.Nullable;

import edu.bradley.llafollette.tutorial.init.ModItems;
import edu.bradley.llafollette.tutorial.inventory.ContainerATM;
import edu.bradley.llafollette.tutorial.inventory.IContainerCallbacks;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityATM extends TileEntity implements ITickable, IContainerCallbacks {

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
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
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
		
		timeRemaining = compound.getInteger("craftTime");
		if (timeRemaining != 0) {
			inUse = true;
		}
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("inventory", inventory.serializeNBT());
		compound.setInteger("craftTime", timeRemaining);
		return super.writeToNBT(compound);
	}
	
	public ContainerATM createContainer(final EntityPlayer player) {
		final IItemHandler playerInventory = (IItemHandler) player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
		return new ContainerATM(playerInventory, inventory, player, this);
	}

	@Override
	public void onContainerOpened(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) == this && player.getDistanceSq(this.pos.getX() + 0.5,
				this.pos.getY() + 0.5, this.pos.getZ() + 0.5) <= 64;
				
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
	@Override 
	public void update() {
		if (inUse) {
			timeRemaining--;
			System.out.println("JT:     tick down crafting timer to " + timeRemaining);
		}
		
		if (!this.world.isRemote) {
			if (!inUse && canCombine()) {
				timeRemaining = 100;
				inUse = true;
			}
			
			if (timeRemaining == 0) && canCombine()) {
				System.out.println("JT:    time remaining is zero, and container can combine slots");
				produceResult();
				inUse = false;
				this.markDirty();
			}
			else if (! canCombine()) {
				inUse = false;
			}
		}
	}

	private void produceResult() {
		ItemStack result = new ItemStack(ModItems.bitcoin);
		ItemStack outputStack = inventory.getStackInSlot(OUTPUT_SLOT);
		ItemStack materialStack = inventory.getStackInSlot(MATERIAL_SLOT);
		ItemStack bitcoinStack = inventory.getStackInSlot(BITCOIN_SLOT);
		
		materialStack.shrink(1);
		bitcoinStack.shrink(1);
		
		if (outputStack.isEmpty()) {
			System.out.println("JT:     assigning new stack to slot");
			inventory.setStackInSlot(OUTPUT_SLOT, result);
		}
		else if (outputStack.getItem() == ModItems.bitcoin) {
			System.out.println("JT:  inserting new item in existing stack");
			System.out.println("JT:  count currently " + outputStack.getCount());
			System.out.println("JT   unlocalized name: " + outputStack.getItem().getUnlocalizedName());
			inventory.insertItem(OUTPUT_SLOT, result, false);
		}
	}

	private boolean canCombine() {
		ItemStack materialItems = inventory.getStackInSlot(MATERIAL_SLOT);
		ItemStack bitcoinItems = inventory.getStackInSlot(BITCOIN_SLOT);
		
		if (materialItems.isEmpty() || bitcoinItems.isEmpty()) {
			return false;
		}
		
		Item material = materialItems.getItem();
		Item bitcoin = bitcoinItems.getItem();
		
		if ((material != Items.IRON_INGOT) && (material != Items.GOLD_INGOT)) {
			return false;
		}
		
		return true;
	}
}
