package edu.bradley.llafollette.tutorial.particle;

import java.util.Random;

import edu.bradley.llafollette.tutorial.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleStar extends Particle {

	private final ResourceLocation starTexture = new ResourceLocation(Reference.MOD_ID, "particles/star");
	
	public ParticleStar(World worldIn, double x, double y, double z, double speedX, double speedY, double speedZ) {
		super(worldIn, x, y, z, speedX, speedY, speedZ);
		
		final float ALPHA_MAX = 1f;
		
		this.particleRed = ((float) (Math.random() * 0.2) + 0.5f);
		this.particleGreen = ((float) (Math.random() * 0.2) + 0.5f);
		this.particleBlue = ((float) (Math.random() * 0.2) + 0.5f);
		this.particleAlpha = ALPHA_MAX;
		
		this.particleMaxAge = 20;
		
		motionX = speedX;
		motionY = speedY;
		motionZ = speedZ;
		
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(starTexture.toString());
		
		setParticleTexture(sprite);
	}
	
	@Override 
	public int getFXLayer() {
		return 1;
	}
	
	@Override 
	public int getBrightnessForRender(float partialTick) {
		final int FULL_BRIGHTNESS = 0xf000f0;
		return FULL_BRIGHTNESS;
	}
	
	// Turn on depthmask so transparent objects render over this particle
	@Override 
	public boolean shouldDisableDepth() {
		return false;
	}
	
	private void generateParticles() {
		
		//dont run on the server
		if (!this.world.isRemote) {
			return;
		}
		
		Random rng = new Random();
		
		if (rng.nextInt(10) == 0) {
			double x = posX + 0.5; //pos.getX()
			double y = posY + 1.5; //pos.getY()
			double z = posZ + 0.5; //pos.getZ()
			
			double xSpeed = rng.nextGaussian() * 0.02d;
			double ySpeed = rng.nextGaussian() * 0.02d;
			double zSpeed = rng.nextGaussian() * 0.02d;
			
		    ParticleStar newEffect = new ParticleStar(this.world, x, y, z, xSpeed, ySpeed, zSpeed);
		    Minecraft.getMinecraft().effectRenderer.addEffect(newEffect);
		}
	}
}
