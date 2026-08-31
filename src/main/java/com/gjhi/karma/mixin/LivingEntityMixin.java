package com.gjhi.karma.mixin;

import com.gjhi.karma.KRConfig;
import com.gjhi.karma.library.caps.KarmaHelper;
import com.gjhi.karma.register.KRDamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci){
        if ((Object) this instanceof LivingEntity living) {
            KarmaHelper.getKarmaData(living).ifPresent(data -> {
                int karma = data.getKarma();
                if (karma <= 0)return;
                int maxKarma = KRConfig.getMaxKarma();
                float ratio = (float)karma / maxKarma;
                Level world = living.level();
                DamageSource source;
                int interval;
                if (ratio <= 0.25f){
                    source = KRDamageTypes.source(world.registryAccess(), KRDamageTypes.KARMA1);
                    interval = 20;
                } else if (ratio <= 0.5f){
                    source = KRDamageTypes.source(world.registryAccess(), KRDamageTypes.KARMA2);
                    interval = 10;
                } else if (ratio <= 0.75f){
                    source = KRDamageTypes.source(world.registryAccess(), KRDamageTypes.KARMA3);
                    interval = 3;
                } else {
                    source = KRDamageTypes.source(world.registryAccess(), KRDamageTypes.KARMA4);
                    interval = 1;
                }
                if (living.tickCount % interval == 0){
                    if (living.hurt(source, 1)){
                        data.removeKarma(1);
                    }
                }
            });
        }
    }
}
