package com.moonfazer.supersteel.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public final class ModToolTiers {
    public static final Tier STEEL = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            300,
            7.0F,
            3.0F,
            14,
            () -> Ingredient.of(ModItems.STEEL.get())
    );
    public static final Tier COPPER = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            220,
            6.0F,
            2.0F,
            10,
            () -> Ingredient.of(Items.COPPER_INGOT)
    );

    private ModToolTiers() {
    }
}
