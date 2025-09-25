package com.modcienciamagia.mod.core.init;

import com.modcienciamagia.mod.ModCienciaMagia;
import com.modcienciamagia.mod.core.blocks.RedstoneAmplifierBlock;
import com.modcienciamagia.mod.core.blocks.RedstoneDistributorBlock;
import com.modcienciamagia.mod.core.blocks.AlchemicalTransmutatorBlock;
import com.modcienciamagia.mod.core.blocks.AttributeMeterBlock;
import com.modcienciamagia.mod.core.blocks.FaceSensorBlock;
import com.modcienciamagia.mod.core.blocks.WeightSensorBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModCienciaMagia.MODID);

    // Redstone Blocks
    public static final RegistryObject<Block> REDSTONE_DISTRIBUTOR = BLOCKS.register("redstone_distributor",
            () -> new RedstoneDistributorBlock(BlockBehaviour.Properties.of().strength(3.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> REDSTONE_AMPLIFIER = BLOCKS.register("redstone_amplifier",
            () -> new RedstoneAmplifierBlock(BlockBehaviour.Properties.of().strength(3.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WEIGHT_SENSOR = BLOCKS.register("weight_sensor",
            () -> new WeightSensorBlock(BlockBehaviour.Properties.of().strength(3.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FACE_SENSOR = BLOCKS.register("face_sensor",
            () -> new FaceSensorBlock(BlockBehaviour.Properties.of().strength(3.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ATTRIBUTE_METER = BLOCKS.register("attribute_meter",
            () -> new AttributeMeterBlock(BlockBehaviour.Properties.of().strength(4.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> ALCHEMICAL_TRANSMUTATOR = BLOCKS.register("alchemical_transmutator",
            () -> new AlchemicalTransmutatorBlock(BlockBehaviour.Properties.of().strength(5.0f).requiresCorrectToolForDrops().noOcclusion()));
}