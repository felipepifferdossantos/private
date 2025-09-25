package com.modcienciamagia.mod.networking.packet;

import com.modcienciamagia.mod.core.blockentities.AttributeMeterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RecalibrateAttributePacket {
    private final BlockPos pos;
    private final String attribute;
    private final boolean increase;

    public RecalibrateAttributePacket(BlockPos pos, String attribute, boolean increase) {
        this.pos = pos;
        this.attribute = attribute;
        this.increase = increase;
    }

    public RecalibrateAttributePacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
        this.attribute = buf.readUtf();
        this.increase = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeUtf(attribute);
        buf.writeBoolean(increase);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // SERVER-SIDE LOGIC
            ServerPlayer player = context.getSender();
            if (player == null) return;

            BlockEntity be = player.level().getBlockEntity(pos);
            if (be instanceof AttributeMeterBlockEntity attributeMeter) {
                attributeMeter.recalibrate(attribute, increase, player);
            }
        });
        return true;
    }
}