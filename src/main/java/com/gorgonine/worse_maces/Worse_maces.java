package com.gorgonine.worse_maces;

import com.gorgonine.worse_maces.item.ModMaces;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Worse_maces implements ModInitializer {
    public static final String MOD_ID = "worse_maces";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModMaces.registerModMaces();
    }
}
