package com.areab.schema2json.model;

public class Column {
    public String columnName;
    public String dataType;
    public boolean isNullable;

    public Column(String columnName, String dataType, boolean isNullable) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.isNullable = isNullable;
    }

    @Override
    public String toString() {
        return "Column{" +
                "columnName='" + columnName + '\'' +
                ", dataType='" + dataType + '\'' +
                ", isNullable=" + isNullable +
                '}';
    }
}
