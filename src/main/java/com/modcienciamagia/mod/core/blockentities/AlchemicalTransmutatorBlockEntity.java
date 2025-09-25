package com.modcienciamagia.mod.core.blockentities;

import com.modcienciamagia.mod.core.init.BlockEntityInit;
import com.modcienciamagia.mod.core.menu.AlchemicalTransmutatorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import com.modcienciamagia.mod.core.init.ItemInit;
import com.modcienciamagia.mod.core.recipes.AlchemicalTransmutationRecipe;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AlchemicalTransmutatorBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public AlchemicalTransmutatorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityInit.ALCHEMICAL_TRANSMUTATOR.get(), pPos, pBlockState);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.modcienciamagia.alchemical_transmutator");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new AlchemicalTransmutatorMenu(pContainerId, pPlayerInventory, this);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, AlchemicalTransmutatorBlockEntity pBlockEntity) {
        if(pLevel.isClientSide()) {
            return;
        }

        SimpleContainer inventory = new SimpleContainer(pBlockEntity.itemHandler.getSlots());
        for (int i = 0; i < pBlockEntity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, pBlockEntity.itemHandler.getStackInSlot(i));
        }

        Optional<AlchemicalTransmutationRecipe> match = pLevel.getRecipeManager()
                .getRecipeFor(AlchemicalTransmutationRecipe.Type.INSTANCE, inventory, pLevel);

        if(match.isPresent()) {
            ItemStack catalyst = pBlockEntity.itemHandler.getStackInSlot(1);
            if (catalyst.is(ItemInit.PHILOSOPHER_STONE.get())) {
                craftItem(pBlockEntity, match.get());
            }
        }
    }

    private static void craftItem(AlchemicalTransmutatorBlockEntity pBlockEntity, AlchemicalTransmutationRecipe recipe) {
        Level level = pBlockEntity.level;
        SimpleContainer inventory = new SimpleContainer(pBlockEntity.itemHandler.getSlots());
        for (int i = 0; i < pBlockEntity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, pBlockEntity.itemHandler.getStackInSlot(i));
        }

        if(hasRecipe(pBlockEntity)) {
            pBlockEntity.itemHandler.extractItem(0,1, false);
            pBlockEntity.itemHandler.getStackInSlot(1).hurt(1, level.random, null);
            pBlockEntity.itemHandler.setStackInSlot(2, new ItemStack(recipe.getResultItem(level.registryAccess()).getItem(),
                    pBlockEntity.itemHandler.getStackInSlot(2).getCount() + 1));
        }
    }

    private static boolean hasRecipe(AlchemicalTransmutatorBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<AlchemicalTransmutationRecipe> match = level.getRecipeManager()
                .getRecipeFor(AlchemicalTransmutationRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem(level.registryAccess()))
                && hasCatalystInSlot(entity);
    }

    private static boolean hasCatalystInSlot(AlchemicalTransmutatorBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(1).is(ItemInit.PHILOSOPHER_STONE.get());
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(2).getItem() == output.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }
}