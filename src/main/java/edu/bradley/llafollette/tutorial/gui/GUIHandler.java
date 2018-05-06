package edu.bradley.llafollette.tutorial.gui;

import javax.annotation.Nullable;

import edu.bradley.llafollette.tutorial.tileentities.TileEntityATM;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler {

	
	public GUIHandler() {
		System.out.println("ATM:    registering GUI Handler");
	}
	
	@Override
	@Nullable
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		final TileEntity tileEntity = world.getTileEntity(new BlockPos(x,y,z));
		
		switch (ID) {
		case GuiIDs.ATM: 
			if (tileEntity != null) {
				System.out.println("ATM:    server-side container create");
				return ((TileEntityATM) tileEntity).createContainer(player);
			}
			break;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		final TileEntity tileEntity = world.getTileEntity(new BlockPos(x,y,z));
		
		switch (ID) {
		case GuiIDs.ATM: 
			if (tileEntity != null) {
				TileEntityATM atm = (TileEntityATM) tileEntity;
				System.out.println("ATM:    client-side container create");
				return new GUIAtm(atm.createContainer(player));
			}
			break;
		}
		return null;
	}

}
