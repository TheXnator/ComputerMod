package thexnator.computermod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thexnator.computermod.ComputerMod;
import thexnator.computermod.tileentity.TileEntityLaptop;

public class ItemTablet extends Item
{
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) 
	{
		if(!playerIn.isSneaking())
		{
			if (worldIn.isRemote)
			{
				playerIn.openGui(ComputerMod.instance, 2, worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
				System.out.println("opened");
	        }
		}
		
		return super.onItemRightClick(itemStackIn, worldIn, playerIn);
	}
}
