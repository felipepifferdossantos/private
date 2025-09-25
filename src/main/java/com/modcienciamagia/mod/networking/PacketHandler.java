package com.modcienciamagia.mod.networking;

import com.modcienciamagia.mod.ModCienciaMagia;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import com.modcienciamagia.mod.networking.packet.RecalibrateAttributePacket;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(ModCienciaMagia.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        int id = 0;
        INSTANCE.registerMessage(id++, RecalibrateAttributePacket.class, RecalibrateAttributePacket::toBytes, RecalibrateAttributePacket::new, RecalibrateAttributePacket::handle);
    }
}