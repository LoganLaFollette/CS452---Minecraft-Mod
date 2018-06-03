package edu.bradley.llafollette.tutorial.particle;

import edu.bradley.llafollette.tutorial.Reference;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleNumbers extends Particle {
	
	private static final ResourceLocation NUMBERS_TEXTURE = new ResourceLocation(Reference.MOD_ID, "particles/Fire.png");
	private static final VertexFormat VERTEX_FORMAT = (new VertexFormat()).addElement(DefaultVertexFormats.POSITION_3F).
			addElement(DefaultVertexFormats.TEX_2F).addElement(DefaultVertexFormats.COLOR_4UB).addElement(DefaultVertexFormats.TEX_2S).
			addElement(DefaultVertexFormats.NORMAL_3B).addElement(DefaultVertexFormats.PADDING_1B);
	
	private int life;
	private final int lifeTime;
	private final TextureManager textureManager;
	private int delay;
	
	public ParticleNumbers(TextureManager textureManagerIn, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, 
			double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		
		final float ALPHA_MAX = 1f;
		
		this.particleRed = ((float) (Math.random() * 0.2) + 0.5f);
		this.particleGreen = ((float) (Math.random() * 0.2) + 0.5f);
		this.particleBlue = ((float) (Math.random() * 0.2) + 0.5f);
		this.particleAlpha = ALPHA_MAX;
		
		this.textureManager = textureManagerIn;
		this.lifeTime = 6 + this.rand.nextInt(4);
		this.delay = 0; // is this arbitrary?
	}
	
	@Override 
	public int getFXLayer() {
		return 3;
	}
	
	@Override 
	public int getBrightnessForRender(float partialTick) {
		final int FULL_BRIGHTNESS = 0xf0;
		return FULL_BRIGHTNESS;
	}
	
	public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		
		int i = this.life % 16;
		
		if (i <= 15) {
			this.textureManager.bindTexture(NUMBERS_TEXTURE);
			float f = (float) (i % 4) / 4.0F;
			float f1 = f + 0.25F;
			float f2 = (float) (i / 4) / 4.0F;
			float f3 = f2 + 0.25F;
			float f4 = 2.0F;
			float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX);
			float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY);
			float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ);
			
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.disableLighting();
			
			RenderHelper.disableStandardItemLighting();
			
			buffer.begin(7, VERTEX_FORMAT);
			
			buffer.pos((double)(f5 - rotationX * f4 - rotationXY * f4), (double) (f6 - rotationZ * f4),
					(double)(f7 - rotationYZ * f4 - rotationXZ * f4)).tex((double)f1,
					(double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			
			buffer.pos((double)(f5 - rotationX * f4 + rotationXY * f4), (double) (f6 + rotationZ * f4),
					(double)(f7 - rotationYZ * f4 + rotationXZ * f4)).tex((double)f1,
					(double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			
			buffer.pos((double)(f5 - rotationX * f4 + rotationXY * f4), (double) (f6 + rotationZ * f4),
					(double)(f7 + rotationYZ * f4 + rotationXZ * f4)).tex((double)f,
					(double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			
			buffer.pos((double)(f5 - rotationX * f4 - rotationXY * f4), (double) (f6 - rotationZ * f4),
					(double)(f7 + rotationYZ * f4 - rotationXZ * f4)).tex((double)f,
					(double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			
			Tessellator.getInstance().draw();
			GlStateManager.enableLighting();
			
		}
	}
	
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		
		move(motionX, motionY, motionZ);
		
		if(delay++ % 10 ==0) {
			++this.life;
		}
		
		if (this.life == this.lifeTime) {
			this.setExpired();
		}
	}
	

}
