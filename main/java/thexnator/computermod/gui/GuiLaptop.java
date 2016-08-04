package thexnator.computermod.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import thexnator.computermod.app.ApplicationNotePad;
import thexnator.computermod.app.ApplicationSettings;
import thexnator.computermod.app.components.ApplicationBar;
import thexnator.computermod.app.components.Window;
import thexnator.computermod.util.GuiHelper;

public class GuiLaptop extends GuiScreen
{
	public static final int ID = 0;
	
	public static final ResourceLocation LAPTOP_GUI = new ResourceLocation("cm:textures/gui/laptop.png");
	public static final List<ResourceLocation> WALLPAPERS = new ArrayList<ResourceLocation>();
	
	private int WIDTH = 316;
	private int HEIGHT = 200;
	
	private ApplicationBar bar;
	private ApplicationSettings settings;
	private ApplicationNotePad notepad;
	private Window window;
	private NBTTagCompound data;
	
	public static int currentWallpaper;
	private int tileX, tileY, tileZ;
	private int lastMouseX, lastMouseY;
	
	private boolean dragging = false;
	private boolean dirty = false;
	
	private GuiButton nextWallpaper;
	private GuiButton prevWallpaper;
	
	public static boolean timeGame = true;
	
	public static boolean settingsOpen = false;
	public static boolean notepadOpen = false;

	public GuiLaptop(NBTTagCompound data, int tileX, int tileY, int tileZ)
	{
		this.data = data;
		this.tileX = tileX;
		this.tileY = tileY;
		this.tileZ = tileZ;
		//this.currentWallpaper = data.getInteger("CurrentWallpaper");
		if(currentWallpaper < 0 || currentWallpaper >= WALLPAPERS.size())
		{
			this.currentWallpaper = 0;
		}
	}
	
	@Override
	public void initGui() 
	{
		super.initGui();
		int posX = (width - WIDTH) / 2;
		int posY = (height - HEIGHT) / 2;
		
		nextWallpaper = new GuiButton(0, posX + 7, posY + 10, ">");
		prevWallpaper = new GuiButton(0, posX - 3, posY + 10, "<");
		
		Keyboard.enableRepeatEvents(true);
		bar = new ApplicationBar();
		bar.init(buttonList, posX + 10, posY + HEIGHT - 28);
		
		settings = new ApplicationSettings();
		notepad = new ApplicationNotePad();
		
		buttonList.add(nextWallpaper);
		buttonList.add(prevWallpaper);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void onGuiClosed() 
	{
		Keyboard.enableRepeatEvents(false);
		closeApplication();
		
		//data.setInteger("CurrentWallpaper", this.currentWallpaper);
		
		if(dirty)
		{
			//PacketHandler.INSTANCE.sendToServer(new MessageSaveData(tileX, tileY, tileZ, data));
		}
		
		bar = null;
		window = null;
		data = null;
	}
	

	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(LAPTOP_GUI);
				
		int posX = (width - WIDTH) / 2;
		int posY = (height - HEIGHT) / 2;

		/* SCREEN */
		GuiHelper.drawModalRectWithUV(posX - 34, posY - 12, 0, 0, 1858, 632, 84, 64);

		this.drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 319, 162);
		
		/* BACKGROUND */
		this.mc.getTextureManager().bindTexture(WALLPAPERS.get(currentWallpaper));
		this.drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 319, 162);
		
