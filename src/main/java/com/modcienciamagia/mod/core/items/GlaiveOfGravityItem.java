package com.modcienciamagia.mod.core.items;

import com.modcienciamagia.mod.core.util.ModTiers;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class GlaiveOfGravityItem extends SwordItem {
    public GlaiveOfGravityItem(Properties properties) {
        super(ModTiers.GRAVITY, 3, -2.4F, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.ENDER_DRAGON_FLAP, SoundSource.PLAYERS, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!pLevel.isClientSide) {
            double radius = 5.0;
            AABB area = new AABB(pPlayer.blockPosition()).inflate(radius);
            List<LivingEntity> entities = pLevel.getEntitiesOfClass(LivingEntity.class, area, (entity) -> entity != pPlayer);

            for (LivingEntity entity : entities) {
                double dx = entity.getX() - pPlayer.getX();
                double dz = entity.getZ() - pPlayer.getZ();
                double distance = Math.sqrt(dx * dx + dz * dz);
                if (distance > 0) {
                    entity.knockback(1.5, -dx / distance, -dz / distance);
                }
            }

            pPlayer.getCooldowns().addCooldown(this, 100); // 5-second cooldown
            itemstack.hurtAndBreak(5, pPlayer, (player) -> player.broadcastBreakEvent(pUsedHand));
        }

        return InteractionResultHolder.success(itemstack);
    }
}