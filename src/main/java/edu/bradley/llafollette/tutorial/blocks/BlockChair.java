package edu.bradley.llafollette.tutorial.blocks;
 
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;

public class BlockChair extends BlockBase {

	public BlockChair(String name, Material material) {
		super(name, material);
	}

	@Override 
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override 
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	//if we want transparency
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
}
