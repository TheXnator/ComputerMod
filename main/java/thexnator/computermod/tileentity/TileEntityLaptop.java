package thexnator.computermod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import thexnator.computermod.ComputerMod;
import thexnator.computermod.gui.GuiLaptopStartup;

public class TileEntityLaptop extends TileEntity implements ITickable
{
	public int rotation = 0;
	public boolean open = false;
	private EntityPlayer player;
	private World world;
	
	public void openClose()
	{
		open = !open;
		worldObj.markBlockForUpdate(pos);
	}
	
	@Override
	public void update() 
	{
		if(!open)
		{
			if(rotation > 0)
			{
				rotation -= 2;
			}
		}
		else
		{
			if(rotation < 112.5)
			{
				rotation += 2;
			}
		}

	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.open = compound.getBoolean("open");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setBoolean("open", open);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		NBTTagCompound tagCom = pkt.getNbtCompound();
		this.readFromNBT(tagCom);
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tagCom = new NBTTagCompound();
		this.writeToNBT(tagCom);
		return new S35PacketUpdateTileEntity(pos, getBlockMetadata(),tagCom);
	}
	
	@Override
	public double getMaxRenderDistanceSquared() {
		return 16384;
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return INFINITE_EXTENT_AABB;
	}
}
