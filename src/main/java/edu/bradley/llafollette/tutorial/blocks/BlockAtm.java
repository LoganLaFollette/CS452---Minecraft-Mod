package edu.bradley.llafollette.tutorial.blocks;

import edu.bradley.llafollette.tutorial.Reference;
import edu.bradley.llafollette.tutorial.gui.GuiIDs;
import edu.bradley.llafollette.tutorial.tileentities.TileEntityATM;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockAtm extends BlockTileEntity<TileEntityATM> {

	public BlockAtm(String name, Material material) {
		super(name, material);
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {

		System.out.println("ATM:     create new tile entity");
		return new TileEntityATM();
	}

	@Override 
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			System.out.println("ATM:     block activated on server side, instructing player to open gui");
			
			playerIn.openGui(Reference.MOD_ID, GuiIDs.ATM, worldIn, pos.getX(), pos.getY(), pos.getZ());
			
			return true;
		}
		
		return true;
		
	}
}
