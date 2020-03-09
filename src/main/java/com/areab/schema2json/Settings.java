package com.areab.schema2json;

import java.io.File;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Settings {
    private String driverClassName;
    private String url;
    private String schema;
    private String username;
    private String password;
    private String outputDirectory;

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public File getOutputDirectory() {
        return new File(outputDirectory);
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public String getSchema() {
        return schema;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabaseName() {
        String regex = ".*[/:]([^?;]+)[?;]?(.*$)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(url);
        if (m.find()) {
            return m.group(1);
        } else {
            throw new IllegalArgumentException(String.format("cannot find database name from url. [ %s ]", url));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Settings settings = (Settings) o;
        return driverClassName.equals(settings.driverClassName) &&
                url.equals(settings.url) &&
                Objects.equals(schema, settings.schema) &&
                username.equals(settings.username) &&
                Objects.equals(password, settings.password) &&
                outputDirectory.equals(settings.outputDirectory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverClassName, url, schema, username, password, outputDirectory);
    }

    @Override
    public String toString() {
        return "Settings{" +
                "driverClassName='" + driverClassName + '\'' +
                ", url='" + url + '\'' +
                ", schema='" + schema + '\'' +
                ", username='" + username + '\'' +
                ", password='" + mask(password) + '\'' +
                ", outputDirectory='" + outputDirectory + '\'' +
                '}';
    }

    private String mask(String rawPassword) {
        return Objects.isNull(rawPassword) ? ""
                : rawPassword.replaceAll(".", "*");
    }
}
