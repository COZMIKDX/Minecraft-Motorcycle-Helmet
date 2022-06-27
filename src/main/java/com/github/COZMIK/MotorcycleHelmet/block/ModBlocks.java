package com.github.COZMIK.MotorcycleHelmet.block;

import com.github.COZMIK.MotorcycleHelmet.MotorcycleHelmet;
import com.github.COZMIK.MotorcycleHelmet.item.ModItemGroup;
import com.github.COZMIK.MotorcycleHelmet.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {
    // Making a block register. Similar to ModItem
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MotorcycleHelmet.MOD_ID);

    // helper methods
    // For registering blocks.
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);

        // Register the item associated item
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // Register the item associated item
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(ModItemGroup.TUTORIAL_MOD)));
    }



    // Create the block
    public static final RegistryObject<Block> AMETHYST_ORE = registerBlock("amethyst_ore", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(5f)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
