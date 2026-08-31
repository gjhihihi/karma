package com.gjhi.karma.register;

import com.gjhi.karma.Karma;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

public class KRDamageTypes {
    public static ResourceKey<DamageType> KARMA1 = create("karma1");
    public static ResourceKey<DamageType> KARMA2 = create("karma2");
    public static ResourceKey<DamageType> KARMA3 = create("karma3");
    public static ResourceKey<DamageType> KARMA4 = create("karma4");
    private static ResourceKey<DamageType> create(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, Karma.getResource(name));
    }
    public static void bootstrap(BootstapContext<DamageType> context){
        context.register(
                KARMA1,
                new DamageType(Karma.prefix("karma1"), DamageScaling.NEVER, 0)
        );
        context.register(
                KARMA2,
                new DamageType(Karma.prefix("karma2"), DamageScaling.NEVER, 0)
        );
        context.register(
                KARMA3,
                new DamageType(Karma.prefix("karma3"), DamageScaling.NEVER, 0)
        );
        context.register(
                KARMA4,
                new DamageType(Karma.prefix("karma4"), DamageScaling.NEVER, 0)
        );
    }

    public static DamageSource source(RegistryAccess access, ResourceKey<DamageType> type, @Nullable Entity direct, @Nullable Entity causing) {
        return new DamageSource(access.registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type), direct, causing);
    }

    public static DamageSource source(RegistryAccess access, ResourceKey<DamageType> type, @Nullable Entity entity) {
        return source(access, type, entity, entity);
    }

    public static DamageSource source(RegistryAccess access, ResourceKey<DamageType> type) {
        return source(access, type, null, null);
    }
}
