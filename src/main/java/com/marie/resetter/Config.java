package com.marie.resetter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class Config {
    private File world;
    private File serverJar;
    private boolean useSeed;
    private String seed;
    private int minRam;
    private int maxRam;

    public Config(File world, File serverJar, boolean useSeed, String seed, int minRam, int maxRam) {
        this.world = world;
        this.serverJar = serverJar;
        this.useSeed = useSeed;
        this.seed = seed;
        this.minRam = minRam;
        this.maxRam = maxRam;
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

    public Config save() {
        try {
            Resetter.configFile.createNewFile();
            Resetter.LOGGER.info("Config file created");

            //  write config to file
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            BufferedWriter writer = new BufferedWriter(new FileWriter(Resetter.configFile));

            String json = gson.toJson(this);

            writer.write(json);
            writer.close();

            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Config load() {
        try {
            // Read the config from the json file
            Gson gson = new Gson();
            BufferedReader reader = new BufferedReader(new FileReader("config.json"));

            return gson.fromJson(reader, Config.class);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
