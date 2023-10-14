package forestry.arboriculture.charcoal.jei;

import com.google.common.collect.ImmutableList;

import forestry.core.config.Config;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import forestry.api.arboriculture.ICharcoalPileWall;
import forestry.core.ModuleCore;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class CharcoalPileWallWrapper implements IRecipeWrapper {

	private final ICharcoalPileWall pileWall;

	public CharcoalPileWallWrapper(ICharcoalPileWall pileWall) {
		this.pileWall = pileWall;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputs(VanillaTypes.ITEM, pileWall.getDisplayItems());
		int amount = 9 + pileWall.getCharcoalAmount();
		ItemStack charcoal = new ItemStack(Items.COAL, amount, 1);
		ItemStack ash = new ItemStack(ModuleCore.getItems().ash, amount / 4);

		// Read the item from the config. If there are any problems, revert back to Forestry Ash
		Item secItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(Config.charcoalPitSecondaryItem));
		if (secItem != null) {
			ash = new ItemStack(secItem, 1, Config.charcoalPitSecondaryItemMeta);
		}

		ImmutableList<ItemStack> outputs = ImmutableList.of(
			charcoal,
			ash
		);
		ingredients.setOutputs(VanillaTypes.ITEM, outputs);
	}

}
