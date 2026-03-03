package com.moonfazer.supersteel.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public final class ModBlocks {
    private static final String MOD_ID = "supersteel";

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, MOD_ID);

    public static final DeferredHolder<Block, Block> STEEL_BLOCK = BLOCKS.register("steel_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(5.0F, 3_600_000.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    private ModBlocks() {
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
