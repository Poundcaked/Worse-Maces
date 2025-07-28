package com.gorgonine.worsemaces;

import com.gorgonine.worsemaces.item.BrassBellMace;
import com.gorgonine.worsemaces.item.GamblersMace;
import com.gorgonine.worsemaces.item.UnfinishedMace;
import com.gorgonine.worsemaces.item.WoodenMace;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.WeaponComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.spongepowered.include.com.google.common.base.Function;

public class ModItems {
    public static final Item WOODEN_MACE = registerItem(
            "wooden_mace",
            WoodenMace::new,
            new Item.Settings()
                    .maxDamage(20)
                    .component(DataComponentTypes.TOOL, WoodenMace.createToolComponent())
                    .repairable(ItemTags.LOGS)
                    .attributeModifiers(WoodenMace.createAttributeModifiers())
                    .enchantable(1)
                    .component(DataComponentTypes.WEAPON, new WeaponComponent(1))

    );

    public static final Item UNFINISHED_MACE = registerItem(
            "unfinished_mace",
            UnfinishedMace::new,
            new Item.Settings()
                    .rarity(Rarity.EPIC)
                    .maxDamage(1000)
                    .component(DataComponentTypes.TOOL, UnfinishedMace.createToolComponent())
                    .attributeModifiers(UnfinishedMace.createAttributeModifiers())
                    .enchantable(15)
                    .component(DataComponentTypes.WEAPON, new WeaponComponent(1))

    );

    public static final Item GAMBLERS_MACE = registerItem(
            "gamblers_mace",
            GamblersMace::new,
            new Item.Settings()
                    .rarity(Rarity.EPIC)
                    .maxDamage(2000)
                    .component(DataComponentTypes.TOOL, GamblersMace.createToolComponent())
                    .attributeModifiers(GamblersMace.createAttributeModifiers())
                    .enchantable(15)
                    .component(DataComponentTypes.WEAPON, new WeaponComponent(1))

    );

    public static final Item BRASS_BELL_MACE = registerItem(
            "brass_bell_mace",
            BrassBellMace::new,
            new Item.Settings()
                    .rarity(Rarity.EPIC)
                    .maxDamage(2000)
                    .component(DataComponentTypes.TOOL, BrassBellMace.createToolComponent())
                    .attributeModifiers(BrassBellMace.createAttributeModifiers())
                    .enchantable(15)
                    .component(DataComponentTypes.WEAPON, new WeaponComponent(1))

    );

    public static Item registerItem(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WorseMaces.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(WOODEN_MACE);
            fabricItemGroupEntries.add(UNFINISHED_MACE);
            fabricItemGroupEntries.add(GAMBLERS_MACE);
        });
    }
}
