package com.modcienciamagia.mod.core.init;

import com.modcienciamagia.mod.ModCienciaMagia;
import com.modcienciamagia.mod.core.effects.CorruptionEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectInit {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ModCienciaMagia.MODID);

    public static final RegistryObject<MobEffect> CORRUPTION =
            EFFECTS.register("corruption", () -> new CorruptionEffect(MobEffectCategory.HARMFUL, 0x4B0082)); // Indigo color
}