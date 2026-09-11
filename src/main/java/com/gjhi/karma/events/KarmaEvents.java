package com.gjhi.karma.events;

import com.gjhi.karma.KRConfig;
import com.gjhi.karma.Karma;
import com.gjhi.karma.library.caps.KarmaHelper;
import com.gjhi.karma.register.KRAttributes;
import com.gjhi.karma.register.KRTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
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
            LivingEntity living = event.getEntity();
            KarmaHelper.addKarma(living, (int) event.getAmount());
        }
    }
    @SubscribeEvent
    public static void rightClickEntity(PlayerInteractEvent.EntityInteract event){
        if (!event.isCanceled() && !event.getCancellationResult().consumesAction() && event.getTarget() instanceof LivingEntity target) {
            if (event.getItemStack().is(Karma.SANS_BONE.get())){
                KarmaHelper.setKarma(target, KarmaHelper.getMaxKarma(target));
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            }
        }
    }
    @SubscribeEvent
    public static void rightClickEmpty(PlayerInteractEvent.RightClickItem event){
        if (!event.isCanceled() && !event.getCancellationResult().consumesAction()) {
            if (event.getItemStack().is(Karma.SANS_BONE.get())){
                Player player = event.getEntity();
                if (player.isCreative() || player.isSpectator())return;
                KarmaHelper.setKarma(player, KarmaHelper.getMaxKarma(player));
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            }
        }
    }
    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event){
        if (!event.isCanceled()) {
            if (event.getEntity() instanceof LivingEntity living && !KarmaHelper.isUsingSpecialMaxKarma(living)){
                AttributeInstance instance = living.getAttribute(KRAttributes.MAX_KARMA.get());
                if (instance != null) {
                    instance.setBaseValue(KRConfig.getConfigMaxKarma());
                }
            }
        }
    }
}
