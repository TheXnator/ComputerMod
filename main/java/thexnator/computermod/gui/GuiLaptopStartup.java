package thexnator.computermod.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import thexnator.computermod.ComputerMod;
import thexnator.computermod.app.ApplicationSettings;
import thexnator.computermod.app.components.ApplicationBar;
import thexnator.computermod.app.components.Window;
import thexnator.computermod.util.GuiHelper;

public class GuiLaptopStartup extends GuiScreen
{
	public static final int ID = 0;
	
	public static final ResourceLocation LAPTOP_STARTUP_GUI = new ResourceLocation("cm:textures/gui/laptopstartup.png");
	
	private int WIDTH = 316;
	private int HEIGHT = 200;
		
	private int tileX, tileY, tileZ;
	private int lastMouseX, lastMouseY;
	
	public int waitTimer = 10;
	
	private EntityPlayer player;
	private World world;
	private GuiLaptop laptop;

	public GuiLaptopStartup(NBTTagCompound data, int tileX, int tileY, int tileZ)
	{
		this.tileX = tileX;
		this.tileY = tileY;
		this.tileZ = tileZ;
	}
	
	@Override
	public void initGui() 
	{
		super.initGui();
		int posX = (width - WIDTH) / 2;
		int posY = (height - HEIGHT) / 2;
		
		Keyboard.enableRepeatEvents(true);
	}
	
	@Override
	public boolean doesGuiPauseGame() 
	{
		return false;
	}
	
	@Override
	public void onGuiClosed() 
	{
		Keyboard.enableRepeatEvents(false);
	}
	

	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		this.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(LAPTOP_STARTUP_GUI);
				
		int posX = (width - WIDTH) / 2;
		int posY = (height - HEIGHT) / 2;

		/* SCREEN */
		GuiHelper.drawModalRectWithUV(posX - 34, posY - 12, 0, 0, 1858, 632, 84, 64);

		waitTimer--;
		if(waitTimer <= 0)
		{
			mc.displayGuiScreen(null);
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException 
	{
		this.lastMouseX = mouseX;
		this.lastMouseY = mouseY;
				
		int posX = (width - WIDTH) / 2;
		int posY = (height - HEIGHT) / 2;
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state)
	{
		super.mouseReleased(mouseX, mouseY, state);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
		super.keyTyped(typedChar, keyCode);
	}
	
	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) 
	{
		int posX = (width - WIDTH) / 2;
		int posY = (height -  HEIGHT) / 2;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException 
	{

	}
	
	public void loadFinish()
	{
		if(waitTimer <= 0)
		{
			player.openGui(ComputerMod.instance, 0, world, (int) player.posX, (int) player.posY, (int) player.posZ);
		}
	}
	
}

