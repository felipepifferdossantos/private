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

    // Block Items
    public static final RegistryObject<Item> REDSTONE_DISTRIBUTOR_ITEM = ITEMS.register("redstone_distributor",
            () -> new BlockItem(BlockInit.REDSTONE_DISTRIBUTOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> REDSTONE_AMPLIFIER_ITEM = ITEMS.register("redstone_amplifier",
            () -> new BlockItem(BlockInit.REDSTONE_AMPLIFIER.get(), new Item.Properties()));
}