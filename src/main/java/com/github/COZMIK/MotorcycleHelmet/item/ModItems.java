package com.github.COZMIK.MotorcycleHelmet.item;

import com.github.COZMIK.MotorcycleHelmet.MotorcycleHelmet;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    // I think we stick our new items in here in some form.
    // The deferredRegister class will deal with setting resourcelocation using my modid and all that junk for me.
    // So, I guess I only have to use deferredregisters.
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MotorcycleHelmet.MOD_ID);

    //public static <T extends SwordItem>

    // Minecraft convention is the type item variable names in all caps.
    // Changning the group item property so that it uses my ModItemGroup
    // public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    // I think this is adding the amethyst to the deferred register and returning a registry object.
    // I don't use AMETHYST yet...
    public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst", () -> new Item(new Item.Properties().group(ModItemGroup.TUTORIAL_MOD)));
    public static final RegistryObject<Item> AMETHYST_KNIFE = ITEMS.register("amethyst_knife", () -> new SwordItem(ItemTier.IRON, 3, -2.4F, (new Item.Properties()).group(ItemGroup.COMBAT)));

    // Called in MotorcycleHelmet constructor/
    // Makes sure the new items this mod adds are registered so minecraft can use them.
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
