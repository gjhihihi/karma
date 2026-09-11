package com.gjhi.karma.library.caps;

import com.gjhi.karma.KRConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class KarmaData implements IKarmaData, INBTSerializable<CompoundTag> {
    private int karma;
    private boolean loaded;

    @Override
    public int getKarma() {
        return karma;
    }

    @Override
    public void setKarma(int value) {
        karma = Math.max(0, value);
    }

    @Override
    public boolean isMaxKarmaLoaded() {
        return loaded;
    }

    @Override
    public void setMaxKarmaLoaded() {
        loaded = true;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("karma", karma);
        nbt.putBoolean("max_karma_loaded", loaded);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        setKarma(nbt.getInt("karma"));
        loaded = nbt.getBoolean("max_karma_loaded");
    }
}
