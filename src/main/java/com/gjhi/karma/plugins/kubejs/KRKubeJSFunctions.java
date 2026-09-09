package com.gjhi.karma.plugins.kubejs;

import com.gjhi.karma.library.caps.KarmaHelper;
import net.minecraft.world.entity.LivingEntity;

public class KRKubeJSFunctions {
    public static int getKarma(LivingEntity living) {
        return KarmaHelper.getKarma(living);
    }
    public static int getMaxKarma(LivingEntity living) {
        return KarmaHelper.getMaxKarma(living);
    }
    public static void setKarma(LivingEntity living, int value) {
        KarmaHelper.setKarma(living, value);
    }
    public static void addKarma(LivingEntity living, int value) {
        KarmaHelper.addKarma(living, value);
    }
    public static void removeKarma(LivingEntity living, int value) {
        KarmaHelper.removeKarma(living, value);
    }
}
