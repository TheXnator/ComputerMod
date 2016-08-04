package thexnator.computermod.app;

import java.awt.Color;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import thexnator.computermod.app.components.Window;
import thexnator.computermod.gui.GuiLaptop;
import thexnator.computermod.util.GuiHelper;

public class ApplicationSettings// extends Window
{	
	public static final ResourceLocation WINDOW_GUI = new ResourceLocation("cm:textures/gui/settings.png");
	public static GuiButton btnClose;
	public static GuiButton gameTime;
	public static GuiButton realTime;
	
	private int WIDTH = 316;
	private int HEIGHT = 200;
	
	public int width, height;
	public int offsetX, offsetY;
	
	//private Window window;
	
	public ApplicationSettings()
	{
		//Window window = new Window();
		this.setWidth(10);
		this.setHeight(10);
	}
	
	private void setWidth(int width)
	{
		this.width = width + 2;
		if(this.width > WIDTH)
		{
			this.width = WIDTH;
		}
	}
	
	private void setHeight(int height)
	{
		this.height = height + 14;
		if(this.height > HEIGHT)
		{
			this.height = HEIGHT;
		}
	}
	
	//@Override
	public void init(List<GuiButton> buttonList, int x, int y)
	{
		//window.init(buttonList, x, y);
		btnClose = new GuiButton(4, x + offsetX + width - 1, y + offsetY + 21, 12, 12, "X");
		gameTime = new GuiButton(0, x + offsetX + width + 3, y + offsetY + 35, "Game Time");
		realTime = new GuiButton(0, x + offsetX + width + 53, y + offsetY + 35, "Real Time");
		buttonList.add(btnClose);
		buttonList.add(gameTime);
		buttonList.add(realTime);
	}
	
	//@Override
	public void render(GuiLaptop gui, Minecraft mc, int x, int y, int mouseX, int mouseY, List<GuiButton> buttonList)
	{	
		if(gui.settingsOpen)
		{
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableBlend();
			mc.getTextureManager().bindTexture(WINDOW_GUI);

			GuiHelper.drawModalRectWithUV(x + offsetX + 10, y + offsetY + 21, 0, 0, width + 200, 100, 12, 8);
			
			mc.fontRendererObj.drawString("Settings", x + offsetX + 3, y + offsetY + 3, Color.WHITE.getRGB(), true);
		
			GlStateManager.disableBlend();
						
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			
			gameTime.height = 13;
			gameTime.width = 51;
			
			realTime.height = 13;
			realTime.width = 51;
		}
		
	}
	
	public void handleDrag(GuiScreen gui, int x, int y, int mouseDX, int mouseDY, int screenStartX, int screenStartY)
	{
		int newX = x + offsetX + mouseDX;
		int newY = y + offsetY + mouseDY;
		
		if(newX <= screenStartX)
		{
			this.offsetX = screenStartX - x;
		}
		else if(newX + width > screenStartX + 296)
		{
			this.offsetX = x - screenStartX;
		}
		else
		{
			this.offsetX += mouseDY;
		}
		if(newY < screenStartY)
		{
			this.offsetY = screenStartY - y;
		}
		else if(newX + height > screenStartY + 162)
		{
			this.offsetY = y - screenStartY;
		}
		else
		{
			this.offsetY += mouseDY;
		}
		
		updateComponents(x, y);
		updateButtons(x + offsetX + 1, y + offsetY + 13);
	}
	
	//@Override
	public void handleButtonClick(GuiButton button)
	{
		
	}
	
	public void handleClick(GuiLaptop gui, int x, int y, int mouseX, int mouseY, int mouseButton)
	{
//		app.handleClick(gui, mouseX, mouseY, mouseButton);
	}
	
	//@Override
	public void updateButtons(int x, int y)
	{
		
	}
	
	public void updateComponents(int x, int y)
	{
		btnClose.xPosition = x + offsetX + width - 12;
		btnClose.yPosition = y + offsetY + 1;
	}
	
	//@Override
	public void hideButtons(List<GuiButton> buttons)
	{
		
	}
	
	//@Override
	public void load(NBTTagCompound tagCompound)
	{
		
	}
	
	//@Override
	public boolean save(NBTTagCompound tagCompound)
	{
		return false;
	}
}
