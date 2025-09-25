package com.modcienciamagia.mod.core.init;

import com.modcienciamagia.mod.ModCienciaMagia;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import com.modcienciamagia.mod.core.blocks.RedstoneAmplifierBlock;
import com.modcienciamagia.mod.core.blocks.RedstoneDistributorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModCienciaMagia.MODID);

    // Redstone Blocks
    public static final RegistryObject<Block> REDSTONE_DISTRIBUTOR = BLOCKS.register("redstone_distributor",
            () -> new RedstoneDistributorBlock(BlockBehaviour.Properties.of().strength(3.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> REDSTONE_AMPLIFIER = BLOCKS.register("redstone_amplifier",
            () -> new RedstoneAmplifierBlock(BlockBehaviour.Properties.of().strength(3.5f).requiresCorrectToolForDrops()));
}