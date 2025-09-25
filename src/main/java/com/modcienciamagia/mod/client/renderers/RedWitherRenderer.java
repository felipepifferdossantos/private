package com.modcienciamagia.mod.client.renderers;

import com.modcienciamagia.mod.ModCienciaMagia;
import com.modcienciamagia.mod.core.entities.boss.RedWitherEntity;
import net.minecraft.client.renderer.entity.WitherBossRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.boss.wither.Wither;

public class RedWitherRenderer extends WitherBossRenderer {
    private static final ResourceLocation WITHER_INVULNERABLE_LOCATION = new ResourceLocation("textures/entity/wither/wither_invulnerable.png");
    private static final ResourceLocation WITHER_LOCATION = new ResourceLocation(ModCienciaMagia.MODID, "textures/entity/red_wither.png"); // Custom texture path

    public RedWitherRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(Wither pEntity) {
        int i = pEntity.getInvulnerableTicks();
        // Use custom texture, but keep invulnerable texture logic
        return i > 0 && (i > 80 || i / 5 % 2 != 1) ? WITHER_INVULNERABLE_LOCATION : WITHER_LOCATION;
    }
}