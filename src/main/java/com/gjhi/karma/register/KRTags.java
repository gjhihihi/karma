package com.gjhi.karma.register;

import com.gjhi.karma.Karma;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

@SuppressWarnings("removal")
public class KRTags {
    public static void init() {
        DamageTypes.init();
    }

    public static class DamageTypes {

        /**
         * Damage Types with the tag can add karma to entities.
         */
        public static final TagKey<DamageType> KARMA_CAUSE = local("karma_cause");
        /**
         * Damage Types with the tag are karma damage.
         */
        public static final TagKey<DamageType> KARMA = local("karma");

        private static void init() {
        }

        private static TagKey<DamageType> forge(String name) {
            return TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("forge", name));
        }

        private static TagKey<DamageType> local(String name) {
            return TagKey.create(Registries.DAMAGE_TYPE, Karma.getResource(name));
        }
    }
}