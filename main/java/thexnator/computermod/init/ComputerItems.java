package thexnator.computermod.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thexnator.computermod.ComputerMod;
import thexnator.computermod.Reference;
import thexnator.computermod.items.ItemPlacer;
import thexnator.computermod.items.ItemTablet;

public class ComputerItems 
{
	
	public static Item laptopitem;
	public static Item tablet;

	public static void init() 
	{
		laptopitem = new ItemPlacer(ComputerBlocks.laptop).setUnlocalizedName("laptopitem").setCreativeTab(ComputerMod.tabComputer);
		tablet = new ItemTablet().setUnlocalizedName("tablet").setCreativeTab(ComputerMod.tabComputer);
	}
	
	public static void register()
	{
		GameRegistry.registerItem(tablet, tablet.getUnlocalizedName().substring(5));
	}
	
	public static void registerRenders()
	{
		registerRender(tablet);
	}
	
	public static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
}
