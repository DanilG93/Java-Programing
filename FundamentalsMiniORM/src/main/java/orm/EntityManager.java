package orm;

import lombok.AllArgsConstructor;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
public class EntityManager<E> implements DbContext<E> {

    private static final String queryInsert = """
            INSERT INTO %s %s VALUES %s
            """;

    private static final String queryUpdate = """
            UPDATE %s
            SET %s
            WHERE id = %s
            """;

    private static final String queryCreate = """
            CREATE TABLE IF NOT EXISTS %s (
                %s
            );
            """;

    private static final String queryDelete = """
            DELETE FROM %s
            WHERE %s
            """;
    private static final String queryAllFieldFromTable = "SELECT `COLUMN_NAME` FROM `INFORMATION_SCHEMA`.`COLUMNS` " +
            "WHERE `TABLE_SCHEMA`='test' AND `COLUMN_NAME` != 'id' " +
            " AND `TABLE_NAME`='users';";

    private static final String queryAlter = """
                ALTER TABLE %s%s;
            """;


    private static final String queryFind = "SELECT * FROM %s %s ";
    private static final String noAnnotation = "No annotation 'Entity' place add...";
    private static final String noPrimaryKay = "Entity does not have primary key";


    private Connection connection;


    @Override
    public void persist(E entity) throws IllegalAccessException, SQLException {
        Field primaryKey = getId(entity.getClass());
        primaryKey.setAccessible(true);
        Object value = primaryKey.get(entity);

        if (value == null || (long) value <= 0) {
            doInsert(entity, primaryKey);
            return;
        }

        duUpdate(entity, primaryKey);
    }

    private void doInsert(E entity, Field primary) throws IllegalAccessException, SQLException {

        String tableName = this.getTableName(entity.getClass());

// I go through all the entity fields and take concrete values to do this...(..., ..., ...) for INSERT
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        Id idField = primary.getAnnotation(Id.class);
        for (Field field : entity.getClass().getDeclaredFields()) {
            Column annotation = field.getAnnotation(Column.class);
            if (annotation == null) {
                continue;
            }
            String name = annotation.name();
            sb.append(name).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        String nameColumnForInsert = sb.toString();

        sb = new StringBuilder();
        sb.append("(");

        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object o = field.get(entity);

            if (idField != null) {
                continue;
            }

            if (o instanceof String || o instanceof LocalDate) {
                sb.append("'").append(o).append("'").append(",");
            } else {
                sb.append(o).append(",");
            }


        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        String valueOfColumnForInsert = sb.toString();

        String queryForInsert = String.format(queryInsert, tableName, nameColumnForInsert, valueOfColumnForInsert);


        Statement statement = connection.createStatement();
        statement.executeUpdate(queryForInsert);

    }

    private void duUpdate(E entity, Field primary) throws SQLException, IllegalAccessException {

        Long id = (Long) primary.get(entity);
        String tableName = this.getTableName(entity.getClass());
        StringBuilder sb = new StringBuilder();

        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.getAnnotation(Id.class) != null) continue;

            field.setAccessible(true);
            Object o = field.get(entity);
            Column annotation = field.getAnnotation(Column.class);
            if (o instanceof String || o instanceof LocalDate) {
                sb.append(annotation.name()).append("= '").append(o).append("',");
            } else {
                sb.append(annotation.name()).append("= ").append(o).append(",");
            }

        }
        sb.deleteCharAt(sb.length() - 1);
        String setNameValuesOfColumnForUpdate = sb.toString();
        String queryForUpdate = String.format(queryUpdate, tableName, setNameValuesOfColumnForUpdate, id);


