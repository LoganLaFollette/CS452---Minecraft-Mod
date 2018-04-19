package edu.bradley.llafollette.tutorial.init;

import java.util.ArrayList;
import java.util.List;

import edu.bradley.llafollette.tutorial.Reference;
import edu.bradley.llafollette.tutorial.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=Reference.MOD_ID)
public class ModBlocks {

	public static List<Block> BLOCKS = new ArrayList<Block>();
	
	public static Block CHEESE_BLOCK = new BlockBase("cheese_block", Material.IRON);
	public static Block ANTMINER_BLOCK = new BlockBase("antminer_block", Material.CIRCUITS);
	
	
}
