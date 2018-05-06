package edu.bradley.llafollette.tutorial.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockTileEntity<TE extends TileEntity> extends BlockNonOpaque {

	public BlockTileEntity(String name, Material material) {
		super(name,material);
	}
	
	@Override 
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	public abstract TileEntity createTileEntity(World world, IBlockState state);
	
	@SuppressWarnings("unchecked")
	@Nullable
	protected TE getTileEntity(final IBlockAccess world, final BlockPos pos) {
		System.out.println("ATM:     get tile entity at location " + pos);
		
		return (TE) world.getTileEntity(pos);
	}
}
