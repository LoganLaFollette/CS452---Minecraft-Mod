package edu.bradley.llafollette.tutorial.capabilities;

import edu.bradley.llafollette.tutorial.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=Reference.MOD_ID)
public class CapabilityHandler {

	public static final ResourceLocation BITCOIN_CAP = new ResourceLocation(Reference.MOD_ID, "bitcoin");
	
//	@SubscribeEvent
//	public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
//		
//		if (!(event.getEntity() instanceof EntityPlayer)) return;
//		event.addCapability(BITCOIN_CAP, new BitcoinProvider());
//	}
}
