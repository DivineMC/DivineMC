package org.bxteam.divinemc.pathfinding;

import net.minecraft.world.level.pathfinder.NodeEvaluator;
import org.jetbrains.annotations.NotNull;

public interface NodeEvaluatorGenerator {
    @NotNull NodeEvaluator generate(NodeEvaluatorFeatures nodeEvaluatorFeatures);
}
