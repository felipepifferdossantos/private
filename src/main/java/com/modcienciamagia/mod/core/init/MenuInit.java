package com.modcienciamagia.mod.core.init;

import com.modcienciamagia.mod.ModCienciaMagia;
import com.modcienciamagia.mod.core.menu.AlchemicalTransmutatorMenu;
import com.modcienciamagia.mod.core.menu.AttributeMeterMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuInit {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, ModCienciaMagia.MODID);

    public static final RegistryObject<MenuType<AttributeMeterMenu>> ATTRIBUTE_METER_MENU =
            MENUS.register("attribute_meter_menu", () -> IForgeMenuType.create(AttributeMeterMenu::new));

    public static final RegistryObject<MenuType<AlchemicalTransmutatorMenu>> ALCHEMICAL_TRANSMUTATOR_MENU =
            MENUS.register("alchemical_transmutator_menu", () -> IForgeMenuType.create(AlchemicalTransmutatorMenu::new));
}