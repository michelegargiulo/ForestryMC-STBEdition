/*******************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 ******************************************************************************/
package forestry.core.items;

import java.util.Locale;

import forestry.core.config.Config;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import forestry.api.core.IModelManager;
import forestry.core.ModuleCore;
import forestry.core.utils.OreDictUtil;

public class ItemFruit extends ItemForestryFood {

	public enum EnumFruit {
		CHERRY(OreDictUtil.CROP_CHERRY),
		WALNUT(OreDictUtil.CROP_WALNUT),
		CHESTNUT(OreDictUtil.CROP_CHESTNUT),
		LEMON(OreDictUtil.CROP_LEMON),
		PLUM(OreDictUtil.CROP_PLUM),
		DATES(OreDictUtil.CROP_DATE),
		PAPAYA(OreDictUtil.CROP_PAPAYA),

		// New crops
		ALMOND(OreDictUtil.CROP_ALMOND),
		APRICOT(OreDictUtil.CROP_APRICOT),
		AVOCADO(OreDictUtil.CROP_AVOCADO),
		BANANA(OreDictUtil.CROP_BANANA),
		BREADFRUIT(OreDictUtil.CROP_BREADFRUIT),
		CASHEW(OreDictUtil.CROP_CASHEW),
		CEDAR(OreDictUtil.CROP_CEDAR),
		DRAGONFRUIT(OreDictUtil.CROP_DRAGONFRUIT),
		DURIAN(OreDictUtil.CROP_DURIAN),
		FIG(OreDictUtil.CROP_FIG),
		GOOSEBERRY(OreDictUtil.CROP_GOOSEBERRY),
		GRAPEFRUIT(OreDictUtil.CROP_GRAPEFRUIT),
		GUAVA(OreDictUtil.CROP_GUAVA),
		HAZELNUT(OreDictUtil.CROP_HAZELNUT),
		JACKFRUIT(OreDictUtil.CROP_JACKFRUIT),
		JUJUBA(OreDictUtil.CROP_JUJUBA),
		KIWI(OreDictUtil.CROP_KIWI),
		LIME(OreDictUtil.CROP_LIME),
		LYCHEE(OreDictUtil.CROP_LYCHEE),
		MANGO(OreDictUtil.CROP_MANGO),
		NUTMEG(OreDictUtil.CROP_NUTMEG),
		OLIVE(OreDictUtil.CROP_OLIVE),
		ORANGE(OreDictUtil.CROP_ORANGE),
		PASSIONFRUIT(OreDictUtil.CROP_PASSIONFRUIT),
		PAWPAW(OreDictUtil.CROP_PAWPAW),
		PEACH(OreDictUtil.CROP_PEACH),
		PEAR(OreDictUtil.CROP_PEAR),
		PECAN(OreDictUtil.CROP_PECAN),
		PEPPERCORN(OreDictUtil.CROP_PEPPERCORN),
		PERSIMMON(OreDictUtil.CROP_PERSIMMON),
		PISTACHIO(OreDictUtil.CROP_PISTACHIO),
		POMEGRANATE(OreDictUtil.CROP_POMEGRANATE),
		RAMBUTAN(OreDictUtil.CROP_RAMBUTAN),
		SOURSOP(OreDictUtil.CROP_SOURSOP),
		STARFRUIT(OreDictUtil.CROP_STARFRUIT),
		TAMARIND(OreDictUtil.CROP_TAMARIND),
		VANILLA(OreDictUtil.CROP_VANILLA),
		;
		//, COCONUT("cropCoconut");
		public static final EnumFruit[] VALUES = values();

		private final String oreDict;

		EnumFruit(String oreDict) {
			this.oreDict = oreDict;
		}

		@SideOnly(Side.CLIENT)
		public static void registerModel(Item item, IModelManager manager) {
			for (int i = 0; i < VALUES.length; i++) {
				EnumFruit fruit = VALUES[i];
				manager.registerItemModel(item, i, "fruits/" + fruit.name().toLowerCase(Locale.ENGLISH));
			}
		}

		public ItemStack getStack() {
			return getStack(1);
		}

		public ItemStack getStack(int qty) {
			return new ItemStack(ModuleCore.getItems().fruits, qty, ordinal());
		}

		public String getOreDict() {
			return oreDict;
		}
	}

	public ItemFruit() {
		super(1, 0.2f);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public boolean isDamageable() {
		return false;
	}

	@Override
	public boolean isRepairable() {
		return false;
	}

	/* MODELS*/
	@SideOnly(Side.CLIENT)
	@Override
	public void registerModel(Item item, IModelManager manager) {
		EnumFruit.registerModel(item, manager);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if (this.isInCreativeTab(tab)) {
			for (int i = 0; i < EnumFruit.values().length; i++) {
				subItems.add(new ItemStack(this, 1, i));
			}
		}
	}

	@Override
	public String getTranslationKey(ItemStack stack) {
		if (stack.getItemDamage() < 0 || stack.getItemDamage() >= EnumFruit.VALUES.length) {
			return super.getTranslationKey(stack);
		}

		return super.getTranslationKey(stack) + "." + EnumFruit.VALUES[stack.getItemDamage()].name().toLowerCase(Locale.ENGLISH);
	}

}
