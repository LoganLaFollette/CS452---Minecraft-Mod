package edu.bradley.llafollette.tutorial.capabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class EventHandler {
	
     @SubscribeEvent
	 public void onPlayerLogsIn(PlayerLoggedInEvent event)

	 {

	 EntityPlayer player = event.player;
	 IBitcoin bitcoin = player.getCapability(BitcoinProvider.BITCOIN_CAP, null);
	 
	 String message = String.format("Hello there, you have §7%d§r Bitcoins in your Bitcoin Wallet.", (int) bitcoin.getCoins());
	 player.sendMessage(new TextComponentString(message));

	 }

	 
	 @SubscribeEvent

	 public void onPlayerSleep(PlayerSleepInBedEvent event)

	 {

	 EntityPlayer player = event.getEntityPlayer();
	 
	 if (player.world.isRemote) return;

	 
	 IBitcoin bitcoin = player.getCapability(BitcoinProvider.BITCOIN_CAP, null);
	 bitcoin.addCoins(70);

	 
	 String message = String.format("Your antminer has been hard at work overnight. You harvested 70 bitcoins, you have §7%d§r bitcoins in your wallet now!.", (int) bitcoin.getCoins());
	 player.sendMessage(new TextComponentString(message));

	 }
	 
	 @SubscribeEvent

	 public void onPlayerClone(PlayerEvent.Clone event)

	 {

	  EntityPlayer player = event.getEntityPlayer();

	  IBitcoin bitcoin = player.getCapability(BitcoinProvider.BITCOIN_CAP, null);

	  IBitcoin previousBitcoin = event.getOriginal().getCapability(BitcoinProvider.BITCOIN_CAP, null);

	  
	  bitcoin.setCoinAmount(previousBitcoin.getCoins());

	 }

}
