package com.moonfazer.supersteel.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    private static final String MOD_ID = "supersteel";

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MOD_ID);

    public static final DeferredHolder<Item, Item> STEEL_BLOCK = ITEMS.register("steel_block",
            () -> new BlockItem(ModBlocks.STEEL_BLOCK.get(), new Item.Properties()));
    public static final DeferredHolder<Item, Item> STEEL = ITEMS.register("steel",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(ModToolTiers.STEEL, new Item.Properties()
                    .stacksTo(1)
                    .attributes(SwordItem.createAttributes(ModToolTiers.STEEL, 3, -2.4F))));
    public static final DeferredHolder<Item, Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(ModToolTiers.STEEL, new Item.Properties()
                    .stacksTo(1)
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.STEEL, 1.0F, -2.8F))));
    public static final DeferredHolder<Item, Item> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(ModToolTiers.STEEL, new Item.Properties()
                    .stacksTo(1)
                    .attributes(AxeItem.createAttributes(ModToolTiers.STEEL, 6.0F, -3.1F))));
    public static final DeferredHolder<Item, Item> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(ModToolTiers.STEEL, new Item.Properties()
                    .stacksTo(1)
                    .attributes(ShovelItem.createAttributes(ModToolTiers.STEEL, 1.5F, -3.0F))));
    public static final DeferredHolder<Item, Item> STEEL_HOE = ITEMS.register("steel_hoe",
            () -> new HoeItem(ModToolTiers.STEEL, new Item.Properties()
                    .stacksTo(1)
                    .attributes(HoeItem.createAttributes(ModToolTiers.STEEL, -2.0F, -1.0F))));
    public static final DeferredHolder<Item, Item> STEEL_HELMET = ITEMS.register("steel_helmet",
            () -> new ArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1)));
    public static final DeferredHolder<Item, Item> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
            () -> new ArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1)));
    public static final DeferredHolder<Item, Item> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
            () -> new ArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1)));
    public static final DeferredHolder<Item, Item> STEEL_BOOTS = ITEMS.register("steel_boots",
            () -> new ArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1)));

    private ModItems() {
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
