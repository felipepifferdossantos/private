package com.modcienciamagia.mod.core.init;

import com.modcienciamagia.mod.ModCienciaMagia;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class StructureInit {
    public static final DeferredRegister<StructureType<?>> STRUCTURES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, ModCienciaMagia.MODID);

    // Placeholder for a custom Structure class if needed later. For now, JSON is sufficient.
    // public static final RegistryObject<StructureType<MyCustomStructure>> MY_STRUCTURE =
    //         STRUCTURES.register("my_structure", () -> () -> MyCustomStructure.CODEC);
}