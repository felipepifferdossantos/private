package com.modcienciamagia.mod.core.init;

import com.modcienciamagia.mod.ModCienciaMagia;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;

public class BiomeInit {
    public static final DeferredRegister<Biome> BIOMES =
            DeferredRegister.create(Registries.BIOME, ModCienciaMagia.MODID);

    public static final ResourceKey<Biome> FLORESTA_LUMINA = register("floresta_lumina");

    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(ModCienciaMagia.MODID, name));
    }
}