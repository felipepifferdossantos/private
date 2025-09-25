package com.modcienciamagia.mod.core.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class FaceSensorBlock extends DirectionalBlock {

    public FaceSensorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getClickedFace());
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        // This block provides power from its back, opposite to where it's facing.
        if (side == blockState.getValue(FACING)) {
            return 0;
        }
        return getOutputSignal(blockAccess, pos, blockState);
    }

    @Override
    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return getDirectSignal(blockState, blockAccess, pos, side);
    }

    private int getOutputSignal(BlockGetter world, BlockPos pos, BlockState state) {
        Direction facing = state.getValue(FACING);
        BlockPos targetPos = pos.relative(facing);
        BlockState targetState = world.getBlockState(targetPos);

        // Based on the design document's texture values
        if (targetState.is(BlockTags.WOOL) || targetState.is(BlockTags.PLANKS)) {
            return 6; // Medium signal for wood/wool
        }
        if (targetState.is(BlockTags.STONE_BRICKS) || targetState.is(BlockTags.DOORS)) {
             return 4; // Lower-Medium for processed stone/wood
        }
        if (targetState.is(BlockTags.LOGS)) {
            return 7;
        }
        if (targetState.is(BlockTags.SAND) || targetState.is(BlockTags.DIRT)) {
            return 9; // High signal for raw earth materials
        }
        if (targetState.is(BlockTags.ICE)) {
            return 2; // Low signal for smooth ice
        }
        if (targetState.is(BlockTags.LEAVES)) {
            return 5;
        }
        if (targetState.getBlock().toString().contains("glass")) {
            return 1; // Very low for smooth glass
        }
        if (targetState.getBlock().toString().contains("obsidian") || targetState.getBlock().toString().contains("netherrack")) {
            return 14; // Very high for corrupted/special blocks
        }
        if (targetState.is(BlockTags.BEACON_BASE_BLOCKS)) { // Iron, Gold, Diamond, Emerald
            return 3; // Low signal for polished metals
        }
        if (targetState.is(BlockTags.BASE_STONE_OVERWORLD) || targetState.is(BlockTags.BASE_STONE_NETHER)) {
            return 11; // High signal for raw stone
        }

        return 0;
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!world.isClientSide) {
            world.updateNeighborsAt(pos, this);
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        // If the block we are facing changes, we need to update our signal
        if (fromPos.equals(pos.relative(state.getValue(FACING)))) {
            if (!world.isClientSide) {
                world.updateNeighborsAt(pos, this);
            }
        }
    }
}