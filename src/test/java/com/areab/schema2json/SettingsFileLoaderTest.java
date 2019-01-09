package com.areab.schema2json;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SettingsFileLoaderTest {

    @Test
    public void load() throws FileNotFoundException {
        String filePath = "./src/test/resources/settings.yaml";
        Settings actual = SettingsFileLoader.load(filePath);

        assertThat(actual.getOutputDirectory().getPath(), is("./.dist"));
    }
}