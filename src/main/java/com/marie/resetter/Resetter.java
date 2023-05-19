package com.marie.resetter;

import com.google.gson.Gson;
import com.marie.resetter.config.Config;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

public class Resetter {
    public static Config config;
    public static File configFile = new File("config.json");
    public static final Logger LOGGER = LogManager.getLogger();
    public static void main(String[] args) {
        LOGGER.info("Starting Co-Op Resetter");
        if (configFile.exists()) {
            config = Config.load();
        } else {
            config = Config.create(new File("placeholder"), new File("placeholder"), false, "placeholder");
        }
    }

//    old method, keeping for now to remember server.properties stuff
//    public static Config loadConfig() throws IOException {
//        // Read the config from the json file
//        Gson gson = new Gson();
//        BufferedReader reader = new BufferedReader(new FileReader("config.json"));
//        Config config = gson.fromJson(reader, Config.class);
//        if (config.isUseSeed()) {
//            System.out.println("What seed would you like to use?");
//            Scanner sc = new Scanner(System.in);
//            Properties properties = new Properties();
//            FileInputStream fileInputStream = new FileInputStream("server.properties");
//            properties.load(fileInputStream);
//            fileInputStream.close();
//
//            FileOutputStream fileOutputStream = new FileOutputStream("server.properties");
//            properties.setProperty("level-seed", sc.nextLine());
//            properties.store(fileOutputStream, null);
//            fileOutputStream.close();
//        }
//        reader.close();
//        return config;
//    }

    public static void reset(Config config) throws IOException {
        Scanner sc = new Scanner(System.in);
        Runtime runtime = Runtime.getRuntime();
        String worldName = config.getWorld().toString();
        File worldFile = new File(worldName);
        String jar = config.getServerJar().toString();
        try {
            runtime.exec("java -Xms4096M -Xmx4096M -jar " + jar + ".jar --nogui");
            if (worldFile.exists()) {
                FileUtils.deleteDirectory(worldFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            System.out.println("Type 'reset' to reset and 'quit' to exit");
            if (sc.nextLine().equalsIgnoreCase("reset")) {
                System.out.println("Resetting...");
            } else if (sc.nextLine().equalsIgnoreCase("quit")) {
                System.out.println("I'm sorry, I either didn't understand or you exited");
                break;
            }
        }
    }
}