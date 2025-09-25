package com.modcienciamagia.mod.core.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class WeightSensorBlock extends Block {

    public WeightSensorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        // Tick randomly to check for entities and update signal
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!world.isClientSide) {
            // Notify neighbors to re-read the signal strength
            world.updateNeighborsAt(pos, this);
        }
    }

    @Override
    public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return calculateOutputSignal(blockAccess.getLevel(), pos);
    }

    @Override
    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return getDirectSignal(blockState, blockAccess, pos, side);
    }

    private int calculateOutputSignal(Level world, BlockPos pos) {
        if (world == null) {
            return 0;
        }

        int totalWeight = 0;
        BlockPos posAbove = pos.above();

        // 1. Check for entities
        AABB detectionArea = new AABB(posAbove);
        List<Entity> entities = world.getEntitiesOfClass(Entity.class, detectionArea, (e) -> !e.isSpectator());
        if (!entities.isEmpty()) {
            for (Entity entity : entities) {
                totalWeight += getEntityWeight(entity);
            }
        }

        // 2. Check for inventories (like chests, hoppers)
        BlockState stateAbove = world.getBlockState(posAbove);
        if (stateAbove.hasBlockEntity()) {
            BlockEntity be = world.getBlockEntity(posAbove);
            if (be instanceof Container) {
                Container container = (Container) be;
                int itemCount = 0;
                for (int i = 0; i < container.getContainerSize(); i++) {
                    itemCount += container.getItem(i).getCount();
                }
                // Add weight based on item count, e.g., 1 power per 5 stacks.
                totalWeight += Math.min(15, itemCount / (64 * 5));
            }
        }

        return Math.min(15, totalWeight);
    }

    private int getEntityWeight(Entity entity) {
        if (entity instanceof Player) return 8;
        if (entity instanceof net.minecraft.world.entity.animal.IronGolem) return 15;
        if (entity instanceof net.minecraft.world.entity.animal.Cow) return 10;
        if (entity instanceof net.minecraft.world.entity.animal.Pig) return 7;
        if (entity instanceof net.minecraft.world.entity.animal.Sheep) return 6;
        if (entity instanceof net.minecraft.world.entity.animal.Chicken) return 3;
        if (entity instanceof net.minecraft.world.entity.monster.Zombie) return 8;
        if (entity instanceof ItemEntity) {
            return Math.min(15, ((ItemEntity) entity).getItem().getCount() / 8 + 1);
        }
        return 1; // Default weight for other entities
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!world.isClientSide) {
            world.updateNeighborsAt(pos, this);
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!world.isClientSide) {
            world.updateNeighborsAt(pos, this);
        }
    }
}