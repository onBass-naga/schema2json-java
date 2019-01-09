package com.areab.schema2json;

import com.areab.schema2json.model.Database;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonWriter {

    public static void write(File outputDirectory, Database database) {
        try (StringWriter writer = new StringWriter()) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            mapper.writeValue(writer, database);

            Path destDir = outputDirectory.toPath();
            if (!Files.exists(destDir)) {
                Files.createDirectories(destDir);
            }

            File file = new File(destDir.toFile(), String.format("%s.json", database.name));

            try (FileWriter filewriter = new FileWriter(file)) {
                filewriter.write(writer.toString());
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
