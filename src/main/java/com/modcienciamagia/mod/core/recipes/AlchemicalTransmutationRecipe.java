package com.modcienciamagia.mod.core.recipes;

import com.google.gson.JsonObject;
import com.modcienciamagia.mod.ModCienciaMagia;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class AlchemicalTransmutationRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final Ingredient input;

    public AlchemicalTransmutationRecipe(ResourceLocation id, ItemStack output, Ingredient input) {
        this.id = id;
        this.output = output;
        this.input = input;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        }
        return input.test(pContainer.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public Ingredient getInput() {
        return input;
    }

    public static class Type implements RecipeType<AlchemicalTransmutationRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "alchemical_transmutation";
    }

    public static class Serializer implements RecipeSerializer<AlchemicalTransmutationRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(ModCienciaMagia.MODID, "alchemical_transmutation");

        @Override
        public AlchemicalTransmutationRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));
            Ingredient input = Ingredient.fromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "input"));
            return new AlchemicalTransmutationRecipe(pRecipeId, output, input);
        }

        @Override
        public @Nullable AlchemicalTransmutationRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            Ingredient input = Ingredient.fromNetwork(pBuffer);
            ItemStack output = pBuffer.readItem();
            return new AlchemicalTransmutationRecipe(pRecipeId, output, input);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, AlchemicalTransmutationRecipe pRecipe) {
            pRecipe.getInput().toNetwork(pBuffer);
            pBuffer.writeItem(pRecipe.getResultItem(null));
        }
    }
}