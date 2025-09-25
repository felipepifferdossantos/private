package com.modcienciamagia.mod.core.init;

import com.modcienciamagia.mod.ModCienciaMagia;
import com.modcienciamagia.mod.core.blockentities.AlchemicalTransmutatorBlockEntity;
import com.modcienciamagia.mod.core.blockentities.AttributeMeterBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ModCienciaMagia.MODID);

    public static final RegistryObject<BlockEntityType<AttributeMeterBlockEntity>> ATTRIBUTE_METER =
            BLOCK_ENTITIES.register("attribute_meter", () ->
                    BlockEntityType.Builder.of(AttributeMeterBlockEntity::new,
                            BlockInit.ATTRIBUTE_METER.get()).build(null));

    public static final RegistryObject<BlockEntityType<AlchemicalTransmutatorBlockEntity>> ALCHEMICAL_TRANSMUTATOR =
            BLOCK_ENTITIES.register("alchemical_transmutator", () ->
                    BlockEntityType.Builder.of(AlchemicalTransmutatorBlockEntity::new,
                            BlockInit.ALCHEMICAL_TRANSMUTATOR.get()).build(null));
}