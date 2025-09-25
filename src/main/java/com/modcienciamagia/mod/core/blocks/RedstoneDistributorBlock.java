package com.modcienciamagia.mod.core.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class RedstoneDistributorBlock extends Block {

    public RedstoneDistributorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        // This block provides strong power, so we use getDirectSignal.
        return 0;
    }

    @Override
    public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        // The side parameter is the direction FROM this block TO the neighbor asking for power.
        // So, we should not provide power back to the input source.

        // 1. Find the strongest input signal and its direction.
        Direction inputDirection = null;
        int inputPower = 0;

        for (Direction direction : Direction.values()) {
            // Check power from neighbors
            int power = blockAccess.getLevel().getSignal(pos.relative(direction), direction);
            if (power > inputPower) {
                inputPower = power;
                inputDirection = direction;
            }
        }

        if (inputPower == 0) {
            return 0;
        }

        // 2. Do not provide power back to the strongest source.
        // The 'side' is the direction we are providing power TO.
        // 'inputDirection' is the direction power is coming FROM.
        // So if side is opposite to inputDirection, we are powering the source back.
        if (side == inputDirection) {
            return 0;
        }

        // 3. Count the number of valid outputs.
        // An output is any side that is not the input direction.
        int outputCount = 0;
        for (Direction direction : Direction.values()) {
            if (direction != inputDirection) {
                outputCount++;
            }
        }

        if (outputCount == 0) {
            return 0;
        }

        // 4. Return the divided power.
        return inputPower / outputCount;
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!oldState.is(state.getBlock())) {
            world.updateNeighborsAt(pos, this);
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!world.isClientSide) {
            // When a neighbor changes, this block's output might change.
            // We need to notify its neighbors that they should re-read the signal.
            world.updateNeighborsAt(pos, this);
        }
    }
}