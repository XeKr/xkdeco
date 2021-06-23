
package net.xekr.xkdeco.itemgroup;

import net.xekr.xkdeco.block.FishTankBlock;
import net.xekr.xkdeco.XkdecoModElements;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

@XkdecoModElements.ModElement.Tag
public class XkDecoBasicItemGroup extends XkdecoModElements.ModElement {
	public XkDecoBasicItemGroup(XkdecoModElements instance) {
		super(instance, 234);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabxk_deco_basic") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(FishTankBlock.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
