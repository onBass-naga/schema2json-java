package com.areab.schema2json.model;

import java.sql.Types;
import java.util.Arrays;

public enum DataTypes {
    ARRAY(Types.ARRAY, "ARRAY"),
    BIGINT(Types.BIGINT, "BIGINT"),
    BINARY(Types.BINARY, "BINARY"),
    BIT(Types.BIT, "BIT"),
    BLOB(Types.BLOB, "BLOB"),
    BOOLEAN(Types.BOOLEAN, "BOOLEAN"),
    CHAR(Types.CHAR, "CHAR"),
    CLOB(Types.CLOB, "CLOB"),
    DATALINK(Types.DATALINK, "DATALINK"),
    DATE(Types.DATE, "DATE"),
    DECIMAL(Types.DECIMAL, "DECIMAL"),
    DISTINCT(Types.DISTINCT, "DISTINCT"),
    DOUBLE(Types.DOUBLE, "DOUBLE"),
    FLOAT(Types.FLOAT, "FLOAT"),
    INTEGER(Types.INTEGER, "INTEGER"),
    JAVA_OBJECT(Types.JAVA_OBJECT, "JAVA_OBJECT"),
    LONGVARBINARY(Types.LONGVARBINARY, "LONGVARBINARY"),
    LONGVARCHAR(Types.LONGVARCHAR, "LONGVARCHAR"),
    LONGNVARCHAR(Types.LONGNVARCHAR, "LONGNVARCHAR"),
    NCHAR(Types.NCHAR, "NCHAR"),
    NCLOB(Types.NCLOB, "NCLOB"),
    NULL(Types.NULL, "NULL"),
    NUMERIC(Types.NUMERIC, "NUMERIC"),
    NVARCHAR(Types.NVARCHAR, "NVARCHAR"),
    OTHER(Types.OTHER, "OTHER"),
    REAL(Types.REAL, "REAL"),
    REF(Types.REF, "REF"),
    ROWID(Types.ROWID, "ROWID"),
    REF_CURSOR(Types.REF_CURSOR, "REF_CURSOR"),
    SMALLINT(Types.SMALLINT, "SMALLINT"),
    STRUCT(Types.STRUCT, "STRUCT"),
    SQLXML(Types.SQLXML, "SQLXML"),
    TIME(Types.TIME, "TIME"),
    TIME_WITH_TIMEZONE(Types.TIME_WITH_TIMEZONE, "TIME_WITH_TIMEZONE"),
    TIMESTAMP(Types.TIMESTAMP, "TIMESTAMP"),
    TIMESTAMP_WITH_TIMEZONE(Types.TIMESTAMP_WITH_TIMEZONE, "TIMESTAMP_WITH_TIMEZONE"),
    TINYINT(Types.TINYINT, "TINYINT"),
    VARBINARY(Types.VARBINARY, "VARBINARY"),
    VARCHAR(Types.VARCHAR, "VARCHAR"),

    UNKNOWN(null, "");

    private Integer types;
    private String typeName;

    DataTypes(Integer types, String typeName) {
        this.types = types;
        this.typeName = typeName;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public static DataTypes of(int code) {
        return Arrays.stream(values())
                .filter(it -> it.types == code)
                .findAny()
                .orElse(UNKNOWN);
    }

    public static DataTypes of(String typeName) {
        return Arrays.stream(values())
                .filter(it -> it.typeName.equals(typeName))
                .findAny()
                .orElse(UNKNOWN);
    }

}
