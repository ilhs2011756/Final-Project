package com.marie.resetter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Properties;

public class Config {
    private File world;
    private File serverJar;
    private boolean useSeed;
    private String seed;
    private int minRam;
    private int maxRam;
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(Config.class, new ConfigAdapter()).setPrettyPrinting().create();

    public Config(File world, File serverJar, boolean useSeed, String seed, int minRam, int maxRam) {
        this.world = world;
        this.serverJar = serverJar;
        this.useSeed = useSeed;
        this.setSeed(seed);
        this.minRam = minRam;
        this.maxRam = maxRam;
        this.save();
    }

    public Config() {
        this.world = new File("C:\\");
        this.serverJar = new File("C:\\");
        this.useSeed = false;
        this.setSeed("");
        this.minRam = 4096;
        this.maxRam = 4096;
        this.save();
    }

    public File getServerJar() {
        return this.serverJar;
    }

    public File getWorld() {
        return this.world;
    }

    public String getSeed() {
        return this.seed;
    }

    public boolean isUseSeed() {
        return this.useSeed;
    }

    public int getMinRam() {
        return this.minRam;
    }

    public int getMaxRam() {
        return this.maxRam;
    }

    public void setWorld(File world) {
        this.world = world;
    }

    public void setServerJar(File serverJar) {
        this.serverJar = serverJar;
    }

    public void setUseSeed(boolean useSeed) {
        this.useSeed = useSeed;
    }

    public void setSeed(String seed) {
        if (this.useSeed) {
            try {
                Properties properties = new Properties();
                FileInputStream fileInputStream = new FileInputStream("server.properties");
                properties.load(fileInputStream);
                fileInputStream.close();

                FileOutputStream fileOutputStream = new FileOutputStream("server.properties");
                properties.setProperty("level-seed", seed);
                properties.store(fileOutputStream, null);
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.seed = seed;
    }

    public void setMinRam(int minRam) {
        this.minRam = minRam;
    }

    public void setMaxRam(int maxRam) {
        this.maxRam = maxRam;
    }

    public void save() {
        try {
            Resetter.configFile.createNewFile();

            // Write config to file
            BufferedWriter writer = new BufferedWriter(new FileWriter(Resetter.configFile));
            String json = gson.toJson(this);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Config load() {
        try {
            // Read the config from the json file
            BufferedReader reader = new BufferedReader(new FileReader(Resetter.configFile));

            return gson.fromJson(reader, Config.class);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
