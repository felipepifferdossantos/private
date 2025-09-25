package com.modcienciamagia.mod.core.entities.boss;

import com.modcienciamagia.mod.core.entities.minions.RedWitherlingEntity;
import com.modcienciamagia.mod.core.entities.projectiles.CorruptedSkullEntity;
import com.modcienciamagia.mod.core.init.BlockInit;
import com.modcienciamagia.mod.core.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.boss.wither.Wither;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;

public class RedWitherEntity extends Wither implements RangedAttackMob {

    public RedWitherEntity(EntityType<? extends Wither> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new WitherDoNothingGoal());
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
        this.goalSelector.addGoal(3, new SummonWitherlingsGoal()); // Summon minions
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide) {
            // Periodically transmute blocks around the boss
            if (this.tickCount % 20 == 0 && this.getHealth() > 0) {
                transmuteBlocksAround();
            }
        }
    }

    private void transmuteBlocksAround() {
        if (this.level().isClientSide) return;

        int radius = 5;
        for (int i = 0; i < 10; ++i) { // Try to transmute 10 blocks per tick
            int x = Mth.floor(this.getX()) + this.random.nextInt(radius * 2 + 1) - radius;
            int y = Mth.floor(this.getY()) + this.random.nextInt(radius + 1);
            int z = Mth.floor(this.getZ()) + this.random.nextInt(radius * 2 + 1) - radius;
            BlockPos targetPos = new BlockPos(x, y, z);
            BlockState targetState = this.level().getBlockState(targetPos);

            if (!targetState.isAir() && !targetState.is(Blocks.BEDROCK) && !targetState.is(BlockInit.CORRUPTED_MATTER_BLOCK.get())) {
                this.level().setBlock(targetPos, BlockInit.CORRUPTED_MATTER_BLOCK.get().defaultBlockState(), 3);
            }
        }
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
        this.launchSkullTo(0, pTarget.getX(), pTarget.getY() + pTarget.getEyeHeight(), pTarget.getZ(), true);
    }

    public void launchSkullTo(int p_31510_, double p_31511_, double p_31512_, double p_31513_, boolean p_31514_) {
        if (!this.level().isClientSide) {
            double d0 = this.getHeadX(p_31510_);
            double d1 = this.getHeadY(p_31510_);
            double d2 = this.getHeadZ(p_31510_);
            double d3 = p_31511_ - d0;
            double d4 = p_31512_ - d1;
            double d5 = p_31513_ - d2;
            Projectile projectile = new CorruptedSkullEntity(this.level(), this, d3, d4, d5);
            projectile.setPos(d0, d1, d2);
            this.level().addFreshEntity(projectile);
        }
    }

    class WitherDoNothingGoal extends Goal {
        public WitherDoNothingGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            return RedWitherEntity.this.getInvulnerableTicks() > 0;
        }
    }

    class SummonWitherlingsGoal extends Goal {
        private int summonTime;

        public SummonWitherlingsGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            return RedWitherEntity.this.getTarget() != null && RedWitherEntity.this.getInvulnerableTicks() == 0 && this.summonTime <= 0;
        }

        public void start() {
            this.summonTime = 400; // Cooldown: 20 seconds
            int count = 2 + RedWitherEntity.this.random.nextInt(3); // Summon 2 to 4 minions
            for (int i = 0; i < count; ++i) {
                BlockPos spawnPos = RedWitherEntity.this.blockPosition().offset(-2 + RedWitherEntity.this.random.nextInt(5), 1, -2 + RedWitherEntity.this.random.nextInt(5));
                RedWitherlingEntity witherling = EntityInit.RED_WITHERLING.get().create(RedWitherEntity.this.level());
                if (witherling != null) {
                    witherling.moveTo(spawnPos, 0.0F, 0.0F);
                    RedWitherEntity.this.level().addFreshEntity(witherling);
                }
            }
        }

        public void tick() {
            --this.summonTime;
        }
    }
}