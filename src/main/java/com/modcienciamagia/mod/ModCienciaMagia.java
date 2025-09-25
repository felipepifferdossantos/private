package com.modcienciamagia.mod;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

@Mod(ModCienciaMagia.MODID)
public class ModCienciaMagia {
    public static final String MODID = "modcienciamagia";
    private static final Logger LOGGER = LogUtils.getLogger();

import com.modcienciamagia.mod.core.init.BlockInit;
import com.modcienciamagia.mod.core.init.ItemInit;

@Mod(ModCienciaMagia.MODID)
public class ModCienciaMagia {
    public static final String MODID = "modcienciamagia";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ModCienciaMagia() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Registers
        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Setting up Ciencia e Magia Mod");
    }

    // Add the items to the creative tabs
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ItemInit.ENGRENAGEM_DE_PRECISAO);
            event.accept(ItemInit.CRISTAL_RESONANTE);
            event.accept(ItemInit.MECANISMO_DE_BALANCA);
            event.accept(ItemInit.MICROSCOPIO_DE_REDSTONE);
            event.accept(ItemInit.CRISTAL_DE_CALIBRACAO);
        }

        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.accept(ItemInit.REDSTONE_DISTRIBUTOR_ITEM);
            event.accept(ItemInit.REDSTONE_AMPLIFIER_ITEM);
        }
    }
}