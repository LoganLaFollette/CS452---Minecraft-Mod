package edu.bradley.llafollette.tutorial.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockNonOpaque {

	public BlockNonOpaque(String name, Material material) {
		// TODO Auto-generated constructor stub
	}

	public boolean hasTileEntity(IBlockState state) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		// TODO Auto-generated method stub
		return false;
	}

}
