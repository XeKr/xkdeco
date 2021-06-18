
package net.xekr.xkdeco.item;

import net.xekr.xkdeco.itemgroup.XkDecoBasicItemGroup;
import net.xekr.xkdeco.XkdecoModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.AxeItem;

@XkdecoModElements.ModElement.Tag
public class SteelAxeItem extends XkdecoModElements.ModElement {
	@ObjectHolder("xkdeco:steel_axe")
	public static final Item block = null;
	public SteelAxeItem(XkdecoModElements instance) {
		super(instance, 3);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new AxeItem(new IItemTier() {
			public int getMaxUses() {
				return 1164;
			}

			public float getEfficiency() {
				return 12f;
			}

			public float getAttackDamage() {
				return 7f;
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
		}, 1, -3f, new Item.Properties().group(XkDecoBasicItemGroup.tab)) {
		}.setRegistryName("steel_axe"));
	}
}
