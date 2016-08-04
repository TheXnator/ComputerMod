package thexnator.computermod.app.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.sun.glass.ui.Application;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import thexnator.computermod.app.ApplicationSettings;
import thexnator.computermod.gui.GuiTablet;
import thexnator.computermod.util.GuiHelper;

public class TabletApplicationBar //extends Component
{
	public static final ResourceLocation APP_BAR_GUI = new ResourceLocation("cm:textures/gui/tablet_application_bar.png");
	
	//public static List<GuiButton> buttonList;
	
	private static final List<Application> APPS = new ArrayList<Application>();
	//private static Application settings = new ApplicationSettings();
	
	private ApplicationSettings settings;
	
	public GuiButton btnLeft;
	public GuiButton btnRight;
	
	//@Override
	public void init(List<GuiButton> buttonList, int posX, int posY)
	{
		btnLeft = new GuiButton(0, posX - 10, posY, "<");
		btnRight = new GuiButton(0, posX + 260, posY, ">");
		buttonList.add(btnLeft);
		buttonList.add(btnRight);
	}

	//@Override
	public void render(GuiTablet gui, Minecraft mc, int x, int y, int mouseX, int mouseY)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
		GlStateManager.enableBlend();
		mc.getTextureManager().bindTexture(APP_BAR_GUI);
		//gui.drawTexturedModalRect(x, y, 0, 0, 1, 18);
		GuiHelper.drawModalRectWithUV(x - 12, y - 2, 0, 0, 1200, 18, 219, 6);
		//gui.drawTexturedModalRect(x - 12, y, 2, 0, 33, 18);
		
		if((int)Minecraft.getMinecraft().theWorld.getWorldTime() >= 0 && (int)Minecraft.getMinecraft().theWorld.getWorldTime() <= 12000)
		{
			GlStateManager.enableBlend();
			mc.getTextureManager().bindTexture(APP_BAR_GUI);
			//GuiHelper.drawModalRectWithUV(x + 10, y, 45, 32, 15, 15, 15, 15);
			GlStateManager.disableBlend();
		}
		
		if((int)Minecraft.getMinecraft().theWorld.getWorldTime() >= 12000)
		{
			GlStateManager.enableBlend();
			mc.getTextureManager().bindTexture(APP_BAR_GUI);
			//GuiHelper.drawModalRectWithUV(x, y, 30, 32, 15, 15, 15, 15);
			GlStateManager.disableBlend();
		}
		
		GlStateManager.disableBlend();
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderHelper.disableStandardItemLighting();
		
		if(GuiTablet.timeGame = true)
		{
			mc.fontRendererObj.drawString(timeToString(Minecraft.getMinecraft().theWorld.getWorldTime()), x + 277, y + 3, Color.BLACK.getRGB());
		}
		else
		{
			mc.fontRendererObj.drawString(timeToString(Minecraft.getSystemTime()), x + 277, y + 3, Color.BLACK.getRGB());
		}
		
		btnLeft.width = 10;
		btnLeft.height = 15;
		
		btnRight.width = 10;
		btnRight.height = 15;
		
		mc.getTextureManager().bindTexture(APP_BAR_GUI);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
		//GlStateManager.enableBlend();
		GuiHelper.drawModalRectWithUV(x + 3, y - 1, 0, 17, 16, 15, 3, 4);
		if(isMouseInside(mouseX, mouseY, x + 3, y - 2, x + 20, y + 14))
		{
			GuiHelper.drawModalRectWithUV(x + 2, y - 1, 0, 24, 17, 16, 3, 5);
		}
		//GlStateManager.disableBlend();
		
		for(int i = 0; i < APPS.size(); i++)
		{
			gui.drawTexturedModalRect(x + 18 + i * 18, y + 2, 0, 46, 14, 14);
		}
		//NOTES ICON
		gui.drawTexturedModalRect(x + 182, y + 2, 15, 32, 15, 15);
		GuiHelper.drawModalRectWithUV(x, y, 15, 32, 15, 15, 150, 150);
		if(isMouseInside(mouseX, mouseY, x + 181, y + 1, x + 236, y + 16))
		{
			gui.drawTexturedModalRect(x + 181, y + 1, 0, 30, 16, 16);
			//gui.drawHoveringText(/*Arrays.asList(settings.getDisplayName())*/"Cheese", mouseX, mouseY);
		}
		//DINOINFO ICON
		gui.drawTexturedModalRect(x + 200, y + 2, 15, 32, 15, 15);
		GuiHelper.drawModalRectWithUV(x, y, 15, 32, 15, 15, 150, 150);
		if(isMouseInside(mouseX, mouseY, x + 199, y + 1, x + 254, y + 16))
		{
			gui.drawTexturedModalRect(x + 199, y + 1, 0, 30, 16, 16);
			//gui.drawHoveringText(/*Arrays.asList(settings.getDisplayName())*/"Cheese", mouseX, mouseY);
		}
		
		//OTHER APP ICONS
		if(isMouseInside(mouseX, mouseY, x + 18, y + 1, x + 236, y + 16))
		{
			int appIndex = (mouseX - x - 1) / 16 - 1;
			if(appIndex <= 8 && appIndex < APPS.size())
			{
				gui.drawTexturedModalRect(x + appIndex * 16 + 17, y + 1, 0, 30, 16, 16);
//				gui.drawHoveringText(Arrays.asList(APPS.get(appIndex).getDisplayName()), mouseX, mouseY);
			}
		}
	}
	
	//@Override
	public void handleClick(GuiTablet gui, int x, int y, int mouseX, int mouseY, int mouseButton)
	{
		if(isMouseInside(mouseX, mouseY, x + 3, y - 2, x + 20, y + 14))
		{
			gui.notepadOpen = true;
			return;
		}
		if(isMouseInside(mouseX, mouseY, x + 18, y + 1, x + 236, y + 16))
		{
			int appIndex = (mouseX - x - 1) / 16 - 1;
			if(appIndex < APPS.size())
			{
				return;
			}
		}
	}
	
	public boolean isMouseInside(int mouseX, int mouseY, int x1, int y1, int x2, int y2)
	{
		return mouseX >= x1 && mouseX <= x2 && mouseY >= y1 && mouseY <= y2;
	}
	
	public static void registerApplication(Application app)
	{
		if(app != null)
		{
			APPS.add(app);
		}
	}
	
	public String timeToString(long time)
	{
		int hours = (int) ((Math.floor(time / 1000.0) + 6) % 24);
		int minutes = (int) Math.floor((time % 1000) / 1000.0 * 60);
		return String.format("%02d:%02d", hours, minutes);
	}
}
