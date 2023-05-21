package com.marie.resetter;

import java.io.File;
import java.io.IOException;

public class DefaultConfig extends Config {
    private static final File world = new File("C:\\");
    private static final File serverJar = new File("C:\\");
    private static final boolean useSeed = false;
    private static final String seed = "";
    private static final int minRam = 4096;
    private static final int maxRam = 4096;
    public DefaultConfig() {
        super(world, serverJar, useSeed, seed, minRam, maxRam);
        try {
            Resetter.configFile.createNewFile();
            Resetter.LOGGER.info("Config file created");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
