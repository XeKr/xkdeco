
package net.xekr.xkdeco.item;

import net.xekr.xkdeco.itemgroup.XkDecoPunkItemGroup;
import net.xekr.xkdeco.XkdecoModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.HoeItem;

@XkdecoModElements.ModElement.Tag
public class SteelHoeItem extends XkdecoModElements.ModElement {
	@ObjectHolder("xkdeco:steel_hoe")
	public static final Item block = null;
	public SteelHoeItem(XkdecoModElements instance) {
		super(instance, 4);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new HoeItem(new IItemTier() {
			public int getMaxUses() {
				return 1164;
			}

			public float getEfficiency() {
				return 8f;
			}

			public float getAttackDamage() {
				return 0f;
			}

			public int getHarvestLevel() {
				return 3;
			}

			public int getEnchantability() {
				return 14;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(SteelIngotItem.block, (int) (1)));
			}
		}, 0, 0f, new Item.Properties().group(XkDecoPunkItemGroup.tab)) {
		}.setRegistryName("steel_hoe"));
	}
}
