
package net.xekr.xkdeco.itemgroup;

import net.xekr.xkdeco.block.GreekIonicColumnHeadBlock;
import net.xekr.xkdeco.XkdecoModElements;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

@XkdecoModElements.ModElement.Tag
public class XkDecoWesternItemGroup extends XkdecoModElements.ModElement {
	public XkDecoWesternItemGroup(XkdecoModElements instance) {
		super(instance, 321);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabxk_deco_western") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(GreekIonicColumnHeadBlock.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
