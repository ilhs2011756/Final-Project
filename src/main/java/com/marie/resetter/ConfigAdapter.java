package com.marie.resetter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.IOException;

// custom TypeAdapter for this program, for whatever reason it didn't work with the default one
public class ConfigAdapter extends TypeAdapter<Config> {

//    parses the data to json
    @Override
    public void write(JsonWriter out, Config config) throws IOException {
        out.beginObject();
        out.name("world");
        out.value(config.getWorld().getAbsolutePath());
        out.name("serverJar");
        out.value(config.getServerJar().getAbsolutePath());
        out.name("useSeed");
        out.value(config.isUseSeed());
        out.name("seed");
        out.value(config.getSeed());
        out.name("minRam");
        out.value(config.getMinRam());
        out.name("maxRam");
        out.value(config.getMaxRam());
        out.endObject();
    }

//    parses the data from json
    @Override
    public Config read(JsonReader in) throws IOException {
        Config config;
        in.beginObject();
        String fieldName = null;

        File world = null;
        File serverJar = null;
        boolean useSeed = false;
        String seed = null;
        int minRam = 0;
        int maxRam = 0;

        while (in.hasNext()) {
            JsonToken token = in.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldName = in.nextName();
            }
            if ("world".equals(fieldName)) {
                token = in.peek();
                world = new File(in.nextString());
            }
            if ("serverJar".equals(fieldName)) {
                token = in.peek();
                serverJar = new File(in.nextString());
            }
            if ("useSeed".equals(fieldName)) {
                token = in.peek();
                useSeed = in.nextBoolean();
            }
            if ("seed".equals(fieldName)) {
                token = in.peek();
                seed = in.nextString();
            }
            if ("minRam".equals(fieldName)) {
                token = in.peek();
                minRam = in.nextInt();
            }
            if ("maxRam".equals(fieldName)) {
                token = in.peek();
                maxRam = in.nextInt();
            }
        }
        in.endObject();
        return new Config(world, serverJar, useSeed, seed, minRam, maxRam);
    }
}
