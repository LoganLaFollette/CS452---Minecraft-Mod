package edu.bradley.llafollette.tutorial.blocks;

import edu.bradley.llafollette.tutorial.Tutorial;
import edu.bradley.llafollette.tutorial.init.IHasModel;
import edu.bradley.llafollette.tutorial.init.ModBlocks;
import edu.bradley.llafollette.tutorial.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel {
	
	public String name;
	
	public BlockBase(String name, Material material)  {
		super(material);
		
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name); //takes module id
		
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		//automatically adds itself to array
		ModBlocks.BLOCKS.add(this);
		ItemBlock itemBlock = new ItemBlock(this);
		itemBlock.setRegistryName(this.getRegistryName());
		ModItems.ITEMS.add(itemBlock);
	}
	

	@Override
	public void registerModels() {
		
		Item itemBlock = Item.getItemFromBlock(this);
		
		Tutorial.proxy.registerItemRenderer(itemBlock, 0, "inventory");
	}

}
