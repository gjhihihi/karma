package com.gjhi.karma.library.caps;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class KarmaDataSyncPacket {
    private final UUID playerUUID;
    private final CompoundTag dataTag;
    public KarmaDataSyncPacket(UUID playerUUID, CompoundTag dataTag) {
        this.playerUUID = playerUUID;
        this.dataTag = dataTag;
    }
    public static void encode(KarmaDataSyncPacket packet, FriendlyByteBuf buf) {
        buf.writeUUID(packet.playerUUID);
        buf.writeNbt(packet.dataTag);
    }
    public static KarmaDataSyncPacket decode(FriendlyByteBuf buf){
        return new KarmaDataSyncPacket(buf.readUUID(), buf.readNbt());
    }
    public static void handle(KarmaDataSyncPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player == null || !player.getUUID().equals(packet.playerUUID))return;
            KarmaHelper.getKarmaData(player).ifPresent(data -> {
                if (data instanceof KarmaData contractData) {
                    contractData.deserializeNBT(packet.dataTag);
                }
            });
        });
        context.get().setPacketHandled(true);
    }
}
