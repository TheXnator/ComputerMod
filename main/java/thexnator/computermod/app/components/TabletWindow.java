package thexnator.computermod.app.components;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.sun.glass.ui.Application;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import thexnator.computermod.app.ApplicationSettings;
import thexnator.computermod.gui.GuiLaptop;
import thexnator.computermod.util.GuiHelper;

public class TabletWindow //extends Component
{
	public static final ResourceLocation WINDOW_GUI = new ResourceLocation("cm:textures/gui/tabletsettings.png");
	
	private int WIDTH = 316;
	private int HEIGHT = 200;
	
	//private Application app;
	public int width, height;
	public int offsetX, offsetY;
	
	private GuiButton btnClose;
	private ApplicationSettings settings;
	
	public TabletWindow()
	{
		//this.app = app;
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
	
//	@Override
	public void init(List<GuiButton> buttonList, int x, int y)
	{
		//btnClose = new GuiButtonClose(0, x + offsetX + width - 12, y + offsetY + 1);
		btnClose = new GuiButton(4, x + offsetX + width - 12, y + offsetY + 1, 5, 5, "X");
		buttonList.add(btnClose);
		System.out.println(buttonList);
		//settings.init(buttonList, x + offsetX + 1, y + offsetY + 13);
	}
	
	public void onTick()
	{
		//app.onTick();
	}
	
	//@Override
	public void render(GuiLaptop gui, Minecraft mc, int x, int y, int mouseX, int mouseY)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F);
		GlStateManager.enableBlend();
		mc.getTextureManager().bindTexture(WINDOW_GUI);
		
		gui.drawTexturedModalRect(x + offsetX, y + offsetY, 0, 0, 1, 13);
		gui.drawTexturedModalRect(x + offsetX + width - 13, y + offsetY, 2, 0, 13, 13);
		gui.drawTexturedModalRect(x + offsetX + width - 1, y + offsetY + height - 1, 14, 14, 1, 1);
		gui.drawTexturedModalRect(x + offsetX, y + offsetY + height - 1, 0, 14, 1, 1);
		
		GuiHelper.drawModalRectWithUV(x + offsetX + 1, y + offsetY, 1, 0, width - 14, 13, 1, 13);
		GuiHelper.drawModalRectWithUV(x + offsetX + width - 1, y + offsetY + 13, 14, 13, 1, height - 14, 1, 1);
		GuiHelper.drawModalRectWithUV(x + offsetX + 1, y + offsetY + height - 1, 1, 14, width - 2, 1, 13, 1);
		GuiHelper.drawModalRectWithUV(x + offsetX, y + offsetY + 13, 0, 13, 1, height - 14, 1, 1);
	
		GuiHelper.drawModalRectWithUV(x + offsetX + 1, y + offsetY + 13, 1, 13, width - 2, height - 14, 13, 1);
	
		//mc.fontRendererObj.drawString(app.getDisplayName(), x + offsetX + 3, y + offsetY + 3, Color.WHITE.getRGB(), true);
	
		GlStateManager.disableBlend();
		
		//app.render(gui, mc, x + offsetX + 1, y + offsetY + 13);
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	//@Override
	public void handleButtonClick(GuiLaptop laptop, GuiButton button)
	{
//		if(button.equals(btnClose))
//		{
//			laptop.closeApplication();
//		}
//		else
//		{
//			app.handleButtonClick(button);
//		}
	}
	
//	@Override
	public void handleClick(GuiLaptop gui, int x, int y, int mouseX, int mouseY, int mouseButton)
	{
//		app.handleClick(gui, mouseX, mouseY, mouseButton);
	}
//	
//	@Override
	public void handleKeyTyped(char character, int code)
	{
//		app.handleKeyTyped(character, code);
	}
	
//	@Override
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
		//app.updateButtons(x + offsetX + 1, y + offsetY + 13);
	}
	
	public void handleClose(List<GuiButton> buttons)
	{
		buttons.remove(btnClose);
		//app.hideButtons(buttons);
	}
	
//	@Override
	public void updateComponents(int x, int y)
	{
		btnClose.xPosition = x + offsetX + width - 12;
		btnClose.yPosition = y + offsetY + 1;
	}
	
//	@Override
	public boolean save(NBTTagCompound tagCompound)
	{
//		if(app.isDirty())
//		{
//			NBTTagCompound container = new NBTTagCompound();
//			app.save(container);
//			tagCompound.setTag(app.getID(), container);
//			return true;
//		}
		return false;
	}

}
