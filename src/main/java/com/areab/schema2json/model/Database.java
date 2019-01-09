package com.areab.schema2json.model;

import java.util.List;

public class Database {
    public String name;
    public String schema;
    public List<Table> tables;

    @Override
    public String toString() {
        return "Database{" +
                "name='" + name + '\'' +
                ", schema='" + schema + '\'' +
                ", tables=" + tables +
                '}';
    }
}
