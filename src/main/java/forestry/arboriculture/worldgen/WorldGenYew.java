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
package forestry.arboriculture.worldgen;

import forestry.api.world.ITreeGenData;
import forestry.arboriculture.worldgen.TreeBlockTypeLeaf;
import forestry.arboriculture.worldgen.TreeBlockTypeLog;
import forestry.arboriculture.worldgen.WorldGenTree;
import forestry.core.worldgen.WorldGenHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class WorldGenYew extends WorldGenTree {

	public WorldGenYew(ITreeGenData tree) {
		super(tree, 7, 3);
	}

	@Override
	public Set<BlockPos> generateTrunk(World world, Random rand, TreeBlockTypeLog wood, BlockPos startPos) {
		WorldGenHelper.generateTreeTrunk(world, rand, wood, startPos, height, girth, 0, 0, null, 0);

		int branchHeight = 4;
		if (rand.nextBoolean()) {
			branchHeight--;
		}

		int branchRadius = height / 2;

		return WorldGenHelper.generateBranches(world, rand, wood, startPos.add(0, branchHeight, 0), girth, 0.5f, 0.5f, branchRadius, 2, 1.0f);
	}

	@Override
	protected void generateLeaves(World world, Random rand, TreeBlockTypeLeaf leaf, List<BlockPos> branchEnds, BlockPos startPos) {
		int leafSpawn = height + 1;

		WorldGenHelper.generateCylinderFromTreeStartPos(world, leaf, startPos.add(0, leafSpawn--, 0), girth, girth, 1, WorldGenHelper.EnumReplaceMode.SOFT);
		WorldGenHelper.generateCylinderFromTreeStartPos(world, leaf, startPos.add(0, leafSpawn--, 0), girth, 0.5f + girth, 1, WorldGenHelper.EnumReplaceMode.SOFT);

		while (leafSpawn > 4) {
			WorldGenHelper.generateCylinderFromTreeStartPos(world, leaf, startPos.add(0, leafSpawn--, 0), girth, 2f + girth, 1, WorldGenHelper.EnumReplaceMode.SOFT);
		}
		if (rand.nextBoolean()) {
			WorldGenHelper.generateCylinderFromTreeStartPos(world, leaf, startPos.add(0, leafSpawn--, 0), girth, 2f + girth, 1, WorldGenHelper.EnumReplaceMode.SOFT);
		}

		for (BlockPos branchEnd : branchEnds) {
			WorldGenHelper.generateCylinderFromPos(world, leaf, branchEnd, 1.0f + girth, 2, WorldGenHelper.EnumReplaceMode.AIR);
		}

		WorldGenHelper.generateCylinderFromTreeStartPos(world, leaf, startPos.add(0, leafSpawn, 0), girth, 1.5f + girth, 1, WorldGenHelper.EnumReplaceMode.SOFT);
	}
}
