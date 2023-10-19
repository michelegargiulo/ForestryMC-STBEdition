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
package forestry.arboriculture.genetics;

import com.mojang.authlib.GameProfile;
import forestry.api.arboriculture.*;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.AlleleSpeciesRegisterEvent;
import forestry.api.genetics.IAllele;
import forestry.api.world.ITreeGenData;
import forestry.arboriculture.ModuleArboriculture;
import forestry.arboriculture.genetics.alleles.AlleleFruits;
import forestry.arboriculture.models.ModelProviderFactory;
import forestry.arboriculture.tiles.TileLeaves;
import forestry.arboriculture.worldgen.*;
import forestry.core.config.Constants;
import forestry.core.genetics.alleles.AlleleBoolean;
import forestry.core.genetics.alleles.AlleleHelper;
import forestry.core.genetics.alleles.EnumAllele;
import forestry.core.tiles.TileUtil;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public enum TreeDefinition implements ITreeDefinition, ITreeGenerator, IStringSerializable {
	Oak(TreeBranchDefinition.QUERCUS, "appleOak", "robur", false, EnumLeafType.DECIDUOUS, new Color(4764952), new Color(4764952).brighter(), EnumVanillaWoodType.OAK) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenOak(tree);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitApple);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FERTILITY, EnumAllele.Saplings.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.MATURATION, EnumAllele.Maturation.FASTER);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.POMES);
		}

		@Override
		protected void registerMutations() {
			// vanilla
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	DarkOak(TreeBranchDefinition.QUERCUS, "darkOak", "velutina", false, EnumLeafType.DECIDUOUS, new Color(4764952), new Color(4764952).brighter(), EnumVanillaWoodType.DARK_OAK) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenDarkOak(tree);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FERTILITY, EnumAllele.Saplings.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.MATURATION, EnumAllele.Maturation.FASTER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.GIRTH, 2);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.POMES);
		}

		@Override
		protected void registerMutations() {
			// vanilla
		}
	},
	Birch(TreeBranchDefinition.BETULA, "silverBirch", "pendula", false, EnumLeafType.DECIDUOUS, new Color(8431445), new Color(0xb0c648), EnumVanillaWoodType.BIRCH) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenBirch(tree);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FERTILITY, EnumAllele.Saplings.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.MATURATION, EnumAllele.Maturation.FASTER);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {

		}

		@Override
		protected void registerMutations() {
			// vanilla
		}
	},
	Silverlime(TreeBranchDefinition.TILIA, "silverLime", "pendula", true, EnumLeafType.DECIDUOUS, new Color(0x5ea107), new Color(0x5ea107).brighter(), EnumForestryWoodType.SILVERLIME) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenSilverLime(tree);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FERTILITY, EnumAllele.Saplings.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			//Allele.helper.set(alleles, EnumTreeChromosome.EFFECT, Allele.leavesBrimstone);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.NUX)
				.addFruitFamily(EnumFruitFamily.PRUNES)
				.addFruitFamily(EnumFruitFamily.POMES).setRarity(0.005F);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Birch, Oak, 15);
		}
	},
	Walnut(TreeBranchDefinition.JUGLANS, "commonWalnut", "regia", true, EnumLeafType.DECIDUOUS, new Color(0x798c55), new Color(0xb0c648), EnumForestryWoodType.WALNUT) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenWalnut(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.NUX)
				.addFruitFamily(EnumFruitFamily.PRUNES)
				.addFruitFamily(EnumFruitFamily.POMES);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitWalnut);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FERTILITY, EnumAllele.Saplings.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.GIRTH, 2);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Silverlime, Cherry, 10);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Chestnut(TreeBranchDefinition.CASTANEA, "sweetChestnut", "sativa", true, EnumLeafType.DECIDUOUS, new Color(0x5ea107), new Color(0xb0c648), EnumForestryWoodType.CHESTNUT) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenChestnut(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.NUX)
				.addFruitFamily(EnumFruitFamily.PRUNES)
				.addFruitFamily(EnumFruitFamily.POMES);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitChestnut);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.LARGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.GIRTH, 2);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Walnut, Silverlime, 10);
			registerMutation(Walnut, Cherry, 10);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Cherry(TreeBranchDefinition.PRUNUS, "hillCherry", "serrulata", true, EnumLeafType.DECIDUOUS, new Color(0xe691da), new Color(0xe63e59), EnumForestryWoodType.CHERRY) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenCherry(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
				.addFruitFamily(EnumFruitFamily.POMES).setRarity(0.0015F);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitCherry);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FERTILITY, EnumAllele.Saplings.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLER);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Silverlime, Oak, 10);
			registerMutation(Silverlime, Birch, 10);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Lemon(TreeBranchDefinition.CITRUS, "lemon", "limon", true, EnumLeafType.DECIDUOUS, new Color(0x88af54), new Color(0xa3b850), EnumForestryWoodType.CITRUS) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenLemon(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
				.addFruitFamily(EnumFruitFamily.POMES);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitLemon);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Silverlime, Cherry, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Plum(TreeBranchDefinition.PRUNUS, "plum", "domestica", true, EnumLeafType.DECIDUOUS, new Color(0x589246), new Color(0xa3b850), EnumForestryWoodType.PLUM) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenPlum(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
				.addFruitFamily(EnumFruitFamily.POMES).setRarity(0.005F).setTemperature(EnumTemperature.WARM).setHumidity(EnumHumidity.DAMP);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitPlum);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.HIGH);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Lemon, Cherry, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Maple(TreeBranchDefinition.ACER, "sugarMaple", "saccharum", true, EnumLeafType.MAPLE, new Color(0xd4f425), new Color(0x619a3c), EnumForestryWoodType.MAPLE) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenMaple(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
				.addFruitFamily(EnumFruitFamily.POMES).setRarity(0.0025F);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FERTILITY, EnumAllele.Saplings.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.AVERAGE);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Spruce, Larch, 5);
		}
	},
	Spruce(TreeBranchDefinition.PICEA, "redSpruce", "abies", false, EnumLeafType.CONIFERS, new Color(6396257), new Color(0x539d12), EnumVanillaWoodType.SPRUCE) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenSpruce(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {

		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FERTILITY, EnumAllele.Saplings.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.MATURATION, EnumAllele.Maturation.FASTER);
		}

		@Override
		protected void registerMutations() {
			// vanilla
		}
	},
	Larch(TreeBranchDefinition.LARIX, "mundaneLarch", "decidua", true, EnumLeafType.CONIFERS, new Color(0x698f90), new Color(0x569896), EnumForestryWoodType.LARCH) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenLarch(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.setRarity(0.0025F).setTemperature(EnumTemperature.COLD);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FERTILITY, EnumAllele.Saplings.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.AVERAGE);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Spruce, Birch, 10);
			registerMutation(Spruce, Oak, 10);
		}
	},
	Pine(TreeBranchDefinition.PINUS, "bullPine", "sabiniana", true, EnumLeafType.CONIFERS, new Color(0xfeff8f), new Color(0xffd98f), EnumForestryWoodType.PINE) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenPine(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.setRarity(0.0025F).setTemperature(EnumTemperature.COLD);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FERTILITY, EnumAllele.Saplings.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.AVERAGE);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Spruce, Larch, 10);
		}
	},
	Sequoia(TreeBranchDefinition.SEQUOIA, "coastSequoia", "sempervirens", false, EnumLeafType.CONIFERS, new Color(0x418e71), new Color(0x569896), EnumForestryWoodType.SEQUOIA) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenSequoia(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {

		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.LARGEST);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.MATURATION, EnumAllele.Maturation.SLOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.GIRTH, 3);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FIREPROOF, EnumAllele.Fireproof.TRUE);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Larch, Pine, 5);
		}
	},
	Gigant(TreeBranchDefinition.SEQUOIADENDRON, "giantSequoia", "giganteum", false, EnumLeafType.CONIFERS, new Color(0x738434), new Color(0x738434).brighter(), EnumForestryWoodType.GIGANTEUM) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenGiganteum(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.setComplexity(10);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.GIGANTIC);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOWEST);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.MATURATION, EnumAllele.Maturation.SLOWEST);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.GIRTH, 4);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FIREPROOF, EnumAllele.Fireproof.TRUE);
		}

		@Override
		protected void registerMutations() {
			// only available by rare villager trade
		}
	},
	Jungle(TreeBranchDefinition.TROPICAL, "jungle", "tectona", false, EnumLeafType.JUNGLE, new Color(4764952), new Color(0x658917), EnumVanillaWoodType.JUNGLE) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenJungle(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitCocoa);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.LARGER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.MATURATION, EnumAllele.Maturation.FAST);
		}

		@Override
		protected void registerMutations() {
			// vanilla
		}
	},
	Teak(TreeBranchDefinition.TECTONA, "teak", "grandis", true, EnumLeafType.JUNGLE, new Color(0xfeff8f), new Color(0xffd98f), EnumForestryWoodType.TEAK) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenTeak(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE).setRarity(0.0025F).setTemperature(EnumTemperature.WARM).setHumidity(EnumHumidity.DAMP);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOWER);
		}

		@Override
		protected void registerMutations() {
			registerMutation(DarkOak, Jungle, 10);
		}
	},
	Ipe(TreeBranchDefinition.TABEBUIA, "ipe", "serratifolia", true, EnumLeafType.JUNGLE, new Color(0xfdd207), new Color(0xad8f04), EnumForestryWoodType.IPE) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenIpe(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.LARGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.GIRTH, 2);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Teak, DarkOak, 10);
		}
	},
	Kapok(TreeBranchDefinition.CEIBA, "kapok", "pentandra", true, EnumLeafType.JUNGLE, new Color(0x89987b), new Color(0x89aa9e), EnumForestryWoodType.KAPOK) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenKapok(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE)
				.addFruitFamily(EnumFruitFamily.PRUNES);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.LARGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.MATURATION, EnumAllele.Maturation.SLOW);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Jungle, Teak, 10);
		}
	},
	Ebony(TreeBranchDefinition.EBONY, "myrtleEbony", "pentamera", true, EnumLeafType.JUNGLE, new Color(0xa2d24a), new Color(0xc4d24a), EnumForestryWoodType.EBONY) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenEbony(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE)
				.addFruitFamily(EnumFruitFamily.PRUNES).setRarity(0.0005F).setTemperature(EnumTemperature.WARM).setHumidity(EnumHumidity.DAMP);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.MATURATION, EnumAllele.Maturation.SLOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.GIRTH, 3);
		}

		@Override
		protected void registerMutations() {
			registerMutation(DarkOak, Kapok, 10);
		}
	},
	Zebrawood(TreeBranchDefinition.ASTRONIUM, "zebrawood", "graveolens", false, EnumLeafType.JUNGLE, new Color(0xa2d24a), new Color(0xc4d24a), EnumForestryWoodType.ZEBRAWOOD) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenZebrawood(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.NUX).setRarity(0.0005F).setTemperature(EnumTemperature.WARM).setHumidity(EnumHumidity.DAMP);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.LARGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.GIRTH, 2);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Ebony, Poplar, 5);
		}
	},
	Mahogony(TreeBranchDefinition.MAHOGANY, "yellowMeranti", "gibbosa", true, EnumLeafType.JUNGLE, new Color(0x8ab154), new Color(0xa9b154), EnumForestryWoodType.MAHOGANY) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenMahogany(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE).setRarity(0.0005F).setTemperature(EnumTemperature.WARM).setHumidity(EnumHumidity.DAMP);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.LARGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.MATURATION, EnumAllele.Maturation.SLOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.GIRTH, 2);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Kapok, Ebony, 10);
		}
	},
	AcaciaVanilla(TreeBranchDefinition.ACACIA, "acacia", "aneura", true, EnumLeafType.DECIDUOUS, new Color(0x616101), new Color(0xb3b302), EnumVanillaWoodType.ACACIA) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenAcaciaVanilla(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE)
				.addFruitFamily(EnumFruitFamily.NUX);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {

		}

		@Override
		protected void registerMutations() {
			// vanilla
		}
	},
	Acacia(TreeBranchDefinition.ACACIA, "desertAcacia", "erioloba", true, EnumLeafType.DECIDUOUS, new Color(0x748C1C), new Color(0xb3b302), EnumForestryWoodType.ACACIA) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenAcacia(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE)
				.addFruitFamily(EnumFruitFamily.NUX).setRarity(0.005F).setTemperature(EnumTemperature.WARM).setHumidity(EnumHumidity.ARID);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {

		}

		@Override
		protected void registerMutations() {
			registerMutation(Teak, Balsa, 10);
		}
	},
	Padauk(TreeBranchDefinition.PTEROCARPUS, "padauk", "soyauxii", true, EnumLeafType.DECIDUOUS, new Color(0xd0df8c), new Color(0x435c32), EnumForestryWoodType.PADAUK) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenPadauk(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE).setRarity(0.005F).setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.LARGE);
		}

		@Override
		protected void registerMutations() {
			registerMutation(AcaciaVanilla, Jungle, 10);
		}
	},
	Balsa(TreeBranchDefinition.OCHROMA, "balsa", "pyramidale", true, EnumLeafType.DECIDUOUS, new Color(0x59ac00), new Color(0xfeff8f), EnumForestryWoodType.BALSA) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenBalsa(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE)
				.addFruitFamily(EnumFruitFamily.NUX).setRarity(0.0005F).setHumidity(EnumHumidity.DAMP).setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FERTILITY, EnumAllele.Saplings.HIGH);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.LARGE);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Teak, AcaciaVanilla, 10);
		}
	},
	Cocobolo(TreeBranchDefinition.DALBERGIA, "cocobolo", "retusa", false, EnumLeafType.DECIDUOUS, new Color(0x6aa17a), new Color(0x487d4c), EnumForestryWoodType.COCOBOLO) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenCocobolo(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE).setRarity(0.0005F).setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.LARGEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Acacia, DarkOak, 10);
		}
	},
	Wenge(TreeBranchDefinition.MILLETTIA, "wenge", "laurentii", true, EnumLeafType.DECIDUOUS, new Color(0xada157), new Color(0xad8a57), EnumForestryWoodType.WENGE) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenWenge(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE)
				.addFruitFamily(EnumFruitFamily.NUX).setRarity(0.0005F).setHumidity(EnumHumidity.DAMP).setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FERTILITY, EnumAllele.Saplings.LOWEST);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.GIRTH, 2);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Cocobolo, Balsa, 10);
		}
	},
	Baobab(TreeBranchDefinition.ADANSONIA, "grandidierBaobab", "digitata", true, EnumLeafType.DECIDUOUS, new Color(0xfeff8f), new Color(0xffd98f), EnumForestryWoodType.BAOBAB) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenBaobab(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE)
				.addFruitFamily(EnumFruitFamily.NUX).setRarity(0.005F).setTemperature(EnumTemperature.HOT).setHumidity(EnumHumidity.DAMP);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.LARGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.MATURATION, EnumAllele.Maturation.SLOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.GIRTH, 3);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Balsa, Wenge, 10);
		}
	},
	Mahoe(TreeBranchDefinition.TALIPARITI, "blueMahoe", "elatum", true, EnumLeafType.DECIDUOUS, new Color(0xa0ba1b), new Color(0x79a175), EnumForestryWoodType.MAHOE) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenMahoe(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE)
				.addFruitFamily(EnumFruitFamily.POMES)
				.addFruitFamily(EnumFruitFamily.PRUNES).setRarity(0.000005F).setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALL);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.HIGH);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.MATURATION, EnumAllele.Maturation.SLOWEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Balsa, Acacia, 5);
		}
	},
	Willow(TreeBranchDefinition.SALIX, "whiteWillow", "alba", true, EnumLeafType.WILLOW, new Color(0xa3b8a5), new Color(0xa3b850), EnumForestryWoodType.WILLOW) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenWillow(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.NUX)
				.addFruitFamily(EnumFruitFamily.PRUNES)
				.addFruitFamily(EnumFruitFamily.POMES)
				.setRarity(0.0025F).setHumidity(EnumHumidity.DAMP);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.MATURATION, EnumAllele.Maturation.FASTER);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Oak, Birch, 10)
				.restrictTemperature(EnumTemperature.WARM, EnumTemperature.HOT)
				.restrictHumidity(EnumHumidity.DAMP);
			registerMutation(Oak, Silverlime, 10)
				.restrictTemperature(EnumTemperature.WARM, EnumTemperature.HOT)
				.restrictHumidity(EnumHumidity.DAMP);
			registerMutation(Silverlime, Birch, 10)
				.restrictTemperature(EnumTemperature.WARM, EnumTemperature.HOT)
				.restrictHumidity(EnumHumidity.DAMP);
		}
	},
	Sipiri(TreeBranchDefinition.CHLOROCARDIUM, "sipiri", "rodiei", true, EnumLeafType.DECIDUOUS, new Color(0x678911), new Color(0x79a175), EnumForestryWoodType.GREENHEART) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenGreenheart(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE).setRarity(0.0025F).setTemperature(EnumTemperature.WARM)
				.setHumidity(EnumHumidity.DAMP);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.LARGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.MATURATION, EnumAllele.Maturation.SLOW);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Kapok, Mahogony, 10)
				.restrictTemperature(EnumTemperature.WARM, EnumTemperature.HOT)
				.restrictHumidity(EnumHumidity.DAMP);
		}
	},
	Papaya(TreeBranchDefinition.CARICA, "papaya", "papaya", true, EnumLeafType.PALM, new Color(0x6d9f58), new Color(0x75E675), EnumForestryWoodType.PAPAYA) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenPapaya(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE)
				.addFruitFamily(EnumFruitFamily.NUX).setRarity(0.005F).setTemperature(EnumTemperature.WARM).setHumidity(EnumHumidity.DAMP);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitPapaya);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FERTILITY, EnumAllele.Saplings.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.AVERAGE);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Jungle, Cherry, 5);
		}
	},
	Date(TreeBranchDefinition.PHOENIX, "datePalm", "dactylifera", true, EnumLeafType.PALM, new Color(0xcbcd79), new Color(0xB3F370), EnumForestryWoodType.PALM) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenDate(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE)
				.addFruitFamily(EnumFruitFamily.NUX).setRarity(0.005F).setTemperature(EnumTemperature.WARM).setHumidity(EnumHumidity.DAMP);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitDates);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FERTILITY, EnumAllele.Saplings.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.AVERAGE);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Jungle, Papaya, 5);
		}
	},
	Poplar(TreeBranchDefinition.POPULUS, "whitePoplar", "alba", true, EnumLeafType.DECIDUOUS, new Color(0xa3b8a5), new Color(0x539d12), EnumForestryWoodType.POPLAR) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenPoplar(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
				.addFruitFamily(EnumFruitFamily.POMES);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALL);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.MATURATION, EnumAllele.Maturation.SLOWER);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Birch, Willow, 5);
			registerMutation(Oak, Willow, 5);
			registerMutation(Silverlime, Willow, 5);
		}
	},

	// -----------------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------

	// Harvestcraft
	Almond(TreeBranchDefinition.CASTANEA, "almond", "amygdalus", true, EnumLeafType.DECIDUOUS, new Color(0x5A8622), new Color(0xBFC71C), EnumForestryWoodType.ALMOND) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenAlmond(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.NUX)
					.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.NORMAL)
					.setTemperature(EnumTemperature.NORMAL);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitAlmond);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.LARGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.GIRTH, 1);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Walnut, Chestnut, 10);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Apricot(TreeBranchDefinition.PRUNUS, "apricot", "armeniaca", true, EnumLeafType.DECIDUOUS, new Color(0x19D61C), new Color(0xB8A838), EnumForestryWoodType.APRICOT) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenApricot(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.NORMAL)
					.setTemperature(EnumTemperature.NORMAL);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitApricot);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Plum, Lemon, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Avocado(TreeBranchDefinition.PERSEA, "avocado", "americana", true, EnumLeafType.DECIDUOUS, new Color(0x416834), new Color(0x75E675), EnumForestryWoodType.AVOCADO) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenAvocado(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE)
					.addFruitFamily(EnumFruitFamily.PRUNES)
					.setTemperature(EnumTemperature.WARM)
					.setHumidity(EnumHumidity.DAMP);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitAvocado);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FERTILITY, EnumAllele.Saplings.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.AVERAGE);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Papaya, Apricot, 5);
		}
	},
	Banana(TreeBranchDefinition.MUSA, "banana", "acuminata", true, EnumLeafType.PALM, new Color(0x3ACD42), new Color(0x99F340), EnumForestryWoodType.BANANA) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenBanana(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE)
					.addFruitFamily(EnumFruitFamily.NUX)
					.setTemperature(EnumTemperature.WARM)
					.setHumidity(EnumHumidity.DAMP);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitBanana);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FERTILITY, EnumAllele.Saplings.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.AVERAGE);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Avocado, Papaya, 5);
		}
	},
	Breadfruit(TreeBranchDefinition.MORACEAE, "breadfruit", "altilis", true, EnumLeafType.DECIDUOUS, new Color(0x6D923F), new Color(0x8FB814), EnumForestryWoodType.BREADFRUIT) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenBreadfruit(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setTemperature(EnumTemperature.WARM)
					.setHumidity(EnumHumidity.DAMP);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitBreadfruit);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.HIGH);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Papaya, Avocado, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Cashew(TreeBranchDefinition.ANACARDIACEAE, "cashew", "occidentale", true, EnumLeafType.DECIDUOUS, new Color(0xCDFF35), new Color(0xE1D43D), EnumForestryWoodType.CASHEW) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenCashew(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.NUX)
					.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.DAMP)
					.setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitCashew);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Silverlime, Cherry, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Cedar(TreeBranchDefinition.CITRUS, "cedar", "medica", true, EnumLeafType.DECIDUOUS, new Color(0x46AF3F), new Color(0xA3B85E), EnumForestryWoodType.CEDAR) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenCedar(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.NORMAL)
					.setTemperature(EnumTemperature.NORMAL);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitCedar);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Lemon, Orange, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Cinnamon(TreeBranchDefinition.LAURACEAE, "cinnamon", "cassia", true, EnumLeafType.DECIDUOUS, new Color(0x1BB848), new Color(0x3C9D28), EnumForestryWoodType.CINNAMON) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenCinnamon(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.DAMP)
					.setTemperature(EnumTemperature.NORMAL);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALL);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.MATURATION, EnumAllele.Maturation.SLOWER);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Poplar, Mahoe, 5);
		}
	},
	Dragonfruit(TreeBranchDefinition.CACTACEAE, "dragonfruit", "undatus", true, EnumLeafType.DECIDUOUS, new Color(0x6EAF4E), new Color(0x64B845), EnumForestryWoodType.DRAGONFRUIT) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenDragonfruit(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.ARID)
					.setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitDragonfruit);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Acacia, Papaya, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Durian(TreeBranchDefinition.MALVACEAE, "durian", "zibethinus", true, EnumLeafType.DECIDUOUS, new Color(0x21853C), new Color(0x43B834), EnumForestryWoodType.DURIAN) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenDurian(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.DAMP)
					.setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitDurian);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Chestnut, Almond, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Fig(TreeBranchDefinition.FICUS, "fig", "carica", true, EnumLeafType.DECIDUOUS, new Color(0x19632A), new Color(0x47B820), EnumForestryWoodType.FIG) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenFig(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.NORMAL)
					.setTemperature(EnumTemperature.NORMAL);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitFig);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Peach, Dragonfruit, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Gooseberry(TreeBranchDefinition.GROSSULARIACEAE, "gooseberry", "uvacrispa", true, EnumLeafType.DECIDUOUS, new Color(0x1C6722), new Color(0x37933F), EnumForestryWoodType.GOOSEBERRY) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenGooseberry(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.NORMAL)
					.setTemperature(EnumTemperature.COLD);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitGooseberry);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FERTILITY, EnumAllele.Saplings.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLER);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Cherry, Fig, 10);
			registerMutation(Cherry, Dragonfruit, 10);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Grapefruit(TreeBranchDefinition.CITRUS, "grapefruit", "aurantium", true, EnumLeafType.DECIDUOUS, new Color(0x41AF53), new Color(0x6CB812), EnumForestryWoodType.GRAPEFRUIT) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenGrapefruit(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.DAMP)
					.setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitGrapefruit);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Orange, Cedar, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Guava(TreeBranchDefinition.MYRTACEAE, "guava", "guajava", true, EnumLeafType.DECIDUOUS, new Color(0x88af54), new Color(0xa3b850), EnumForestryWoodType.GUAVA) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenGuava(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.DAMP)
					.setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitGuava);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Durian, Pear, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Hazelnut(TreeBranchDefinition.BETULA, "hazelnut", "avellana", true, EnumLeafType.DECIDUOUS, new Color(0x2CAF33), new Color(0x3FB830), EnumForestryWoodType.HAZELNUT) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenHazelnut(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.NUX)
					.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.NORMAL)
					.setTemperature(EnumTemperature.NORMAL);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitHazelnut);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Chestnut, Grapefruit, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Jackfruit(TreeBranchDefinition.MORACEAE, "jackfruit", "heterophyllus", true, EnumLeafType.DECIDUOUS, new Color(0xB9CD60), new Color(0xB3F370), EnumForestryWoodType.JACKFRUIT) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenJackfruit(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE)
					.addFruitFamily(EnumFruitFamily.PRUNES)
					.setTemperature(EnumTemperature.WARM)
					.setHumidity(EnumHumidity.DAMP);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitJackfruit);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FERTILITY, EnumAllele.Saplings.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.AVERAGE);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Jujuba, Date, 5);
		}
	},
	Jujuba(TreeBranchDefinition.RHAMNACEAE, "jujuba", "ziziphus", true, EnumLeafType.DECIDUOUS, new Color(0x0D6B2F), new Color(0x2C7332), EnumForestryWoodType.JUJUBA) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenJujuba(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.NORMAL)
					.setTemperature(EnumTemperature.NORMAL);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitJujuba);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Date, Jungle, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Kiwi(TreeBranchDefinition.ACTINIDIACEAE, "kiwi", "deliciosa", true, EnumLeafType.DECIDUOUS, new Color(0x239635), new Color(0x70B839), EnumForestryWoodType.KIWI) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenKiwi(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.NUX)
					.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.DAMP)
					.setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitKiwi);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Cedar, Pawpaw, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Lime(TreeBranchDefinition.CITRUS, "lime", "glauca", true, EnumLeafType.DECIDUOUS, new Color(0x7EDF4C), new Color(0xB8CB26), EnumForestryWoodType.LIME) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenLime(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.NORMAL)
					.setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitLime);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Lemon, Silverlime, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Lychee(TreeBranchDefinition.SAPINDACEAE, "lychee", "chinensis", true, EnumLeafType.DECIDUOUS, new Color(0x58AF23), new Color(0x37B847), EnumForestryWoodType.LYCHEE) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenLychee(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.DAMP)
					.setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitLychee);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Grapefruit, Cherry, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Mango(TreeBranchDefinition.ANACARDIACEAE, "mango", "indica", true, EnumLeafType.DECIDUOUS, new Color(0x668945), new Color(0x84B855), EnumForestryWoodType.MANGO) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenMango(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.DAMP)
					.setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitMango);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Orange, Fig, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Nutmeg(TreeBranchDefinition.MYRISTICACEAE, "nutmeg", "fragrans", true, EnumLeafType.DECIDUOUS, new Color(0x3A8C25), new Color(0x46B846), EnumForestryWoodType.NUTMEG) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenNutmeg(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.NUX)
					.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.NORMAL)
					.setTemperature(EnumTemperature.NORMAL);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitNutmeg);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Hazelnut, Peach, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Olive(TreeBranchDefinition.OLEACEAE, "olive", "europaea", true, EnumLeafType.DECIDUOUS, new Color(0x57AE57), new Color(0x3C853C), EnumForestryWoodType.OLIVE) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenOlive(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.NORMAL)
					.setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitOlive);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Grapefruit, Fig, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Orange(TreeBranchDefinition.CITRUS, "orange", "aurantium", true, EnumLeafType.DECIDUOUS, new Color(0x138823), new Color(0x198D1F), EnumForestryWoodType.ORANGE) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenOrange(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.NORMAL)
					.setTemperature(EnumTemperature.NORMAL);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitOrange);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Lemon, Cedar, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Paperbark(TreeBranchDefinition.MYRTACEAE, "paperbark", "malaleuca", true, EnumLeafType.DECIDUOUS, new Color(0x27AF57), new Color(0x157938), EnumForestryWoodType.PAPERBARK) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenPaperbark(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.NORMAL)
					.setTemperature(EnumTemperature.NORMAL);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Guava, Grapefruit, 5);
		}
	},
	Passionfruit(TreeBranchDefinition.PASSIFLORACEAE, "passionfruit", "edulis", true, EnumLeafType.DECIDUOUS, new Color(0x106E1D), new Color(0x0A641F), EnumForestryWoodType.PASSIONFRUIT) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenPassionfruit(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.DAMP)
					.setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitPassionFruit);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Plum, Guava, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Pawpaw(TreeBranchDefinition.ANNONANCEAE, "pawpaw", "asimina", true, EnumLeafType.DECIDUOUS, new Color(0x46AF3B), new Color(0x28B827), EnumForestryWoodType.PAWPAW) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenPawpaw(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.DAMP)
					.setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitPawpaw);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Papaya, Pear, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Peach(TreeBranchDefinition.PRUNUS, "peach", "persica", true, EnumLeafType.DECIDUOUS, new Color(0xF3D2F4), new Color(0xC88CAE), EnumForestryWoodType.PEACH) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenPeach(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.NORMAL)
					.setTemperature(EnumTemperature.NORMAL);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitPeach);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Plum, Orange, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Pear(TreeBranchDefinition.ROSACEAE, "pear", "communis", true, EnumLeafType.DECIDUOUS, new Color(0x54AF42), new Color(0x2BB856), EnumForestryWoodType.PEAR) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenPear(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.NORMAL)
					.setTemperature(EnumTemperature.NORMAL);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitPear);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Peach, Fig, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Pecan(TreeBranchDefinition.JUGLANDACEAE, "pecan", "illinoinensis", true, EnumLeafType.DECIDUOUS, new Color(0x23AF1D), new Color(0x41B83D), EnumForestryWoodType.PECAN) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenPecan(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.NUX)
					.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.NORMAL)
					.setTemperature(EnumTemperature.NORMAL);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitPecan);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Nutmeg, Hazelnut, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Peppercorn(TreeBranchDefinition.PIPERACEAE, "peppercorn", "nigrum", true, EnumLeafType.DECIDUOUS, new Color(0x2FAF5E), new Color(0x0C7D1B), EnumForestryWoodType.PEPPERCORN) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenPeppercorn(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.NUX)
					.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.DAMP)
					.setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitPeppercorn);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Olive, Almond, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Persimmon(TreeBranchDefinition.EBENACEAE, "persimmon", "diospyros", true, EnumLeafType.DECIDUOUS, new Color(0xAE8318), new Color(0xD4AA0B), EnumForestryWoodType.PERSIMMON) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenPersimmon(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.NORMAL)
					.setTemperature(EnumTemperature.NORMAL);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitPersimmon);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Mango, Plum, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Pistachio(TreeBranchDefinition.ANACARDIACEAE, "pistachio", "vera", true, EnumLeafType.DECIDUOUS, new Color(0x88AF54), new Color(0xAAB84A), EnumForestryWoodType.PISTACHIO) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenPistachio(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.NUX)
					.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.ARID)
					.setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitPistachio);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Cashew, Pecan, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Pomegranate(TreeBranchDefinition.LYTHRACEAE, "pomegranate", "granatum", true, EnumLeafType.DECIDUOUS, new Color(0x16A03C), new Color(0x3CB834), EnumForestryWoodType.POMEGRANATE) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenPomegranate(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.NORMAL)
					.setTemperature(EnumTemperature.NORMAL);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitPomegranate);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Plum, Pawpaw, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Rambutan(TreeBranchDefinition.SAPINDACEAE, "rambutan", "lappaceum", true, EnumLeafType.DECIDUOUS, new Color(0x116B18), new Color(0x33B82B), EnumForestryWoodType.RAMBUTAN) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenRambutan(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.DAMP)
					.setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitRambutan);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Pomegranate, Durian, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Soursop(TreeBranchDefinition.ANNONANCEAE, "soursop", "muricata", true, EnumLeafType.DECIDUOUS, new Color(0x2BAF26), new Color(0x38B82D), EnumForestryWoodType.SOURSOP) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenSoursop(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.DAMP)
					.setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitSoursop);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Silverlime, Cherry, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Starfruit(TreeBranchDefinition.OXALYDACEAE, "starfruit", "carambola", true, EnumLeafType.DECIDUOUS, new Color(0x3BAF42), new Color(0x68B845), EnumForestryWoodType.STARFRUIT) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenStarfruit(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.DAMP)
					.setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitStarfruit);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Dragonfruit, Pomegranate, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Tamarind(TreeBranchDefinition.CITRUS, "tamarind", "indica", true, EnumLeafType.DECIDUOUS, new Color(0x2B8427), new Color(0x376E1A), EnumForestryWoodType.TAMARIND) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenTamarind(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.NUX)
					.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.DAMP)
					.setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitTamarind);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.LARGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.MATURATION, EnumAllele.Maturation.SLOWEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Wenge, Cinnamon, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Vanilla(TreeBranchDefinition.ORCHIDACEAE, "vanilla", "planifolia", true, EnumLeafType.DECIDUOUS, new Color(0x2CAF20), new Color(0x70B839), EnumForestryWoodType.VANILLA) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenVanilla(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.NUX)
					.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.DAMP)
					.setTemperature(EnumTemperature.WARM);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitVanilla);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Cinnamon, Breadfruit, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return true;
		}
	},
	Yew(TreeBranchDefinition.TAXACEAE, "yew", "baccata", true, EnumLeafType.DECIDUOUS, new Color(0x175B11), new Color(0x325F34), EnumForestryWoodType.YEW) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenYew(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.NUX)
					.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.NORMAL)
					.setTemperature(EnumTemperature.NORMAL);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.SMALLEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Pine, Padauk, 5);
		}
	},

	/*
	// Natura
	Bloodwood(TreeBranchDefinition.INVERSUS, "bloodwood", "infernalis", true, EnumLeafType.DECIDUOUS, new Color(0x9B0504), new Color(0xD9231B), EnumForestryWoodType.BLOODWOOD) {

		@Override
		public ILeafSpriteProvider getLeafSpriteProvider(EnumLeafType leafType, Color primary, Color secondary) {
			return new LeafProviderBloodwood();
		}

		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenBloodwood(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES)
					.setHumidity(EnumHumidity.ARID)
					.setTemperature((EnumTemperature.HELLISH));
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOWEST);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOWEST);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.LARGER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FIREPROOF, EnumAllele.Fireproof.TRUE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.GIRTH, 2);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Orange, Dragonfruit, 5);
		}

		@Override
		public boolean hasFruitLeaves() {
			return false;
		}
	},
	Ghostwood(TreeBranchDefinition.INTACTILIACEAE, "ghostwood", "pallidis", true, EnumLeafType.WILLOW, new Color(0xFFFCF9), new Color(0xF6FFF7), EnumForestryWoodType.GHOSTWOOD) {

		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenGhostwood(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE).setRarity(0.0025F).setTemperature(EnumTemperature.WARM).setHumidity(EnumHumidity.DAMP);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOWER);
		}

		@Override
		protected void registerMutations() {
			registerMutation(DarkOak, Jungle, 10);
		}
	},

	// Integrated Dynamics

	// Extra Utilities

	// Immersive Intelligence
	Rubber(TreeBranchDefinition.EUPHORBIACEAE, "rubber", "brasiliensis", false, EnumLeafType.JUNGLE, new Color(0x297A3D), new Color(0x3F8955), EnumForestryWoodType.RUBBER) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenRubber(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitCocoa);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.LARGER);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.MATURATION, EnumAllele.Maturation.FAST);
		}

		@Override
		protected void registerMutations() {
			// vanilla
		}
	},

	// Oceanic Expanse
	Coconut(TreeBranchDefinition.ARECACEAE, "coconutPalm", "nucifera", true, EnumLeafType.PALM, new Color(0x5ACD3E), new Color(0x3AA632), EnumForestryWoodType.COCONUT) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenDate(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.JUNGLE)
					.addFruitFamily(EnumFruitFamily.NUX).setRarity(0.005F).setTemperature(EnumTemperature.WARM).setHumidity(EnumHumidity.DAMP);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FRUITS, AlleleFruits.fruitDates);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.FERTILITY, EnumAllele.Saplings.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.YIELD, EnumAllele.Yield.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.AVERAGE);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Jungle, Papaya, 5);
		}
	},


	// RandomThings
	Spectre(TreeBranchDefinition.INTACTILIACEAE, "spectre", "exspiravit", true, EnumLeafType.WILLOW, new Color(0xa3b8a5), new Color(0x56B8A7), EnumForestryWoodType.SPECTRE) {
		@Override
		public WorldGenerator getWorldGenerator(ITreeGenData tree) {
			return new WorldGenSpectre(tree);
		}

		@Override
		protected void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies) {
			treeSpecies.addFruitFamily(EnumFruitFamily.PRUNES)
					.addFruitFamily(EnumFruitFamily.POMES);
		}

		@Override
		protected void setAlleles(IAllele[] alleles) {
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.HEIGHT, EnumAllele.Height.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.SAPPINESS, EnumAllele.Sappiness.LOW);
			AlleleHelper.getInstance().set(alleles, EnumTreeChromosome.MATURATION, EnumAllele.Maturation.FASTER);
		}

		@Override
		protected void registerMutations() {
			registerMutation(Willow, Balsa, 5);
		}
	},


	// Tinkers Construct

	// Witchery
	*/

	;

	// End new trees
	// -----------------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------

	public static final TreeDefinition[] VALUES = values();

	private final TreeBranchDefinition branch;
	private final IAlleleTreeSpecies species;

	private final IWoodType woodType;

	private IAllele[] template;
	private ITreeGenome genome;

	TreeDefinition(TreeBranchDefinition branch, String speciesName, String binomial, boolean dominant, EnumLeafType leafType, Color primary, Color secondary, IWoodType woodType) {
		String uid = Constants.MOD_ID + ".tree" + this;
		String unlocalizedDescription = "for.description.tree" + this;
		String unlocalizedName = "for.trees.species." + speciesName;

		this.branch = branch;

		ILeafSpriteProvider leafIconProvider = getLeafSpriteProvider(leafType, primary, secondary);
		IGermlingModelProvider germlingIconProvider = ModelProviderFactory.create(woodType, uid, leafIconProvider);
		IWoodProvider woodProvider = WoodProviderFactory.create(woodType);

		ILeafProvider leafProvider = new LeafProvider();

		IAlleleTreeSpeciesBuilder speciesBuilder = TreeManager.treeFactory.createSpecies(uid, unlocalizedName, "Sengir", unlocalizedDescription, dominant, branch.getBranch(), binomial, Constants.MOD_ID, leafIconProvider, germlingIconProvider, woodProvider, this, leafProvider);
		setSpeciesProperties(speciesBuilder);
		this.species = speciesBuilder.build();
		this.woodType = woodType;
	}

	public ILeafSpriteProvider getLeafSpriteProvider(EnumLeafType leafType, Color primary, Color secondary) {
		return TreeManager.treeFactory.getLeafIconProvider(leafType, primary, secondary);
	}

	protected abstract void setSpeciesProperties(IAlleleTreeSpeciesBuilder treeSpecies);

	protected abstract void setAlleles(IAllele[] alleles);

	protected abstract void registerMutations();

	public boolean hasFruitLeaves() {
		return false;
	}

	@Override
	public boolean setLogBlock(ITreeGenome genome, World world, BlockPos pos, EnumFacing facing) {
		AlleleBoolean fireproofAllele = (AlleleBoolean) genome.getActiveAllele(EnumTreeChromosome.FIREPROOF);
		boolean fireproof = fireproofAllele.getValue();
		IBlockState logBlock = TreeManager.woodAccess.getBlock(woodType, WoodBlockKind.LOG, fireproof);

		BlockLog.EnumAxis axis = BlockLog.EnumAxis.fromFacingAxis(facing.getAxis());
		return world.setBlockState(pos, logBlock.withProperty(BlockLog.LOG_AXIS, axis));
	}

	@Override
	public boolean setLeaves(ITreeGenome genome, World world, @Nullable GameProfile owner, BlockPos pos) {
		return setLeaves(genome, world, owner, pos, world.rand);
	}

	@Override
	public boolean setLeaves(ITreeGenome genome, World world, @Nullable GameProfile owner, BlockPos pos, Random rand) {
		if (owner == null && genome.matchesTemplateGenome()) {
			IFruitProvider fruitProvider = genome.getFruitProvider();
			String speciesUid = genome.getPrimary().getUID();
			IBlockState defaultLeaves;
			if (fruitProvider.isFruitLeaf(genome, world, pos) && rand.nextFloat() <= fruitProvider.getFruitChance(genome, world, pos)) {
				defaultLeaves = ModuleArboriculture.getBlocks().getDefaultLeavesFruit(speciesUid);
			} else {
				defaultLeaves = ModuleArboriculture.getBlocks().getDefaultLeaves(speciesUid);
			}
			return world.setBlockState(pos, defaultLeaves);
		} else {
			IBlockState leaves = ModuleArboriculture.getBlocks().leaves.getDefaultState();
			boolean placed = world.setBlockState(pos, leaves);
			if (!placed) {
				return false;
			}

			TileLeaves tileLeaves = TileUtil.getTile(world, pos, TileLeaves.class);
			if (tileLeaves == null) {
				world.setBlockToAir(pos);
				return false;
			}

			if (owner != null) {
				tileLeaves.getOwnerHandler().setOwner(owner);
			}
			tileLeaves.setTree(new Tree(genome));

			world.markBlockRangeForRenderUpdate(pos, pos);
			return true;
		}
	}

	public static void initTrees() {
		for (TreeDefinition tree : values()) {
			tree.init();
		}
		for (TreeDefinition tree : values()) {
			tree.registerMutations();
		}
	}

	private void init() {
		template = branch.getTemplate();
		AlleleHelper.getInstance().set(template, EnumTreeChromosome.SPECIES, species);
		setAlleles(template);

		genome = TreeManager.treeRoot.templateAsGenome(template);

		TreeManager.treeRoot.registerTemplate(template);
	}

	protected final ITreeMutationBuilder registerMutation(TreeDefinition parent1, TreeDefinition parent2, int chance) {
		return TreeManager.treeMutationFactory.createMutation(parent1.species, parent2.species, getTemplate(), chance);
	}

	@Override
	public final IAllele[] getTemplate() {
		return Arrays.copyOf(template, template.length);
	}

	public final String getUID() {
		return species.getUID();
	}

	public final String getUnlocalizedName() {
		return species.getUnlocalizedName();
	}

	@Override
	public final ITreeGenome getGenome() {
		return genome;
	}

	@Override
	public final ITree getIndividual() {
		return new Tree(genome);
	}

	@Override
	public final ItemStack getMemberStack(EnumGermlingType treeType) {
		ITree tree = getIndividual();
		return TreeManager.treeRoot.getMemberStack(tree, treeType);
	}

	public static void preInit() {
		MinecraftForge.EVENT_BUS.post(new AlleleSpeciesRegisterEvent(TreeManager.treeRoot, IAlleleTreeSpecies.class));
	}

	@Override
	public String getName() {
		return name().toLowerCase(Locale.ENGLISH);
	}

	public int getMetadata() {
		return ordinal();
	}

	public static TreeDefinition byMetadata(int meta) {
		if (meta < 0 || meta >= VALUES.length) {
			meta = 0;
		}
		return VALUES[meta];
	}

}
