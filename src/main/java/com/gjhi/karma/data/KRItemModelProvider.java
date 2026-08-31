package com.gjhi.karma.data;

import com.gjhi.karma.Karma;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.gjhi.karma.Karma.MODID;

public class KRItemModelProvider extends ItemModelProvider {
    public KRItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }
    @Override
    protected void registerModels() {
        this.basicItem(Karma.SANS_BONE.get());
    }
}
