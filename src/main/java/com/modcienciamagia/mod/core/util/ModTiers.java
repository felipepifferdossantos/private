package com.modcienciamagia.mod.core.util;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import java.util.List;
import com.modcienciamagia.mod.core.init.ItemInit;
import net.minecraft.world.item.Tiers;

public class ModTiers {
    public static final ForgeTier GRAVITY = new ForgeTier(
            4, // harvestLevel
            1500, // maxUses
            8.0f, // efficiency
            7.0f, // attackDamage
            20, // enchantability
            TierSortingRegistry.getSortedTiers(),
            List.of(Tiers.DIAMOND),
            List.of(() -> Ingredient.of(ItemInit.GRAVITATIONAL_FRAGMENT.get()))
    );
}