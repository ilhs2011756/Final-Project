package com.marie.resetter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.IOException;

public class ConfigAdapter extends TypeAdapter<Config> {

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

    @Override
    public Config read(JsonReader in) throws IOException {
        Config config = new Config();
        in.beginObject();
        String fieldName = null;

        while (in.hasNext()) {
            JsonToken token = in.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldName = in.nextName();
            }
            if ("world".equals(fieldName)) {
                token = in.peek();
                config.setWorld(new File(in.nextString()));
            }
            if ("serverJar".equals(fieldName)) {
                token = in.peek();
                config.setServerJar(new File(in.nextString()));
            }
            if ("useSeed".equals(fieldName)) {
                token = in.peek();
                config.setUseSeed(in.nextBoolean());
            }
            if ("seed".equals(fieldName)) {
                token = in.peek();
                config.setSeed(in.nextString());
            }
            if ("minRam".equals(fieldName)) {
                token = in.peek();
                config.setMinRam(in.nextInt());
            }
            if ("maxRam".equals(fieldName)) {
                token = in.peek();
                config.setMaxRam(in.nextInt());
            }
        }
        in.endObject();
        return config;
    }
}
