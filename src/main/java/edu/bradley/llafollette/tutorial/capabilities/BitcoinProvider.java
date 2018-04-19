package edu.bradley.llafollette.tutorial.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class BitcoinProvider implements ICapabilitySerializable<NBTBase>{

	@CapabilityInject(IBitcoin.class)
	public static final Capability<IBitcoin> BITCOIN_CAP = null;
	private IBitcoin instance = BITCOIN_CAP.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		
		return capability == BITCOIN_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		
		return capability == BITCOIN_CAP ? BITCOIN_CAP.<T>cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		
		return BITCOIN_CAP.getStorage().writeNBT(BITCOIN_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		
		BITCOIN_CAP.getStorage().readNBT(BITCOIN_CAP, this.instance, null, nbt);
	}

	
}
