package forestry.arboriculture.worldgen;

import forestry.api.world.ITreeGenData;
import forestry.arboriculture.worldgen.TreeBlockTypeLeaf;
import forestry.arboriculture.worldgen.WorldGenTree;
import forestry.core.worldgen.WorldGenHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class WorldGenAvocado extends WorldGenTree {

    public WorldGenAvocado(ITreeGenData tree) {
        super(tree, 6, 3);
    }

    @Override
    protected void generateLeaves(World world, Random rand, TreeBlockTypeLeaf leaf, List<BlockPos> branchEnds, BlockPos startPos) {
        int yCenter = height - girth;
        yCenter = yCenter > 2 ? yCenter : 3;
        int radius = Math.round((2 + rand.nextInt(girth)) * (height / 4.0f));
        if (radius > 4) {
            radius = 4;
        }
        WorldGenHelper.generateSphereFromTreeStartPos(world, startPos.add(0, yCenter, 0), girth, radius, leaf, WorldGenHelper.EnumReplaceMode.AIR);
    }
}
