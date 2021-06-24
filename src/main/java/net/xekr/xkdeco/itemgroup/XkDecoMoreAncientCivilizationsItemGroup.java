
package net.xekr.xkdeco.itemgroup;

import net.xekr.xkdeco.block.MayaChiseledStonebricksBlock;
import net.xekr.xkdeco.XkdecoModElements;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

@XkdecoModElements.ModElement.Tag
public class XkDecoMoreAncientCivilizationsItemGroup extends XkdecoModElements.ModElement {
	public XkDecoMoreAncientCivilizationsItemGroup(XkdecoModElements instance) {
		super(instance, 463);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabxk_deco_more_ancient_civilizations") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(MayaChiseledStonebricksBlock.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
