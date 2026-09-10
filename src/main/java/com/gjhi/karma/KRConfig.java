package com.gjhi.karma;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

public class KRConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.IntValue MAX_KARMA = BUILDER.comment("Maximum of karma by default").defineInRange("maxKarma", 40, 0, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue INTERVAL1 = BUILDER.comment("Damage interval of karma (0, 25%]").defineInRange("interval1", 20, 1, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue INTERVAL2 = BUILDER.comment("Damage interval of karma (25%, 50%]").defineInRange("interval2", 10, 1, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue INTERVAL3 = BUILDER.comment("Damage interval of karma (50%, 75%]").defineInRange("interval3", 3, 1, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue INTERVAL4 = BUILDER.comment("Damage interval of karma (75%, 100%]").defineInRange("interval4", 1, 1, Integer.MAX_VALUE);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    private static int maxKarma;
    private static final int[] karmaInterval = new int[4];

    public static int getConfigMaxKarma(){
        return maxKarma;
    }
    public static int getKarmaInterval(int index){
        return karmaInterval[index - 1];
    }

    static void onLoad(final ModConfigEvent event) {
        maxKarma = MAX_KARMA.get();
        karmaInterval[0] = INTERVAL1.get();
        karmaInterval[1] = INTERVAL2.get();
        karmaInterval[2] = INTERVAL3.get();
        karmaInterval[3] = INTERVAL4.get();
    }
}
