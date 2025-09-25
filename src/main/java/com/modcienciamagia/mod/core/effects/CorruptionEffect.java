package com.modcienciamagia.mod.core.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class CorruptionEffect extends MobEffect {
    public CorruptionEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        // This effect will drain life. We can do this by applying instant damage.
        // The "no natural healing" is implicitly handled by the constant damage.
        pLivingEntity.hurt(pLivingEntity.damageSources().magic(), 1.0F + pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        // Apply the effect every second (20 ticks)
        int i = 20 >> pAmplifier;
        if (i > 0) {
            return pDuration % i == 0;
        } else {
            return true;
        }
    }
}