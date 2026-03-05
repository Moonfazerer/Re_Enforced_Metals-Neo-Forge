package com.moonfazer.supersteel;

import com.moonfazer.supersteel.registry.ModBlocks;
import com.moonfazer.supersteel.registry.ModArmorMaterials;
import com.moonfazer.supersteel.registry.ModCreativeTabs;
import com.moonfazer.supersteel.registry.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
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
        NeoForge.EVENT_BUS.addListener(this::onPlayerTick);
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
            event.accept(ModItems.PLATINUM_ORE.get());
            event.accept(ModItems.DEEPSLATE_PLATINUM_ORE.get());
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.STEEL.get());
            event.accept(ModItems.RAW_PLATINUM.get());
            event.accept(ModItems.PLATINUM.get());
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.STEEL_SWORD.get());
            event.accept(ModItems.COPPER_SWORD.get());
            event.accept(ModItems.STEEL_HELMET.get());
            event.accept(ModItems.STEEL_CHESTPLATE.get());
            event.accept(ModItems.STEEL_LEGGINGS.get());
            event.accept(ModItems.STEEL_BOOTS.get());
            event.accept(ModItems.COPPER_HELMET.get());
            event.accept(ModItems.COPPER_CHESTPLATE.get());
            event.accept(ModItems.COPPER_LEGGINGS.get());
            event.accept(ModItems.COPPER_BOOTS.get());
            event.accept(ModItems.PLATINUM_HELMET.get());
            event.accept(ModItems.PLATINUM_CHESTPLATE.get());
            event.accept(ModItems.PLATINUM_LEGGINGS.get());
            event.accept(ModItems.PLATINUM_BOOTS.get());
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.STEEL_PICKAXE.get());
            event.accept(ModItems.STEEL_AXE.get());
            event.accept(ModItems.STEEL_SHOVEL.get());
            event.accept(ModItems.STEEL_HOE.get());
            event.accept(ModItems.COPPER_PICKAXE.get());
            event.accept(ModItems.COPPER_AXE.get());
            event.accept(ModItems.COPPER_SHOVEL.get());
            event.accept(ModItems.COPPER_HOE.get());
        }
    }

    private void onPlayerTick(PlayerTickEvent.Post event) {
        var player = event.getEntity();
        if (player.level().isClientSide) {
            return;
        }

        int platinumPieces = 0;
        if (player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.PLATINUM_HELMET.get())) {
            platinumPieces++;
        }
        if (player.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.PLATINUM_CHESTPLATE.get())) {
            platinumPieces++;
        }
        if (player.getItemBySlot(EquipmentSlot.LEGS).is(ModItems.PLATINUM_LEGGINGS.get())) {
            platinumPieces++;
        }
        if (player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.PLATINUM_BOOTS.get())) {
            platinumPieces++;
        }

        if (platinumPieces > 0) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, platinumPieces - 1, false, false, true));
        }
    }
}
