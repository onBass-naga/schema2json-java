# Schema2Json

Schema2Json is a JSON generator. It extract the metadata from a RDB.(PostgreSQL/MySQL)   

#### Output Example

```json
{
  "name": "test_db",
  "schema": "public",
  "tables": [
    {
      "name": "users",
      "columns": [
        {
          "columnName": "id",
          "dataType": "BIGINT",
          "isNullable": false
        },
        {
          "columnName": "name",
          "dataType": "VARCHAR",
          "isNullable": false
        }
      ],
      "primaryKeys": ["id"],
      "foreignKeys": []
    },
    {
      "name": "issues",
      "columns": [
        {
          "columnName": "id",
          "dataType": "BIGINT",
          "isNullable": false
        },
        {
          "columnName": "user_id",
          "dataType": "BIGINT",
          "isNullable": false
        },
        {
          "columnName": "description",
          "dataType": "VARCHAR",
          "isNullable": false
        }
      ],
      "primaryKeys": ["id"],
      "foreignKeys": ["users.id:issues.user_id"]
    }
  ]
}
```

## Usage (Standalone jar)

### 1. JDBC Driver

Download a JDBC driver.  

### 2. Build 

```
$ ./gradlew build
```

### 3. Connection settings

Write your connection info to `settings.yaml`.  

### 4. Run java -jar 

Add JDBC Driver to classpath and a file path for settings.yaml to jar option.

ex) PostgreSQL

```
$ java -Xbootclasspath/a:../schema2json/libs/postgresql-9.4.1212.jar -jar build/libs/schema2json-1.0-SNAPSHOT.jar ./settings.yaml
```

ex) MySQL

```
$ java -Xbootclasspath/a:../schema2json/libs/mysql-connector-java-5.1.46.jar -jar build/libs/schema2json-1.0-SNAPSHOT.jar ./settings.yaml
```

