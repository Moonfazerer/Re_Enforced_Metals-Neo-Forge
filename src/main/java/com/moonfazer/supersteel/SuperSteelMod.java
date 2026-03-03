package com.moonfazer.supersteel;

import com.moonfazer.supersteel.registry.ModBlocks;
import com.moonfazer.supersteel.registry.ModArmorMaterials;
import com.moonfazer.supersteel.registry.ModCreativeTabs;
import com.moonfazer.supersteel.registry.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;

@Mod(SuperSteelMod.MOD_ID)
public final class SuperSteelMod {
    public static final String MOD_ID = "supersteel";
    private static final Logger LOGGER = LogUtils.getLogger();

    public SuperSteelMod(IEventBus modEventBus, ModContainer modContainer) {
        ModArmorMaterials.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            var steelBlockId = ResourceLocation.fromNamespaceAndPath(MOD_ID, "steel_block");
            var steelId = ResourceLocation.fromNamespaceAndPath(MOD_ID, "steel");
            LOGGER.info("Block registered {}: {}", steelBlockId, BuiltInRegistries.BLOCK.containsKey(steelBlockId));
            LOGGER.info("Item registered {}: {}", steelBlockId, BuiltInRegistries.ITEM.containsKey(steelBlockId));
            LOGGER.info("Item registered {}: {}", steelId, BuiltInRegistries.ITEM.containsKey(steelId));
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModItems.STEEL_BLOCK.get());
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.STEEL.get());
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.STEEL_SWORD.get());
            event.accept(ModItems.STEEL_HELMET.get());
            event.accept(ModItems.STEEL_CHESTPLATE.get());
            event.accept(ModItems.STEEL_LEGGINGS.get());
            event.accept(ModItems.STEEL_BOOTS.get());
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.STEEL_PICKAXE.get());
            event.accept(ModItems.STEEL_AXE.get());
            event.accept(ModItems.STEEL_SHOVEL.get());
            event.accept(ModItems.STEEL_HOE.get());
        }
    }
}
