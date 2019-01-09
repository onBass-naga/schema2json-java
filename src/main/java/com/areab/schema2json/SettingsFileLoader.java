package com.areab.schema2json;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class SettingsFileLoader {

    public static Settings load(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Map<String, Object> map = new Yaml().load(new FileReader(file));
        return initSettings(map);
    }

    private static Settings initSettings(Map<String, Object> map) {
        Settings dest = new Settings();
        dest.setOutputDirectory(map.get("outputDirectory").toString());

        @SuppressWarnings("unchecked")
        Map<String, String> datasource = (Map<String, String>) map.get("datasource");
        dest.setDriverClassName(datasource.get("driverClassName"));
        dest.setUrl(datasource.get("url"));
        dest.setSchema(datasource.get("schema"));
        dest.setUsername(datasource.get("username"));
        dest.setPassword(datasource.get("password"));

        return dest;
    }


}
