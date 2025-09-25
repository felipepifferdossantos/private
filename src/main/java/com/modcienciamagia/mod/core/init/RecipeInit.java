package com.modcienciamagia.mod.core.init;

import com.modcienciamagia.mod.ModCienciaMagia;
import com.modcienciamagia.mod.core.recipes.AlchemicalTransmutationRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeInit {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ModCienciaMagia.MODID);

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, ModCienciaMagia.MODID);

    public static final RegistryObject<RecipeSerializer<AlchemicalTransmutationRecipe>> ALCHEMICAL_TRANSMUTATION_SERIALIZER =
            RECIPE_SERIALIZERS.register("alchemical_transmutation", () -> AlchemicalTransmutationRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeType<AlchemicalTransmutationRecipe>> ALCHEMICAL_TRANSMUTATION_TYPE =
            RECIPE_TYPES.register("alchemical_transmutation", () -> AlchemicalTransmutationRecipe.Type.INSTANCE);
}