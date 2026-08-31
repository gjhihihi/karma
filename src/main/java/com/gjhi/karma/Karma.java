package com.gjhi.karma;

import com.gjhi.karma.data.KRDamageTypeProvider;
import com.gjhi.karma.data.KRDamageTypesTagProvider;
import com.gjhi.karma.data.KRItemModelProvider;
import com.gjhi.karma.register.KRCapabilities;
import com.gjhi.karma.register.KRDamageTypes;
import com.gjhi.karma.register.KRTags;
import com.mojang.logging.LogUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("removal")
@Mod(Karma.MODID)
public class Karma {
    public static final String MODID = "karma";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final RegistryObject<Item> SANS_BONE = ITEMS.register("sans_bone", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)){
        @Override
        public boolean isFoil(ItemStack p_41453_) {
            return true;
        }
    });

    public Karma() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        //bus.addListener(this::commonSetup);
        ITEMS.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
        bus.addListener(this::addCreative);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, KRConfig.SPEC);
        KRTags.init();
        bus.addListener(this::gatherData);
        bus.addListener(KRCapabilities::registerCapabilities);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.OP_BLOCKS && event.hasPermissions()) {
            event.accept(SANS_BONE);
        }
    }

    private void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();
        boolean server = event.includeServer();
        boolean client = event.includeClient();
        generator.addProvider(server, new KRDamageTypeProvider(output, registries));
        generator.addProvider(server, new KRDamageTypesTagProvider(output, registries, helper));
        generator.addProvider(client, new KRItemModelProvider(output, helper));
    }

    public static ResourceLocation getResource(String id){
        return new ResourceLocation(MODID, id);
    }
    public static String prefix(String name) {
        return MODID + "." + name.toLowerCase(Locale.US);
    }
}
