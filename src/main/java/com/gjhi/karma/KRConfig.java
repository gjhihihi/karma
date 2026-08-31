package com.gjhi.karma;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(
        modid = Karma.MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class KRConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.IntValue MAX_KARMA = BUILDER.comment("Maximum of karma").defineInRange("maxKarma", 40, 0, Integer.MAX_VALUE);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    private static int maxKarma;

    public static int getMaxKarma(){
        return maxKarma;
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        maxKarma = MAX_KARMA.get();
    }
}
