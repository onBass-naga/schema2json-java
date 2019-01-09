package com.areab.schema2json;

import com.areab.schema2json.model.Column;
import com.areab.schema2json.model.DataTypes;
import com.areab.schema2json.model.Database;
import com.areab.schema2json.model.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SchemaMetaFactory {

    static List<String> getTableNames(Connection connection) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        String catalog = null;
        ResultSet result = meta.getTables(catalog, connection.getSchema(), "%", new String[]{"TABLE"});

        List<String> tableNames = new ArrayList<>();
        while (result.next()) {
            tableNames.add(result.getString("TABLE_NAME"));
        }
        return tableNames;
    }

    static List<Column> getColumns(Connection connection, String tableName) throws SQLException {

        DatabaseMetaData meta = connection.getMetaData();
        String catalog = null;
        ResultSet result = meta.getColumns(catalog, connection.getSchema(), tableName, "%");

        List<Column> columns = new ArrayList<>();
        while (result.next()) {
            String name = result.getString("COLUMN_NAME");
            int dataType = result.getInt("DATA_TYPE");
            boolean isNullable = Arrays.asList("YES", "Y").contains(result.getString("IS_NULLABLE"));

            columns.add(new Column(name, DataTypes.of(dataType).getTypeName(), isNullable));
        }
        return columns;
    }

    static List<String> getPrimaryKeys(Connection connection, String tableName) throws SQLException {

        DatabaseMetaData meta = connection.getMetaData();
        String catalog = null;
        ResultSet result = meta.getPrimaryKeys(catalog, connection.getSchema(), tableName);

        return getKeys(result, "COLUMN_NAME");
    }

    static List<String> getForeignKeys(Connection connection, String tableName) throws SQLException {

        DatabaseMetaData meta = connection.getMetaData();
        String catalog = null;
        ResultSet result = meta.getImportedKeys(catalog, connection.getSchema(), tableName);

        return getKeys(result, "FKTABLE_NAME", "FKCOLUMN_NAME");
    }

    private static List<String> getKeys(ResultSet result, String... labels) throws SQLException {
        List<String> keys = new ArrayList<>();
        while (result.next()) {
            String key = Arrays.stream(labels).map(label -> {
                try {
                    return result.getString(label);
                } catch (SQLException ex) {
                    return "";
                }
            }).collect(Collectors.joining("."));

            keys.add(key);
        }
        return keys;
    }

    public static Database create(Settings settings) {

        try (Connection connection = initConnection(settings)) {
            List<String> tableNames = getTableNames(connection);
            List<Table> tables = tableNames.stream()
                    .map(tableName -> {
                        Table table = new Table();
                        try {
                            table.name = tableName;
                            table.columns = getColumns(connection, tableName);
                            table.primaryKeys = getPrimaryKeys(connection, tableName);
                            table.foreignKeys = getForeignKeys(connection, tableName);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return table;
                    })
                    .collect(Collectors.toList());

            Database db = new Database();
            db.name = settings.getDatabaseName();
            db.schema = connection.getSchema();
            db.tables = tables;
            return db;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static Connection initConnection(Settings settings) throws ClassNotFoundException, SQLException {
        String driver = settings.getDriverClassName();
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(settings.getUrl(), settings.getUsername(), settings.getPassword());

        if (driver.contains("postgres")) {
            String schema = settings.getSchema();
            String schemaName = schema == null ? "public" : schema;
            conn.setSchema(schemaName);
        }

        return conn;
    }

}
