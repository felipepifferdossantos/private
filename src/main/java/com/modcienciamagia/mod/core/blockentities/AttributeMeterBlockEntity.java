package com.modcienciamagia.mod.core.blockentities;

import com.modcienciamagia.mod.core.init.BlockEntityInit;
import com.modcienciamagia.mod.core.menu.AttributeMeterMenu;
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
import com.google.common.collect.Multimap;
import com.modcienciamagia.mod.core.init.ItemInit;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TieredItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class AttributeMeterBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(2); // 0: Input, 1: Catalyst
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public AttributeMeterBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityInit.ATTRIBUTE_METER.get(), pPos, pBlockState);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.modcienciamagia.attribute_meter");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new AttributeMeterMenu(pContainerId, pPlayerInventory, this);
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

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, AttributeMeterBlockEntity pBlockEntity) {
        // Tick logic can be added here if needed, e.g., for processing over time.
    }

    public void recalibrate(String attributeId, boolean increase, ServerPlayer player) {
        ItemStack tool = this.itemHandler.getStackInSlot(0);
        ItemStack catalyst = this.itemHandler.getStackInSlot(1);

        if (tool.isEmpty() || !(tool.getItem() instanceof TieredItem) || catalyst.isEmpty() || !catalyst.is(ItemInit.ATTRIBUTE_ESSENCE.get())) {
            return;
        }

        if (player.experienceLevel < 1 && !player.isCreative()) {
            return;
        }

        // Consume resources
        if(!player.isCreative()) {
            player.giveExperienceLevels(-1);
            catalyst.shrink(1);
        }

        CompoundTag nbt = tool.getOrCreateTag();
        ListTag modifiers = nbt.getList("AttributeModifiers", 10);

        boolean foundModifier = false;
        if ("attack_damage".equals(attributeId)) {
            for (int i = 0; i < modifiers.size(); i++) {
                CompoundTag modifierTag = modifiers.getCompound(i);
                if ("minecraft:generic.attack_damage".equals(modifierTag.getString("AttributeName"))) {
                    double amount = modifierTag.getDouble("Amount");
                    double newAmount = increase ? amount + 0.5 : amount - 0.5;
                    // Prevent going below 0 or into negative damage
                    if (newAmount < 0) newAmount = 0;
                    modifierTag.putDouble("Amount", newAmount);
                    foundModifier = true;
                    break;
                }
            }
             if (!foundModifier && increase) {
                CompoundTag newModifier = new CompoundTag();
                newModifier.putString("AttributeName", "minecraft:generic.attack_damage");
                newModifier.putString("Name", "generic.attack_damage");
                newModifier.putDouble("Amount", 0.5);
                newModifier.putInt("Operation", 0);
                newModifier.putUUID("UUID", UUID.randomUUID());
                newModifier.putString("Slot", "mainhand");
                modifiers.add(newModifier);
            }
        }

        nbt.put("AttributeModifiers", modifiers);
        tool.setTag(nbt);
        setChanged();
    }
}