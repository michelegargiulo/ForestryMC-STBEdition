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
package forestry.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import forestry.core.models.ModelManager;
import forestry.core.proxy.Proxies;
import forestry.plugins.PluginManager;

public abstract class BlockRegistry {
	protected <T extends Block> T registerBlock(T block, ItemBlock itemBlock, String name) {
		if (PluginManager.getStage() != PluginManager.Stage.REGISTER) {
			throw new RuntimeException("Tried to register Block outside of REGISTER");
		}
		block.setUnlocalizedName("for." + name);
		GameRegistry.registerBlock(block, null, name);
		GameRegistry.registerItem(itemBlock, name);
		Proxies.common.registerBlock(block);
		return block;
	}

	protected <T extends Block> T registerBlock(T block, String name) {
		return registerBlock(block, (Class<? extends ItemBlock>) null, name);
	}

	protected <T extends Block> T registerBlock(T block, Class<? extends ItemBlock> itemClass, String name, Object... itemCtorArgs) {
		if (PluginManager.getStage() != PluginManager.Stage.REGISTER) {
			throw new RuntimeException("Tried to register Block outside of REGISTER");
		}
		block.setUnlocalizedName("for." + name);
		GameRegistry.registerBlock(block, itemClass, name, itemCtorArgs);
		Proxies.common.registerBlock(block);
		return block;
	}

	protected static void registerOreDictWildcard(String oreDictName, Block block) {
		OreDictionary.registerOre(oreDictName, new ItemStack(block, 1, OreDictionary.WILDCARD_VALUE));
	}
}
