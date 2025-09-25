package com.modcienciamagia.mod.core.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class PhilosopherStoneItem extends Item {
    public PhilosopherStoneItem(Properties properties) {
        super(properties.defaultDurability(256)); // Set durability, e.g., 256 uses
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack container = itemStack.copy();
        if (container.hurt(1, null, null)) {
            return ItemStack.EMPTY;
        } else {
            return container;
        }
    }
}