package com.gorgonine.worsemaces;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorseMaces implements ModInitializer {
    public static final String MOD_ID = "worsemaces";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModEffects.registerEffects();
        ModItemGroups.registerItemGroups();
        ModSounds.registerSounds();
    }
}
