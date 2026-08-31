package com.gjhi.karma.events;

import com.gjhi.karma.KRConfig;
import com.gjhi.karma.Karma;
import com.gjhi.karma.library.caps.KarmaHelper;
import com.gjhi.karma.register.KRTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = Karma.MODID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class KarmaEvents {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onHurt(LivingHurtEvent event){
        if (event.getSource().is(KRTags.DamageTypes.KARMA_CAUSE)){
            KarmaHelper.getKarmaData(event.getEntity()).ifPresent(data -> data.addKarma((int) event.getAmount()));
        }
    }
    @SubscribeEvent
    public static void rightClick(PlayerInteractEvent.EntityInteract event){
        if(!event.isCanceled() && !event.getCancellationResult().consumesAction() && event.getTarget() instanceof LivingEntity target) {
            if (event.getItemStack().is(Karma.SANS_BONE.get())){
                KarmaHelper.getKarmaData(target).ifPresent(data -> {
                    data.setKarma(KRConfig.getMaxKarma());
                    event.setCancellationResult(InteractionResult.SUCCESS);
                });
            }
        }
    }
}
