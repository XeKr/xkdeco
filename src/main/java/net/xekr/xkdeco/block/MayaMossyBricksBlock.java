
package net.xekr.xkdeco.block;

import net.xekr.xkdeco.itemgroup.XkDecoMoreAncientCivilizationsItemGroup;
import net.xekr.xkdeco.XkdecoModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.List;
import java.util.Collections;

@XkdecoModElements.ModElement.Tag
public class MayaMossyBricksBlock extends XkdecoModElements.ModElement {
	@ObjectHolder("xkdeco:maya_mossy_bricks")
	public static final Block block = null;
	public MayaMossyBricksBlock(XkdecoModElements instance) {
		super(instance, 281);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(XkDecoMoreAncientCivilizationsItemGroup.tab))
				.setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).harvestLevel(1)
					.harvestTool(ToolType.PICKAXE).setRequiresTool());
			setRegistryName("maya_mossy_bricks");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
