package com.marie.resetter;

import com.google.gson.Gson;

import java.io.*;

public class Config {
    private File world;
    private File serverJar;
    private boolean useSeed;
    private String seed;
    private int minRam;
    private int maxRam;

    private Config(File world, File serverJar, boolean useSeed, String seed, int minRam, int maxRam) {
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

    public static Config create(File world, File serverJar, boolean useSeed, String seed, int minRam, int maxRam) {
        try {
            Resetter.configFile.createNewFile();
            Resetter.LOGGER.info("Config file created");

            //  write config to file
            Gson gson = new Gson();
            BufferedWriter writer = new BufferedWriter(new FileWriter(Resetter.configFile));

            Config config = new Config(world, serverJar, useSeed, seed, minRam, maxRam);
            String json = gson.toJson(config);
            writer.write(json);
            writer.close();

            return config;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Config load() {
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
