package com.modcienciamagia.mod.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.modcienciamagia.mod.ModCienciaMagia;
import com.modcienciamagia.mod.core.menu.AttributeMeterMenu;
import net.minecraft.client.gui.GuiGraphics;
import com.modcienciamagia.mod.networking.PacketHandler;
import com.modcienciamagia.mod.networking.packet.RecalibrateAttributePacket;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class AttributeMeterScreen extends AbstractContainerScreen<AttributeMeterMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ModCienciaMagia.MODID, "textures/gui/attribute_meter_gui.png");

    public AttributeMeterScreen(AttributeMeterMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // Example for Attack Damage
        this.addRenderableWidget(new Button.Builder(Component.literal("+"),
                (button) -> {
                    PacketHandler.INSTANCE.sendToServer(new RecalibrateAttributePacket(this.menu.blockEntity.getBlockPos(), "attack_damage", true));
                })
                .bounds(x + 50, y + 34, 20, 20)
                .build());

        this.addRenderableWidget(new Button.Builder(Component.literal("-"),
                (button) -> {
                    PacketHandler.INSTANCE.sendToServer(new RecalibrateAttributePacket(this.menu.blockEntity.getBlockPos(), "attack_damage", false));
                })
                .bounds(x + 75, y + 34, 20, 20)
                .build());
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}