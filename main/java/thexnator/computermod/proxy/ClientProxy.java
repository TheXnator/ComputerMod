package thexnator.computermod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thexnator.computermod.init.ComputerBlocks;
import thexnator.computermod.init.ComputerItems;
import thexnator.computermod.tileentity.TileEntityLaptop;
import thexnator.computermod.tileentity.render.LaptopRenderer;

public class ClientProxy extends CommonProxy
{
	public static boolean rendering = false;
	public static Entity renderEntity = null;
	public static Entity backupEntity = null;

	@Override
	public void registerRenders()
	{
		ComputerItems.registerRenders();
		ComputerBlocks.registerRenders();
	}
	
	@Override
	public EntityPlayer getClientPlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}

	@Override
	public boolean isSinglePlayer()
	{
		return Minecraft.getMinecraft().isSingleplayer();
	}

	@Override
	public boolean isDedicatedServer()
	{
		return false;
	}

	@Override
	public void preInit()
	{
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public void init()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaptop.class, new LaptopRenderer());
	}
	
	
	@SubscribeEvent
	public void onPrePlayerRender(RenderPlayerEvent.Pre event)
	{
		if(!rendering)
			return;
		
		if(event.entityPlayer == renderEntity)
		{
			this.backupEntity = Minecraft.getMinecraft().getRenderManager().livingPlayer;
			Minecraft.getMinecraft().getRenderManager().livingPlayer = renderEntity;
		}
	}

	@SubscribeEvent
	public void onPostPlayerRender(RenderPlayerEvent.Post event)
	{
		if(!rendering)
			return;
		
		if (event.entityPlayer == renderEntity)
		{
			Minecraft.getMinecraft().getRenderManager().livingPlayer = backupEntity;
			renderEntity = null;
		}
	}
}

