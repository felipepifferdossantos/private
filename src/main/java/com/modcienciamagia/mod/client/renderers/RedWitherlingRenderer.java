package com.modcienciamagia.mod.client.renderers;

import com.modcienciamagia.mod.ModCienciaMagia;
import com.modcienciamagia.mod.core.entities.minions.RedWitherlingEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.VexRenderer;
import net.minecraft.resources.ResourceLocation;

public class RedWitherlingRenderer extends VexRenderer {
    private static final ResourceLocation WITHERLING_TEXTURE = new ResourceLocation(ModCienciaMagia.MODID, "textures/entity/red_witherling.png");

    public RedWitherlingRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(net.minecraft.world.entity.monster.Vex pEntity) {
        // We are extending VexRenderer, so we override its getTextureLocation method,
        // but we will apply our own texture.
        return WITHERLING_TEXTURE;
    }
}