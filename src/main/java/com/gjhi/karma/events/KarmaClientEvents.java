package com.gjhi.karma.events;

import com.gjhi.karma.Karma;
import com.gjhi.karma.library.caps.KarmaHelper;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = Karma.MODID,
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class KarmaClientEvents {
    @SubscribeEvent
    public static void onRenderKarma(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player != null && !player.isCreative() && !player.isSpectator()) {
            if (event.getOverlay() == VanillaGuiOverlay.PLAYER_HEALTH.type()) {
                int karma = KarmaHelper.getKarma(player);
                if (karma > 0) {
                    GuiGraphics graphics = event.getGuiGraphics();
                    Font font = mc.font;
                    String text = String.valueOf(karma);
                    int textWidth = font.width(text);
                    Window window = event.getWindow();
                    int screenWidth = window.getGuiScaledWidth();
                    int screenHeight = window.getGuiScaledHeight();
                    int heartStartX = screenWidth / 2 - 91;
                    int textX = heartStartX - textWidth - 4;
                    int textY = screenHeight - 39 + 1;
                    graphics.drawString(font, text, textX, textY, 0xFF55FF, true);
                }
            }
        }
    }
}
