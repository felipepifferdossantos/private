package com.modcienciamagia.mod.core.entities.projectiles;

import com.modcienciamagia.mod.core.init.EffectInit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class CorruptedSkullEntity extends WitherSkull {
    public CorruptedSkullEntity(EntityType<? extends WitherSkull> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public CorruptedSkullEntity(Level pLevel, LivingEntity pShooter, double pX, double pY, double pZ) {
        super(EntityType.WITHER_SKULL, pLevel);
        this.setOwner(pShooter);
        this.setPos(pX, pY, pZ);
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 1.0F, false, Level.ExplosionInteraction.MOB);
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!this.level().isClientSide) {
            Entity entity = pResult.getEntity();
            Entity owner = this.getOwner();
            if (entity instanceof LivingEntity livingentity) {
                // Apply the Corruption effect for 10 seconds (200 ticks)
                livingentity.addEffect(new MobEffectInstance(EffectInit.CORRUPTION.get(), 200), owner);
            }
        }
    }

    @Override
    public boolean isDangerous() {
        return true; // Skulls are dangerous and cause explosions
    }
}