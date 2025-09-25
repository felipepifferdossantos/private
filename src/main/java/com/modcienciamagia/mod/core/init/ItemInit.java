package com.modcienciamagia.mod.core.init;

import com.modcienciamagia.mod.ModCienciaMagia;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.minecraft.world.item.BlockItem;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModCienciaMagia.MODID);

    // Basic Crafting Components
    public static final RegistryObject<Item> ENGRENAGEM_DE_PRECISAO = ITEMS.register("engrenagem_de_precisao", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRISTAL_RESONANTE = ITEMS.register("cristal_resonante", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MECANISMO_DE_BALANCA = ITEMS.register("mecanismo_de_balanca", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MICROSCOPIO_DE_REDSTONE = ITEMS.register("microscopio_de_redstone", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRISTAL_DE_CALIBRACAO = ITEMS.register("cristal_de_calibracao", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CIRCULO_DE_TRANSMUTACAO_GRAVADO = ITEMS.register("circulo_de_transmutacao_gravado", () -> new Item(new Item.Properties()));

    // Block Items
    public static final RegistryObject<Item> REDSTONE_DISTRIBUTOR_ITEM = ITEMS.register("redstone_distributor",
            () -> new BlockItem(BlockInit.REDSTONE_DISTRIBUTOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> REDSTONE_AMPLIFIER_ITEM = ITEMS.register("redstone_amplifier",
            () -> new BlockItem(BlockInit.REDSTONE_AMPLIFIER.get(), new Item.Properties()));
    public static final RegistryObject<Item> WEIGHT_SENSOR_ITEM = ITEMS.register("weight_sensor",
            () -> new BlockItem(BlockInit.WEIGHT_SENSOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> FACE_SENSOR_ITEM = ITEMS.register("face_sensor",
            () -> new BlockItem(BlockInit.FACE_SENSOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> ATTRIBUTE_METER_ITEM = ITEMS.register("attribute_meter",
            () -> new BlockItem(BlockInit.ATTRIBUTE_METER.get(), new Item.Properties()));
    public static final RegistryObject<Item> ALCHEMICAL_TRANSMUTATOR_ITEM = ITEMS.register("alchemical_transmutator",
            () -> new BlockItem(BlockInit.ALCHEMICAL_TRANSMUTATOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> AGED_IRON_BLOCK_ITEM = ITEMS.register("aged_iron_block",
            () -> new BlockItem(BlockInit.AGED_IRON_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> AGED_COPPER_BLOCK_ITEM = ITEMS.register("aged_copper_block",
            () -> new BlockItem(BlockInit.AGED_COPPER_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> POLISHED_WOOD_PLANKS_ITEM = ITEMS.register("polished_wood_planks",
            () -> new BlockItem(BlockInit.POLISHED_WOOD_PLANKS.get(), new Item.Properties()));
    public static final RegistryObject<Item> TEMPERED_GLASS_ITEM = ITEMS.register("tempered_glass",
            () -> new BlockItem(BlockInit.TEMPERED_GLASS.get(), new Item.Properties()));
    public static final RegistryObject<Item> DECORATIVE_GEAR_ITEM = ITEMS.register("decorative_gear",
            () -> new BlockItem(BlockInit.DECORATIVE_GEAR.get(), new Item.Properties()));
}