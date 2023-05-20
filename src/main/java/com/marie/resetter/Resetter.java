package com.marie.resetter;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class Resetter {
    public static Config config;
    public static File configFile = new File("config.json");
    public static final Logger LOGGER = LogManager.getLogger();
    private static Runtime runtime = Runtime.getRuntime();
    private static Process serverProcess;
    public static void main(String[] args) {
        LOGGER.info("Starting Co-Op Resetter");
        GUI gui = new GUI();
        if (configFile.exists()) {
            config = config.load();
        } else {
            config = new DefaultConfig();
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

    public static void reset() throws IOException {
        if (serverProcess.isAlive()) {
            serverProcess.destroy();
        }
        FileUtils.deleteDirectory(config.getWorld());
        serverProcess = runtime.exec("java -Xms"+ config.getMinRam()
                + "M -Xmx" + config.getMaxRam() + "M -jar "
                + config.getServerJar().toString() + ".jar --nogui");
    }
}