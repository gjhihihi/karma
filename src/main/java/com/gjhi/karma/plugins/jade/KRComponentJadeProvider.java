package com.gjhi.karma.plugins.jade;

import com.gjhi.karma.KRConfig;
import com.gjhi.karma.Karma;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum KRComponentJadeProvider implements IEntityComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor accessor, IPluginConfig config) {
        CompoundTag nbt = accessor.getServerData();
        if (nbt.contains("karma_jade")) {
            tooltip.add(tooltip.getElementHelper().text(
                    Component.translatable("tooltip.karma.jade.karma", nbt.getInt("karma_jade"), KRConfig.getMaxKarma())
            ));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return Karma.getResource("karma_component");
    }
}
