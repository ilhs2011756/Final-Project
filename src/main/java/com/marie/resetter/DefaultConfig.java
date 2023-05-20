package com.marie.resetter;

import java.io.File;
import java.io.IOException;

public class DefaultConfig extends Config {
    public DefaultConfig() {
        super(new File("C:\\"), new File("C:\\"), false, "", 4096, 4096);
        try {
            Resetter.configFile.createNewFile();
            Resetter.LOGGER.info("Config file created");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
