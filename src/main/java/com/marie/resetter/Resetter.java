package com.marie.resetter;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Resetter {
    public static Config config;
    public static File configFile = new File("config.json");
    private static final Runtime runtime = Runtime.getRuntime();
    private static Process serverProcess;
    public static void main(String[] args) {
        GUI gui = new GUI();
        if (configFile.exists()) {
            config = Config.load();
        } else {
            config = new Config();
        }
    }

    public static void reset() throws IOException {
        stopServer();
        startServer();
    }

    public static void stopServer() {
        if (serverProcess.isAlive()) {
            serverProcess.destroy();
        }
        try {
            FileUtils.deleteDirectory(config.getWorld());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startServer() throws IOException {
        serverProcess = runtime.exec("java -Xms"+ config.getMinRam()
                + "M -Xmx" + config.getMaxRam() + "M -jar "
                + config.getServerJar().toString() + ".jar --nogui");
    }
}