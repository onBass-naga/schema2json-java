package com.areab.schema2json;

import com.areab.schema2json.model.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;

public class Main {

    final private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws FileNotFoundException {
        logger.info("args: " + String.join(", ", args));

        if (args.length == 0) {
            throw new IllegalArgumentException("Path to setting.yaml is required.");
        }

        String filePath = args[0];
        Settings settings = SettingsFileLoader.load(filePath);
        Database database = SchemaMetaFactory.create(settings);

        if (logger.isDebugEnabled()) { logger.debug("database: " + database); }

        JsonWriter.write(settings.getOutputDirectory(), database);
    }
}
