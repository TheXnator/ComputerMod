package thexnator.computermod.app;

import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import thexnator.computermod.gui.GuiLaptop;
import thexnator.computermod.util.GuiHelper;

public class ApplicationNotePad
{
	
public static final ResourceLocation WINDOW_GUI = new ResourceLocation("cm:textures/gui/settings.png");
	
	private int WIDTH = 316;
	private int HEIGHT = 200;
	
	public int width, height;
	public int offsetX, offsetY;
	
	public static GuiButton btnClose;
	public static GuiButton btnSave;
	public static GuiTextField textArea;
	
	public ApplicationNotePad()
	{
		//super("note_pad", Notepad, 160, 80);
		this.setWidth(100);
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
		textArea = new GuiTextField(0, Minecraft.getMinecraft().fontRendererObj, x + 13, y + 39, 105, 60);
		textArea.setFocused(true);
		btnSave = new GuiButton(0, x + offsetX + width - 90, y + offsetY + 106, 107, 12, "Save");
		btnClose = new GuiButton(4, x + offsetX + width - 92, y + offsetY + 25, 12, 12, "X");
		buttonList.add(btnClose);
		buttonList.add(btnSave);
	}
	
	//@Override
	public void onTick()
	{
		//textArea.onTick();
	}
	
	//@Override
	public void render(GuiLaptop gui, Minecraft mc, int x, int y)
	{
		if(gui.notepadOpen)
		{
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableBlend();
			mc.getTextureManager().bindTexture(WINDOW_GUI);

			GuiHelper.drawModalRectWithUV(x + offsetX + 10, y + offsetY + 25, 0, 0, width + 14, 100, 11, 8);
	
			GlStateManager.disableBlend();
		
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			textArea.drawTextBox();
		}
	}
	
	//@Override
	public void handleButtonClick(Gui gui, int mouseX, int mouseY, int mouseButton)
	{
		textArea.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	//@Override
	public void handleKeyTyped(char character, int code)
	{
		textArea.textboxKeyTyped(character, code);
	}
	
	//@Override
	public void handleButtonClick(GuiButton button)
	{
		if(button == btnSave)
		{
			//markDirty();
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
	
	public void updateComponents(int x, int y)
	{
		btnClose.xPosition = x + offsetX + width - 12;
		btnClose.yPosition = y + offsetY + 1;
	}
	
	//@Override
	public void updateButtons(int x, int y)
	{
		btnSave.xPosition = x + 105;
		btnSave.yPosition = y + 5;
		textArea.xPosition = x + 5;
		textArea.yPosition = y + 5;
	}
	
	//@Override
	public void hideButtons(List<GuiButton> buttons)
	{
		buttons.remove(btnSave);
	}
	
	//@Override
	public void load(NBTTagCompound tagCompound)
	{
		textArea.setText(tagCompound.getString("CurrentText"));
	}
	
	//@Override
	public void save(NBTTagCompound tagCompound)
	{
		tagCompound.setString("CurrentText", textArea.getText());
	}
}
