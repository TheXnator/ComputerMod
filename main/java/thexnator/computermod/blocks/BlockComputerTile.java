package thexnator.computermod.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import thexnator.computermod.gui.inventory.ISimpleInventory;
import thexnator.computermod.util.InventoryUtil;

public abstract class BlockComputerTile extends BlockComputer implements ITileEntityProvider
{
	public BlockComputerTile(Material material)
	{
		super(material);
		this.isBlockContainer = true;
	}
	
	@Override
	public int getComparatorInputOverride(World world, BlockPos pos)
	{
		TileEntity tileEntity = world.getTileEntity(pos);
		return Container.calcRedstone(tileEntity);
	}
	
	@Override
	public boolean hasComparatorInputOverride() 
	{
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity instanceof IInventory)
		{
			IInventory inv = (IInventory) tileEntity;
			InventoryHelper.dropInventoryItems(world, pos, inv);
		}
		if (tileEntity instanceof ISimpleInventory)
		{
			ISimpleInventory inv = (ISimpleInventory) tileEntity;
			InventoryUtil.dropInventoryItems(world, pos, inv);
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam)
	{
		super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
	}
}
