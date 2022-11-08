package com.github.COZMIK.MotorcycleHelmet.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

// This is a class for making a custom tab menu in the creative mode item menu.
// It will replace the group property for an item when registering. For this tutorial, I replace that
// with this in ModItems.java.

// I don't really have plans for this at the moment, but I'm following a tutorial
// so I'll go ahead and do it anyway.

// For items like blades, I could skip this and just stick them into the weapons category.
// For items that don't fit into a category / I want a special tab for them, we'd do the below.
public class ModItemGroup {

    // An anonymous class on the right side of the expression.
    //
    public static final ItemGroup TUTORIAL_MOD = new ItemGroup("tutorialModTab") {
        @Override
        public ItemStack createIcon() {
            // not totally sure what this function does at the moment.

            // The return stuff here specifies the icon on the tab.
            return new ItemStack(ModItems.AMETHYST.get());
        }
    };
}
