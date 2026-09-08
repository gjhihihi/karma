package com.gjhi.karma.plugins.jade;

import com.gjhi.karma.Karma;
import com.gjhi.karma.library.caps.KarmaHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IServerDataProvider;

public enum KRDataJadeProvider implements IServerDataProvider<EntityAccessor> {
    INSTANCE;

    @Override
    public void appendServerData(CompoundTag nbt, EntityAccessor accessor) {
        if (accessor.getEntity() instanceof LivingEntity living) {
            nbt.putInt("karma_jade", KarmaHelper.getKarma(living));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return Karma.getResource("karma_data");
    }
}
