package edu.bradley.llafollette.tutorial.gui;

import org.fusesource.jansi.Ansi.Color;

import edu.bradley.llafollette.tutorial.Reference;
import edu.bradley.llafollette.tutorial.inventory.ContainerATM;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.items.IItemHandler;

public class GUIAtm extends GuiContainer {
	
	private static final ResourceLocation JT_GUI_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/jewelry_table_gui.png");
	private static final int TEXT_COLOR = 0x404040;
	
	private final IItemHandler playerInventory;
	private final IItemHandler atmInventory;
	
	public GUIAtm(ContainerATM inventorySlotsIn) {
		super(inventorySlotsIn);
		
		System.out.println("JT:  GUI-ATM created. ");
		playerInventory = inventorySlotsIn.getPlayerInventory();
		atmInventory = inventorySlotsIn.getAtmInventory();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		
		this.mc.getTextureManager().bindTexture(JT_GUI_TEXTURE);
		
		final int x = (this.width - this.xSize) / 2;
		final int y = (this.height - this.ySize) / 2;
		
		this.drawTexturedModalRect(x, y, 0, 0,  this.xSize, this.ySize);

	}
	
	@Override 
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		TextComponentTranslation localized = new TextComponentTranslation("tile.jewelry_table.name", new Object[0]);
		
		this.fontRenderer.drawString(localized.getFormattedText(), 70, 9, Color.BLACK.value());
	}

}
