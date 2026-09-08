package com.gjhi.karma.plugins.kubejs;

import com.gjhi.karma.KRConfig;
import com.gjhi.karma.library.caps.KarmaHelper;
import net.minecraft.world.entity.LivingEntity;

import java.util.concurrent.atomic.AtomicInteger;

public class KRKubeJSFunctions {
    public static int getKarma(LivingEntity living) {
        AtomicInteger karma = new AtomicInteger(0);
        KarmaHelper.getKarmaData(living).ifPresent(data -> karma.set(data.getKarma()));
        return karma.get();
    }
    public static int getMaxKarma(LivingEntity living) {
        return KRConfig.getMaxKarma();
    }
    public static void setKarma(LivingEntity living, int value) {
        KarmaHelper.getKarmaData(living).ifPresent(data -> data.setKarma(value));
    }
    public static void addKarma(LivingEntity living, int value) {
        KarmaHelper.getKarmaData(living).ifPresent(data -> data.addKarma(value));
    }
    public static void removeKarma(LivingEntity living, int value) {
        KarmaHelper.getKarmaData(living).ifPresent(data -> data.removeKarma(value));
    }
}
