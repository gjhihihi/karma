package com.gjhi.karma.library.caps;

import com.gjhi.karma.KRConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class KarmaData implements IKarmaData, INBTSerializable<CompoundTag> {
    private int karma;

    @Override
    public int getKarma() {
        return karma;
    }

    @Override
    public void setKarma(int value) {
        karma = Math.max(0, Math.min(value, KRConfig.getMaxKarma()));
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("karma", karma);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        karma = Math.max(0, Math.min(nbt.getInt("karma"), KRConfig.getMaxKarma()));
    }
}
