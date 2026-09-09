package com.gjhi.karma.library.caps;

import com.gjhi.karma.register.KRAttributes;
import com.gjhi.karma.register.KRCapabilities;
import com.gjhi.karma.register.KRNetworkHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.PacketDistributor;

import java.util.concurrent.atomic.AtomicInteger;

public class KarmaHelper {
    public static boolean hasKarmaData(LivingEntity living){
        return getKarmaData(living).isPresent();
    }
    static LazyOptional<IKarmaData> getKarmaData(LivingEntity living){
        return living.getCapability(KRCapabilities.KARMA_DATA);
    }

    private static void syncPlayerKarmaData(Player player){
        if (player.level().isClientSide)return;
        KarmaHelper.getKarmaData(player).ifPresent(data -> {
            if (data instanceof KarmaData KarmaData) {
                KRNetworkHandler.CHANNEL.send(
                        PacketDistributor.PLAYER.with(() -> (ServerPlayer) player),
                        new KarmaDataSyncPacket(player.getUUID(), KarmaData.serializeNBT())
                );
            }
        });
    }
    public static int getKarma(LivingEntity living) {
        AtomicInteger karma = new AtomicInteger(0);
        KarmaHelper.getKarmaData(living).ifPresent(data -> karma.set(data.getKarma()));
        return karma.get();
    }
    public static int getMaxKarma(LivingEntity living) {
        AttributeInstance instance = living.getAttribute(KRAttributes.MAX_KARMA.get());
        if (instance != null) {
            return Math.max((int) instance.getValue(), 0);
        }
        return 0;
    }
    public static void setKarma(LivingEntity living, int value) {
        KarmaHelper.getKarmaData(living).ifPresent(data -> {
            data.setKarma(Math.min(value, KarmaHelper.getMaxKarma(living)));
            if (living instanceof Player player) {
                KarmaHelper.syncPlayerKarmaData(player);
            }
        });
    }
    public static void addKarma(LivingEntity living, int value) {
        setKarma(living, getKarma(living) + value);
    }
    public static void removeKarma(LivingEntity living, int value) {
        setKarma(living, getKarma(living) - value);
    }
}
