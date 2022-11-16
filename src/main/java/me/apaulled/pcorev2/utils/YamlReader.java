package me.apaulled.pcorev2.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;

public class YamlReader {
    private HashMap<String, Object> yamlMap;

    public YamlReader(String fileName) {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        this.yamlMap = yaml.load(inputStream);
    }

    public HashMap<String, Object> getYamlMap() {
        return this.yamlMap;
    }

    public Object getValue(String key) {
        return this.yamlMap.get(key);
    }
}
