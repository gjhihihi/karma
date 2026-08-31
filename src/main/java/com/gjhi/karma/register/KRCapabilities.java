package com.gjhi.karma.register;

import com.gjhi.karma.Karma;
import com.gjhi.karma.library.caps.IKarmaData;
import com.gjhi.karma.library.caps.KarmaData;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.gjhi.karma.Karma.MODID;

@Mod.EventBusSubscriber(
        modid = MODID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class KRCapabilities {
    public static final Capability<IKarmaData> KARMA_DATA = CapabilityManager.get(new CapabilityToken<>() {
        @Override
        public String toString() {
            return MODID + ":karma_data";
        }
    });
    
    public static void registerCapabilities(RegisterCapabilitiesEvent event){
        event.register(IKarmaData.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event){
        if (event.getObject() instanceof LivingEntity){
            KarmaData data = new KarmaData();
            LazyOptional<IKarmaData> optional = LazyOptional.of(() -> data);
            ICapabilityProvider provider = new ICapabilityProvider() {
                @Override
                public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                    return cap == KARMA_DATA? optional.cast(): LazyOptional.empty();
                }
            };
            event.addCapability(Karma.getResource("karma_data"), provider);
        }
    }
}
