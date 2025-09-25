package com.modcienciamagia.mod.core.init;

import com.modcienciamagia.mod.ModCienciaMagia;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.modcienciamagia.mod.core.items.PhilosopherStoneItem;
import net.minecraft.world.item.BlockItem;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModCienciaMagia.MODID);

    // Catalyst Items
    public static final RegistryObject<Item> PHILOSOPHER_STONE = ITEMS.register("philosopher_stone",
            () -> new PhilosopherStoneItem(new Item.Properties().fireResistant()));

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
    public static final RegistryObject<Item> CORRUPTED_MATTER_BLOCK_ITEM = ITEMS.register("corrupted_matter_block",
            () -> new BlockItem(BlockInit.CORRUPTED_MATTER_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> CORRUPTED_REDSTONE_BLOCK_ITEM = ITEMS.register("corrupted_redstone_block",
            () -> new BlockItem(BlockInit.CORRUPTED_REDSTONE_BLOCK.get(), new Item.Properties()));

    // Boss-related Items
    public static final RegistryObject<Item> TRANSMUTED_WITHER_SKULL = ITEMS.register("transmuted_wither_skull",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CORRUPTED_HEART_FRAGMENT = ITEMS.register("corrupted_heart_fragment",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TRANSMUTATION_ESSENCE = ITEMS.register("transmutation_essence",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ATTRIBUTE_ESSENCE = ITEMS.register("attribute_essence",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GRAVITATIONAL_FRAGMENT = ITEMS.register("gravitational_fragment",
            () -> new Item(new Item.Properties()));

    // Weapons
    public static final RegistryObject<Item> GLAIVE_OF_GRAVITY = ITEMS.register("glaive_of_gravity",
            () -> new GlaiveOfGravityItem(new Item.Properties().stacksTo(1)));
}