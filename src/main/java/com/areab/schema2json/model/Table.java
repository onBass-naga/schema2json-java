package com.areab.schema2json.model;

import java.util.List;

public class Table {
    public String name;
    public List<Column> columns;
    public List<String> primaryKeys;
    public List<String> foreignKeys;

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", columns=" + columns +
                ", primaryKeys=" + primaryKeys +
                '}';
    }
}
