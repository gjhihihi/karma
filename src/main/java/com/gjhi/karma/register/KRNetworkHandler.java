package com.gjhi.karma.register;

import com.gjhi.karma.Karma;
import com.gjhi.karma.library.caps.KarmaDataSyncPacket;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class KRNetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            Karma.getResource("main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
    private static int packetId = 0;
    public static void register() {
        CHANNEL.registerMessage(
                packetId++,
                KarmaDataSyncPacket.class,
                KarmaDataSyncPacket::encode,
                KarmaDataSyncPacket::decode,
                KarmaDataSyncPacket::handle
        );
    }
}
