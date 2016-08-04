package thexnator.computermod.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {

	public static Configuration config;

	public static final String CATEGORY_RECIPE_SETTINGS = "recipe-settings";
	public static final String CATEGORY_API = "recipe-api";
	public static final String CATEGORY_SETTINGS = "settings";

	public static String[] items = {};
	public static boolean canDisplay = true;
	public static boolean hasDisplayedOnce = false;
	public static boolean api_debug = true;
	
	public static boolean oven_1 = true, oven_2 = true, oven_3 = true, oven_4 = true, oven_5 = true, oven_6 = true, oven_7 = true;
	
	public static boolean wash_1 = true, wash_2 = true, wash_3 = true, wash_4 = true, wash_5 = true, wash_6 = true, wash_7 = true, wash_8 = true, wash_9 = true, wash_10 = true;
	public static boolean wash_11 = true, wash_12 = true, wash_13 = true, wash_14 = true, wash_15 = true, wash_16 = true, wash_17 = true, wash_18 = true, wash_19 = true, wash_20 = true;

	public static void init(File file)
	{
		if (config == null)
		{
			config = new Configuration(file);
			loadConfig(false);
		}
	}

	public static void loadConfig(boolean shouldChange)
	{
		
		if (config.hasChanged() && shouldChange)
		{
			
		}
		config.save();
	}

	private static void updateEnabledRecipes()
	{
		
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs)
	{
		if (eventArgs.modID.equals("fm"))
		{
			loadConfig(true);
		}
	}
}
