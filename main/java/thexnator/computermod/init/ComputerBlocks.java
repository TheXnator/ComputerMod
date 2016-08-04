package thexnator.computermod.init;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thexnator.computermod.ComputerMod;
import thexnator.computermod.Reference;
import thexnator.computermod.blocks.BlockArcadeMachine;
import thexnator.computermod.blocks.BlockLaptop;

public class ComputerBlocks {
	
	public static Block laptop;
	public static Block arcademachine;

	public static void init()
	{
		laptop = new BlockLaptop().setUnlocalizedName("laptop").setCreativeTab(ComputerMod.tabComputer);
		arcademachine = new BlockArcadeMachine().setUnlocalizedName("arcademachine").setCreativeTab(ComputerMod.tabComputer);
	}
	
	public static void register()
	{
		GameRegistry.registerBlock(laptop, laptop.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(arcademachine, arcademachine.getUnlocalizedName().substring(5));
	}
	
	public static void registerRenders()
	{
		registerRender(laptop);
		registerRender(arcademachine);
	}
	
	public static void registerRender(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
}
