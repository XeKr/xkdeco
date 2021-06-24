
package net.xekr.xkdeco.itemgroup;

import net.xekr.xkdeco.block.SteelTilesBlock;
import net.xekr.xkdeco.XkdecoModElements;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

@XkdecoModElements.ModElement.Tag
public class XkDecoPunkItemGroup extends XkdecoModElements.ModElement {
	public XkDecoPunkItemGroup(XkdecoModElements instance) {
		super(instance, 464);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabxk_deco_punk") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(SteelTilesBlock.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
