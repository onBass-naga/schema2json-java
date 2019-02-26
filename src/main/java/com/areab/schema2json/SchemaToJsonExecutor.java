package com.areab.schema2json;

import com.areab.schema2json.model.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;

public class SchemaToJsonExecutor {

    final private static Logger logger = LoggerFactory.getLogger(SchemaToJsonExecutor.class);

    public void execute(String settingFilePath) throws FileNotFoundException {
        Settings settings = SettingsFileLoader.load(settingFilePath);
        execute(settings);
    }

    public void execute(Settings settings) {
        Database database = SchemaMetaFactory.create(settings);

        if (logger.isDebugEnabled()) {
            logger.debug("database: " + database);
        }

        JsonWriter.write(settings.getOutputDirectory(), database);
    }
}