//		if(this.settings != null)
//		{
//			settings.render(this, mc, getSettingsX(), getSettingsY(), mouseX, mouseY);
//		}
		
		nextWallpaper.width = 10;
		nextWallpaper.height = 10;
		
		prevWallpaper.width = 10;
		prevWallpaper.height = 10;
		
		bar.render(this, mc, posX + 10, posY + HEIGHT - 28, mouseX, mouseY);
		if(settingsOpen)
		{
			settings.render(this, mc, posX, posY, mouseX, mouseY, buttonList);
		}
		if(notepadOpen)
		{
			notepad.render(this, mc, posX, posY);
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		this.lastMouseX = mouseX;
		this.lastMouseY = mouseY;
				
		int posX = (width - WIDTH) / 2;
		int posY = (height - HEIGHT) / 2;
		
		this.bar.handleClick(this, posX + 10, posY + HEIGHT - 28, mouseX, mouseY, mouseButton);
		
		if(isMouseInside(mouseX, mouseY, posX + 13, posY + HEIGHT - 30, posX + 30, posY + HEIGHT - 8))
		{
			notepad.init(buttonList, posX, posY);
			this.bar.handleClick(this, posX + 10, posY + HEIGHT - 28, mouseX, mouseY, mouseButton);
		}
		
		if(settings != null)
		{
			int windowX = getSettingsX();
			int windowY = getSettingsY();
			
			this.settings.handleClick(this, windowX, windowY, mouseX, mouseY, mouseButton);
			
			if(mouseX >= windowX + settings.offsetX + 1 && mouseX <= windowX + settings.offsetX + settings.width - 13)
			{
				if(mouseY >= windowY + settings.offsetX + 1 && mouseY <= windowY + settings.offsetY + 11)
				{
					this.dragging = true;
					return;
				}
			}
		}
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		this.dragging = false;
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
		if(window != null)
		{
			this.window.handleKeyTyped(typedChar, keyCode);
		}
		super.keyTyped(typedChar, keyCode);
	}
	
	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		int posX = (width - WIDTH) / 2;
		int posY = (height -  HEIGHT) / 2;
		if(dragging && settings != null)
		{
			if(mouseX >= posX + 10 && mouseX <= posX + WIDTH - 20 && mouseY >= posY + 10 && mouseY <= posY + HEIGHT - 20)
			{
				settings.handleDrag(this, getSettingsX(), getSettingsY(), -(lastMouseX = mouseX), -(lastMouseY - mouseY), posX + 10, posY + 10);
			}
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
//		if(window != null)
//		{
//			window.handleButtonClick(this, button);
//		}
		if (button == this.nextWallpaper) 
		{
	        nextWallpaper();
	        System.out.println("nextwallpaper");
	    }
		if (button == this.prevWallpaper) 
		{
			prevWallpaper();
			System.out.println("prevwallpaper");
	    }
		if(button == ApplicationSettings.gameTime)
		{
			timeGame = true;
		}
		if(button == ApplicationSettings.realTime)
		{
			timeGame = false;
		}
		if(button == ApplicationSettings.btnClose)
		{
			settingsOpen = false;
			if(buttonList.contains(ApplicationSettings.btnClose) && buttonList.contains(ApplicationSettings.gameTime) && buttonList.contains(ApplicationSettings.realTime))
			{
				buttonList.remove(ApplicationSettings.btnClose);
				buttonList.remove(ApplicationSettings.gameTime);
				buttonList.remove(ApplicationSettings.realTime);
			}
		}
		if(button == ApplicationNotePad.btnClose)
		{
			notepadOpen = false;
			if(buttonList.contains(ApplicationNotePad.btnClose) && buttonList.contains(ApplicationNotePad.btnSave))
			{
				buttonList.remove(ApplicationNotePad.btnClose);
				buttonList.remove(ApplicationNotePad.btnSave);
			}
		}
	}
	
//	public void openApplication(Application app)
//	{
//		closeApplication();
//		window = new Window(app);
//		window.init(buttonList, getWindowX(), getWindowY());
//		if(data.hasKey(app.getID()))
//		{
//			app.load(data.getCompoundTag(app.getID()));
//		}
//		Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
//	}
	
	public void closeApplication()
	{
		if(window != null)
		{
			if(window.save(data))
			{
				dirty = true;
			}
			//window.handleClose(buttonList);
			window = null;
		}
		
	}
	public int getSettingsX()
	{
		if(settings != null)
		{
			int posX = (width - WIDTH) / 2;
			return posX + (WIDTH - settings.width) / 2;
		}
		return -1;
	}
	
	public int getSettingsY()
	{
		if(settings != null)
		{
			int posY = (height - HEIGHT) / 2;
			return posY + 10 + (HEIGHT - 38 - settings.height) / 2;
		}
		return -1;
	}
	
	public static void nextWallpaper()
	{
		if(currentWallpaper + 1 < WALLPAPERS.size())
		{
			currentWallpaper++;
		}
	}
	public static void prevWallpaper()
	{
		if(currentWallpaper - 1 >= 0)
		{
			currentWallpaper--;
		}
	}
	
	public boolean isMouseInside(int mouseX, int mouseY, int x1, int y1, int x2, int y2)
	{
		return mouseX >= x1 && mouseX <= x2 && mouseY >= y1 && mouseY <= y2;
	}
	
	public static void addWallpaper(ResourceLocation wallpaper)
	{
		if(wallpaper != null)
		{
			WALLPAPERS.add(wallpaper);
		}
	}
	
	public static boolean isSettingsOpen() 
	{
		return settingsOpen;
	}
	
	public static boolean isNotePadOpen() 
	{
		return notepadOpen;
	}
	
}
