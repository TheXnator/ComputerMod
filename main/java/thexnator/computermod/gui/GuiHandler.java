package thexnator.computermod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	public static final int LAPTOP_GUI = 0;
	public static final int LAPTOP_STARTUP_GUI = 1;
	public static final int TABLET_GUI = 2;
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		if (id == LAPTOP_GUI)
	        return new GuiLaptop(null, x, y, z);
		if (id == LAPTOP_STARTUP_GUI)
	        return new GuiLaptopStartup(null, x, y, z);
		if (id == TABLET_GUI)
	        return new GuiTablet(null, x, y, z);
//		if (id == TABLET_STARTUP_GUI)
//	        return new GuiTabletStartup(null, x, y, z);
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		if (id == LAPTOP_GUI)
	        return new GuiLaptop(null, x, y, z);
		if (id == LAPTOP_STARTUP_GUI)
			return new GuiLaptopStartup(null, x, y, z);
		if (id == TABLET_GUI)
	        return new GuiTablet(null, x, y, z);
//		if (id == TABLET_STARTUP_GUI)
//			return new GuiTabletStartup(null, x, y, z);
	    return null;
	}
	
}
