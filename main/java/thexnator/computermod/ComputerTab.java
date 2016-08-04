package thexnator.computermod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class ComputerTab extends CreativeTabs {

	public ComputerTab(String label) {
		super(label);
		this.setBackgroundImageName("computer.png");
	}
	
	@Override
	public Item getTabIconItem() {
		return Items.redstone;
	}

}
