package com.modcienciamagia.mod.event;

import com.modcienciamagia.mod.ModCienciaMagia;
import com.modcienciamagia.mod.client.renderers.RedWitherRenderer;
import com.modcienciamagia.mod.client.renderers.RedWitherlingRenderer;
import com.modcienciamagia.mod.core.entities.boss.RedWitherEntity;
import com.modcienciamagia.mod.core.entities.minions.RedWitherlingEntity;
import com.modcienciamagia.mod.core.init.EntityInit;
import net.minecraft.client.renderer.entity.WitherSkullRenderer;
import net.minecraft.world.entity.boss.wither.Wither;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModCienciaMagia.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.RED_WITHER.get(), RedWitherRenderer::new);
        event.registerEntityRenderer(EntityInit.CORRUPTED_SKULL.get(), WitherSkullRenderer::new);
        event.registerEntityRenderer(EntityInit.RED_WITHERLING.get(), RedWitherlingRenderer::new);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        // Use Wither attributes as a base and register them for our Red Wither
        event.put(EntityInit.RED_WITHER.get(), Wither.createAttributes().build());
        event.put(EntityInit.RED_WITHERLING.get(), RedWitherlingEntity.createAttributes().build());
    }
}