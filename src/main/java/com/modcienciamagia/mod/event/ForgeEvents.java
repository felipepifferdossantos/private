package com.modcienciamagia.mod.event;

import com.modcienciamagia.mod.ModCienciaMagia;
import com.modcienciamagia.mod.core.init.ItemInit;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModCienciaMagia.MODID)
public class ForgeEvents {
    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        if (event.getEntity() instanceof EnderMan) {
            // Add a 10% chance to drop Attribute Essence
            if (event.getEntity().level().random.nextFloat() < 0.1F) {
                event.getDrops().add(new ItemEntity(event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), new ItemStack(ItemInit.ATTRIBUTE_ESSENCE.get())));
            }
        }

        if (event.getEntity() instanceof Shulker) {
            // Add a guaranteed drop for Gravitational Fragment
            event.getDrops().add(new ItemEntity(event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), new ItemStack(ItemInit.GRAVITATIONAL_FRAGMENT.get())));
        }
    }
}