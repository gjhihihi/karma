package com.gjhi.karma.register;

import com.gjhi.karma.KRConfig;
import com.gjhi.karma.Karma;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KRAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, Karma.MODID);

    public static final RegistryObject<Attribute> MAX_KARMA = ATTRIBUTES.register("generic.max_karma",
            () -> new RangedAttribute("attribute.karma.generic.max_karma", 40, 0, 1024).setSyncable(true)
    );

    public static void onEntityAttributeModification(EntityAttributeModificationEvent event) {
        event.getTypes().forEach(type -> {
            if (!event.has(type, MAX_KARMA.get())) {
                event.add(type, MAX_KARMA.get());
            }
        });
    }
}
