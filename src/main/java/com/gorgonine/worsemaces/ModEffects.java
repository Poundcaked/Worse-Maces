package com.gorgonine.worsemaces;

import com.gorgonine.worsemaces.effect.SplinterEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> SPLINTER = registerStatusEffect("splinter",
            new SplinterEffect(StatusEffectCategory.NEUTRAL, 0x452e15)
    );



    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(WorseMaces.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
    }
}
