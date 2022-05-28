package ru.job4j.io;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() throws IllegalArgumentException {

        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String line = null;
            while ((line = read.readLine()) != null) {
                if (line.startsWith("=") && StringUtils.countMatches(line, "=") > 1) {
                    throw new IllegalArgumentException();
                }
                String[] s = line.split("=");
                String add = s.length <= 1
                        ? values.put(s[0], null)
                        : values.put(s[0], s[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (key == null) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}