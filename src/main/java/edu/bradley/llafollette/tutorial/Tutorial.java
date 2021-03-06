package edu.bradley.llafollette.tutorial;

import edu.bradley.llafollette.tutorial.proxy.IProxy;
import edu.bradley.llafollette.tutorial.tileentities.TileEntityATM;
import edu.bradley.llafollette.tutorial.Reference;
import edu.bradley.llafollette.tutorial.capabilities.BitcoinStorage;
import edu.bradley.llafollette.tutorial.capabilities.BitcoinWallet;
import edu.bradley.llafollette.tutorial.capabilities.IBitcoin;
import edu.bradley.llafollette.tutorial.gui.GUIHandler;
import edu.bradley.llafollette.tutorial.init.MessageManager;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)

public class Tutorial
{
	@Instance
	public static Tutorial instance;
	
	@SidedProxy(clientSide=Reference.CLIENT_PROXY_CLASS, serverSide=Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		CapabilityManager.INSTANCE.register(IBitcoin.class, new BitcoinStorage(), BitcoinWallet.class);
		System.out.println("preInit");
		
		MessageManager.registerMessages("tutorial_channel");
		
		GameRegistry.registerTileEntity(TileEntityATM.class, "atm");
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		System.out.println("init");
		proxy.registerKeyBindings();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		System.out.println("postInit");
	}
	
}
		