        connection.prepareStatement(queryForUpdate).execute();
    }

    @Override
    public Iterator<E> find(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return find(table, null);
    }

    @Override
    public Iterator<E> find(Class<E> table, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Statement statement = connection.createStatement();
        String tableName = getTableName(table);

        String query = String.format(queryFind, tableName, where != null ? " WHERE " + where : "");

        ResultSet resultSet = statement.executeQuery(query);


        List<E> entities = new ArrayList<>();
        while (resultSet.next()) {
            E entity = table.getDeclaredConstructor().newInstance();
            fillEntity(table, resultSet, entity);
            entities.add(entity);
        }


        return entities.iterator();
    }

    @Override
    public E findFirst(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return findFirst(table, null);
    }

    @Override
    public E findFirst(Class<E> table, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Statement statement = connection.createStatement();
        String tableName = getTableName(table);

        String query = String.format(queryFind + "LIMIT 1;", tableName, where != null ? " WHERE " + where : "");

        ResultSet resultSet = statement.executeQuery(query);
        E entity = table.getDeclaredConstructor().newInstance();

        resultSet.next();

        fillEntity(table, resultSet, entity);

        return entity;
    }

    private void fillEntity(Class<E> table, ResultSet resultSet, E entity) throws SQLException, IllegalAccessException {
        Field[] declaredFields = Arrays.stream(table.getDeclaredFields()).toArray(Field[]::new);
        for (Field field : declaredFields) {
            field.setAccessible(true);
            fillField(field, resultSet, entity);
        }
    }

    private void fillField(Field field, ResultSet resultSet, E entity) throws SQLException, IllegalAccessException {
        field.setAccessible(true);
        if (field.getType() == int.class || field.getType() == long.class) {
            field.set(entity, resultSet.getInt(field.getName()));
        } else if (field.getType() == LocalDate.class) {
            field.set(entity, LocalDate.parse(resultSet.getString(field.getName())));
        } else {
            field.set(entity, resultSet.getString(field.getName()));
        }
    }


    private String getTableName(Class<?> aClass) {
        if (aClass.getAnnotation(Entity.class) == null) {
            System.out.println(noAnnotation);
            return null;
        }

        String name = aClass.getAnnotation(Entity.class).name();
        if (Objects.equals(name, "")) {
            name = aClass.getSimpleName().toLowerCase();
        }

        return name;
    }

    @Override
    public void doCreate(Class<E> entityClass) throws SQLException {
        String tableName = getTableName(entityClass);
        StringBuilder sb = new StringBuilder();

        Field[] declaredFields = entityClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getAnnotation(Id.class) != null) {
                sb.append("id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,");
                continue;
            }

            sb.append(getNameAndDateTypeOfField(field)).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        String columnDefinition = sb.toString();

        String createTableQuery = String.format(queryCreate, tableName, columnDefinition);
        Statement statement = connection.createStatement();
        statement.executeUpdate(createTableQuery);
    }

    @Override
    public void delete(E entity, String where) throws SQLException {
        String tableName = getTableName(entity.getClass());
        String deleteQuery = String.format(queryDelete, tableName, where);
        Statement statement = connection.createStatement();
        statement.executeUpdate(deleteQuery);
    }

    private Set<String> getAllFieldsFromTable() throws SQLException {
        Set<String> allFields = new HashSet<>();
        PreparedStatement preparedStatement = connection.prepareStatement(queryAllFieldFromTable);
        ResultSet allFieldsFromTable = preparedStatement.executeQuery();
        while (allFieldsFromTable.next()) {
            allFields.add(allFieldsFromTable.getString(1));
        }

        return allFields;
    }

    @Override
    public void doAlter(E entity) throws SQLException {
        String tableName = getTableName(entity.getClass());
        String alterQuery = String.format(queryAlter, tableName, getNewFields(entity.getClass()));
        Statement statement = connection.createStatement();
        statement.executeUpdate(alterQuery);
    }

    private String getNewFields(Class<?> entityClass) throws SQLException {
        StringBuilder result = new StringBuilder();
        Set<String> fields = getAllFieldsFromTable();
        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                String fieldName = field.getName();
                if (!fields.contains(fieldName)) {
                    result.append(" ADD COLUMN ").append(getNameAndDateTypeOfField(field)).append(",");
                }
            }
        }
        if (!result.isEmpty()) {
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }

    private String getNameAndDateTypeOfField(Field field) {
        String nameField = field.getName();
        String nameType;
        if (field.getType() == int.class || field.getType() == Integer.class) {
            nameType = " INT";
        } else if (field.getType() == LocalDate.class) {
            nameType = " DATE";
        } else {
            nameType = " VARCHAR(255)";
        }

        return nameField + nameType;
    }


    public Field getId(Class<?> entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .filter(x -> x.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException(noPrimaryKay));
    }
}
