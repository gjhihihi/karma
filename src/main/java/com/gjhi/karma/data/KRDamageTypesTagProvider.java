package com.gjhi.karma.data;

import com.gjhi.karma.Karma;
import com.gjhi.karma.register.KRDamageTypes;
import com.gjhi.karma.register.KRTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class KRDamageTypesTagProvider extends TagsProvider<DamageType> {
    public KRDamageTypesTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper helper) {
        super(output, Registries.DAMAGE_TYPE, provider, Karma.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(KRTags.DamageTypes.KARMA_CAUSE);
        this.tag(KRTags.DamageTypes.KARMA)
                .add(KRDamageTypes.KARMA1)
                .add(KRDamageTypes.KARMA2)
                .add(KRDamageTypes.KARMA3)
                .add(KRDamageTypes.KARMA4);
        this.tag(DamageTypeTags.ALWAYS_HURTS_ENDER_DRAGONS)
                .addTag(KRTags.DamageTypes.KARMA);
        this.tag(DamageTypeTags.AVOIDS_GUARDIAN_THORNS)
                .addTag(KRTags.DamageTypes.KARMA);
        this.tag(DamageTypeTags.BYPASSES_ARMOR)
                .addTag(KRTags.DamageTypes.KARMA);
        this.tag(DamageTypeTags.BYPASSES_COOLDOWN)
                .addTag(KRTags.DamageTypes.KARMA);
        this.tag(DamageTypeTags.BYPASSES_EFFECTS)
                .addTag(KRTags.DamageTypes.KARMA);
        this.tag(DamageTypeTags.BYPASSES_ENCHANTMENTS)
                .addTag(KRTags.DamageTypes.KARMA);
        this.tag(DamageTypeTags.NO_ANGER)
                .addTag(KRTags.DamageTypes.KARMA);
        this.tag(DamageTypeTags.NO_IMPACT)
                .addTag(KRTags.DamageTypes.KARMA);
    }
}
