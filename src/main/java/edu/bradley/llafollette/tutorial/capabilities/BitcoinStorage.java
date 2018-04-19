package edu.bradley.llafollette.tutorial.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class BitcoinStorage implements IStorage<IBitcoin> {

	@Override
	public NBTBase writeNBT(Capability<IBitcoin> capability, IBitcoin instance, EnumFacing side) {
		// TODO Auto-generated method stub
		return new NBTTagFloat(instance.getCoins());
	}

	@Override
	public void readNBT(Capability<IBitcoin> capability, IBitcoin instance, EnumFacing side, NBTBase nbt) {
		instance.setCoinAmount(((NBTPrimitive) nbt).getFloat());
		
	}

}
