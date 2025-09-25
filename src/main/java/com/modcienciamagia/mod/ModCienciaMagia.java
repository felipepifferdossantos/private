package com.modcienciamagia.mod;

import com.mojang.logging.LogUtils;
import com.modcienciamagia.mod.client.screens.AlchemicalTransmutatorScreen;
import com.modcienciamagia.mod.client.screens.AttributeMeterScreen;
import com.modcienciamagia.mod.core.init.BlockEntityInit;
import com.modcienciamagia.mod.core.init.BlockInit;
import com.modcienciamagia.mod.core.init.ItemInit;
import com.modcienciamagia.mod.core.init.MenuInit;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ModCienciaMagia.MODID)
public class ModCienciaMagia {
    public static final String MODID = "modcienciamagia";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ModCienciaMagia() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        BlockEntityInit.BLOCK_ENTITIES.register(modEventBus);
        MenuInit.MENUS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::clientSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Setting up Ciencia e Magia Mod");
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(MenuInit.ATTRIBUTE_METER_MENU.get(), AttributeMeterScreen::new);
            MenuScreens.register(MenuInit.ALCHEMICAL_TRANSMUTATOR_MENU.get(), AlchemicalTransmutatorScreen::new);
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ItemInit.ENGRENAGEM_DE_PRECISAO);
            event.accept(ItemInit.CRISTAL_RESONANTE);
            event.accept(ItemInit.MECANISMO_DE_BALANCA);
            event.accept(ItemInit.MICROSCOPIO_DE_REDSTONE);
            event.accept(ItemInit.CRISTAL_DE_CALIBRACAO);
            event.accept(ItemInit.CIRCULO_DE_TRANSMUTACAO_GRAVADO);
        }

        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.accept(ItemInit.REDSTONE_DISTRIBUTOR_ITEM);
            event.accept(ItemInit.REDSTONE_AMPLIFIER_ITEM);
            event.accept(ItemInit.WEIGHT_SENSOR_ITEM);
            event.accept(ItemInit.FACE_SENSOR_ITEM);
        }

        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ItemInit.ATTRIBUTE_METER_ITEM);
            event.accept(ItemInit.ALCHEMICAL_TRANSMUTATOR_ITEM);
        }

        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ItemInit.AGED_IRON_BLOCK_ITEM);
            event.accept(ItemInit.AGED_COPPER_BLOCK_ITEM);
            event.accept(ItemInit.POLISHED_WOOD_PLANKS_ITEM);
            event.accept(ItemInit.TEMPERED_GLASS_ITEM);
            event.accept(ItemInit.DECORATIVE_GEAR_ITEM);
        }
    }
}