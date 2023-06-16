package com.marie.resetter;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

// main class for the program
public class Resetter {
    public static Config config;
    public static File configFile = new File("config.json");
    private static final Runtime runtime = Runtime.getRuntime();
    private static Process serverProcess;

//    startup code, loads config, and starts GUI
    public static void main(String[] args) {
        if (configFile.exists()) {
            config = Config.load();
        } else {
            config = new Config();
        }
        new GUI();
    }

//    stops the server, then starts it again
    public static void reset() throws IOException {
        stopServer();
        startServer();
    }

//    stops the server and deletes the world file
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

//    starts the server
    public static void startServer() throws IOException {
        serverProcess = runtime.exec("java -Xms"+ config.getMinRam()
                + "M -Xmx" + config.getMaxRam() + "M -jar \""
                + config.getServerJar().toString() + "\" --nogui");
    }
}