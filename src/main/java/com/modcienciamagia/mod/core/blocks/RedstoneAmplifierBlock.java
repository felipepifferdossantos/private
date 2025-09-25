package com.modcienciamagia.mod.core.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class RedstoneAmplifierBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    private static final int AMPLIFICATION_FACTOR = 2; // Simple amplification factor

    public RedstoneAmplifierBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, Boolean.valueOf(false)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        if (!blockState.getValue(POWERED)) {
            return 0;
        }
        // Provide power only in the direction it's facing
        return blockState.getValue(FACING) == side ? getOutputSignal(blockAccess.getLevel(), pos, blockState) : 0;
    }

    @Override
    public int getSignal(BlockState p_60481_, BlockGetter p_60482_, BlockPos p_60483_, Direction p_60484_) {
        return this.getDirectSignal(p_60481_, p_60482_, p_60483_, p_60484_);
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!world.isClientSide) {
            if (state.getValue(POWERED) != this.shouldBePowered(world, pos, state)) {
                world.scheduleTick(pos, this, 1);
            }
        }
    }

    @Override
    public void onPlace(BlockState p_55469_, Level p_55470_, BlockPos p_55471_, BlockState p_55472_, boolean p_55473_) {
        if (!p_55470_.isClientSide) {
            p_55470_.scheduleTick(p_55471_, this, 1);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        boolean shouldBePowered = this.shouldBePowered(world, pos, state);
        if (state.getValue(POWERED) != shouldBePowered) {
            world.setBlock(pos, state.setValue(POWERED, shouldBePowered), 2);
            world.updateNeighborsAt(pos, this);
        }
    }

    protected boolean shouldBePowered(Level world, BlockPos pos, BlockState state) {
        return this.getInputSignal(world, pos, state) > 0;
    }

    protected int getInputSignal(Level world, BlockPos pos, BlockState state) {
        Direction direction = state.getValue(FACING);
        BlockPos blockpos = pos.relative(direction);
        int i = world.getSignal(blockpos, direction);
        if (i >= 15) {
            return i;
        } else {
            BlockState blockstate = world.getBlockState(blockpos);
            return Math.max(i, blockstate.is(this) ? 0 : world.getDirectSignal(blockpos, direction));
        }
    }

    protected int getOutputSignal(Level world, BlockPos pos, BlockState state) {
        int input = this.getInputSignal(world, pos, state);
        if (input == 0) {
            return 0;
        }
        // Amplify the signal, capped at 15
        return Math.min(input * AMPLIFICATION_FACTOR, 15);
    }
}