package com.gjhi.karma.library.caps;

import com.gjhi.karma.register.KRCapabilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.util.LazyOptional;

public class KarmaHelper {
    public static LazyOptional<IKarmaData> getKarmaData(LivingEntity living){
        return living.getCapability(KRCapabilities.KARMA_DATA);
    }
    public static IKarmaData getKarmaDataOrDefault(LivingEntity living){
        return getKarmaData(living).orElse(new KarmaData());
    }
}
