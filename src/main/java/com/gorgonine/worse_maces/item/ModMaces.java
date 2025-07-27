package com.gorgonine.worse_maces.item;

import com.gorgonine.worse_maces.Worse_maces;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModMaces {

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Worse_maces.MOD_ID, name), item);
    }

    public static void registerModMaces() {
    }
}
