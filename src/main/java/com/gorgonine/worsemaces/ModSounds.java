package com.gorgonine.worsemaces;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent bell_hit = registerSoundEvent("bell_hit");
    public static final SoundEvent tinnitus = registerSoundEvent("tinnitus");
    public static final SoundEvent pufferfish_noise = registerSoundEvent("pufferfish_noise");

    private static SoundEvent registerSoundEvent(String name){
        Identifier id = Identifier.of(WorseMaces.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds(){

    }
}
