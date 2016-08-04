package thexnator.computermod;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import thexnator.computermod.app.ApplicationNotePad;
import thexnator.computermod.app.components.ApplicationBar;
import thexnator.computermod.gui.GuiHandler;
import thexnator.computermod.gui.GuiLaptop;
import thexnator.computermod.handler.ConfigurationHandler;
import thexnator.computermod.init.ComputerBlocks;
import thexnator.computermod.init.ComputerItems;
import thexnator.computermod.init.ComputerTileEntities;
import thexnator.computermod.network.PacketHandler;
import thexnator.computermod.proxy.CommonProxy;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class ComputerMod 
{
	@Instance(Reference.MOD_ID)
	public static ComputerMod instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static final ComputerTab tabComputer = new ComputerTab("tabComputer");
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		ComputerBlocks.init();
		ComputerBlocks.register();
		ComputerItems.init();
		ComputerItems.register();
		
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		
		PacketHandler.init();
		
		proxy.preInit();
		
		System.out.println("ComputerMod PreInitialized!");
    }
	
	@EventHandler
    public void init(FMLInitializationEvent event)
    {
		proxy.registerRenders();
		proxy.init();
		
		ComputerTileEntities.register();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		System.out.println("ComputerMod Initialized!");
    }
	
	@EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
		//ApplicationBar.registerApplication(new ApplicationNotePad());
		//ApplicationBar.registerApplication(new ApplicationSettings());
		
		GuiLaptop.addWallpaper(new ResourceLocation("cm:textures/gui/background.png"));
		GuiLaptop.addWallpaper(new ResourceLocation("cm:textures/gui/background2.png"));
		GuiLaptop.addWallpaper(new ResourceLocation("cm:textures/gui/background3.png"));
		GuiLaptop.addWallpaper(new ResourceLocation("cm:textures/gui/background4.png"));
		GuiLaptop.addWallpaper(new ResourceLocation("cm:textures/gui/background5.png"));
		
		System.out.println("ComputerMod PostInitialized!");
    }

}