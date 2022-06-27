package com.github.COZMIK.MotorcycleHelmet.item;

import com.github.COZMIK.MotorcycleHelmet.MotorcycleHelmet;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    // I think we stick our new items in here in some form.
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MotorcycleHelmet.MOD_ID);

    // Minecraft convention is the type item variable names in all caps.
    // Changning the group item property so that it uses my ModItemGroup
    // public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst", () -> new Item(new Item.Properties().group(ModItemGroup.TUTORIAL_MOD)));

    // Called in MotorcycleHelmet constructor/
    // Makes sure the new items this mod adds are registered so minecraft can use them.
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
