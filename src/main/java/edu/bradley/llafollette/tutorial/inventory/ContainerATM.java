package edu.bradley.llafollette.tutorial.inventory;

import edu.bradley.llafollette.tutorial.tileentities.TileEntityATM;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerATM extends Container {

	private final IContainerCallbacks callbacks;
	private final IItemHandler playerInventory;
	private final IItemHandler atmInventory;
	
	private static final int PLAYER_ROWS = 3;
	private static final int BITCOIN_SLOT = 0;
	private static final int MATERIAL_SLOT = 1;
	private static final int OUTPUT_SLOT = 2;
	private static final int TOTAL_TABLE_SLOTS = 3;
	
	private static final int SLOTS_PER_ROW = 9;
	private static final int CELL_SIZE = 18;
	private static final int PLAYER_SLOT_BASE = 84;
	private static final int HOTBAR_BASE = 142;
	private static final int LEFT_BORDER_WIDTH = 8;
	
	private static final int TOTAL_PLAYER_SLOTS = 4 * SLOTS_PER_ROW;
	private static final int TOTAL_CONTAINER_SLOTS = TOTAL_TABLE_SLOTS + TOTAL_PLAYER_SLOTS;
	
	public ContainerATM(final IItemHandler playerInventory,final ItemStackHandler atmInventory,final EntityPlayer player,
			final IContainerCallbacks callbacks) {
		
		this.playerInventory = playerInventory;
		this.atmInventory = atmInventory;
		this.callbacks = callbacks;
		
		callbacks.onContainerOpened(player);
		
		addSlotToContainer(new SlotItemHandler(atmInventory, 0, 17, 12)
				{
					@Override
					public void onSlotChanged() {
						
					}
				});
		addSlotToContainer(new SlotItemHandler(atmInventory, 1, 17, 51)
				{
			 		@Override 
			 		public void onSlotChanged() {
			 			
			 		}
				});
		addSlotToContainer(new SlotItemHandler(atmInventory, 2, 115, 34)
		{
	 		@Override 
	 		public void onSlotChanged() {
	 			
	 		}
		});
		
		for (int row = 0; row < PLAYER_ROWS; row++) {
			int y_position = PLAYER_SLOT_BASE + (row * CELL_SIZE);
			
			for (int col = 0; col < SLOTS_PER_ROW; col++) {
				int index = SLOTS_PER_ROW + col + row*SLOTS_PER_ROW;
				
				int x_position = LEFT_BORDER_WIDTH + (col * CELL_SIZE);
				
				addSlotToContainer(new SlotItemHandler(playerInventory, index, x_position, y_position));
			}
		}
		
		for (int col = 0; col < SLOTS_PER_ROW; col++) {
			
			int x_position = LEFT_BORDER_WIDTH + (col * CELL_SIZE);
			int index = col;
		}
	}
	
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		final Slot slot = this.inventorySlots.get(index);
		ItemStack originalStack = ItemStack.EMPTY;
		
		if (slot != null && !slot.getStack().isEmpty()) {
			
			final ItemStack stack = slot.getStack();
			originalStack = stack.copy();
			
			if (index == OUTPUT_SLOT) {
				
				if (!this.mergeItemStack(stack, TOTAL_TABLE_SLOTS, TOTAL_CONTAINER_SLOTS, false)) {
					return ItemStack.EMPTY;
				}
			}
			else if (index != BITCOIN_SLOT && index != MATERIAL_SLOT) {
				
				Item toMove = stack.getItem();
				if (toMove == Items.IRON_INGOT || toMove == Items.GOLD_INGOT) {
					
					if (!this.mergeItemStack(stack, MATERIAL_SLOT, MATERIAL_SLOT+1, false)) {
						return ItemStack.EMPTY;
					}
				}
				
				if (toMove == Items.DIAMOND || toMove == Items.EMERALD) {
					if (!this.mergeItemStack(stack, BITCOIN_SLOT, BITCOIN_SLOT+1, false)) {
						return ItemStack.EMPTY;
					}
				}
			}
			else {
				if (!this.mergeItemStack(stack, TOTAL_TABLE_SLOTS, TOTAL_CONTAINER_SLOTS, false)) {
					return ItemStack.EMPTY;
				}
			}
			
			if (stack.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			}
			else {
				slot.onSlotChanged();
			}
			
			if (stack.getCount() == originalStack.getCount()) {
				return ItemStack.EMPTY;
			}
			
			slot.onTake(playerIn, stack);
		}
		return originalStack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return callbacks.isUsableByPlayer(playerIn);
	}
	
	@Override 
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);
		
		callbacks.onContainerClosed(playerIn);
	}

	public IItemHandler getPlayerInventory() {
		return playerInventory;
	}
	
	public IItemHandler getAtmInventory() {
		return atmInventory;
	}
}
