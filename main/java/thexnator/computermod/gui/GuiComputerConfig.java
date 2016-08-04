package thexnator.computermod.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import thexnator.computermod.handler.ConfigurationHandler;

public class GuiComputerConfig extends GuiConfig
{
	public GuiComputerConfig(GuiScreen parent)
	{
		super(parent, new ConfigElement(ConfigurationHandler.config.getCategory(ConfigurationHandler.CATEGORY_SETTINGS)).getChildElements(), "cm", false, false, GuiConfig.getAbridgedConfigPath(ConfigurationHandler.config.toString()));
	}
}
