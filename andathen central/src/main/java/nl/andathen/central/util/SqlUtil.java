package nl.andathen.central.util;

/**
 * Helper class for generating SQL query strings.
 */
public class SqlUtil{

    /**
     * Method for generating a select query without condition
     * @param columns Which columns to select from e.g., "id,name" or "*"
     * @param table Which table to select from e.g., "Comics"
     * @return Query string
     * @author Can Karabey
     */
    public static String select(String columns, String table){
        String query = String.format("SELECT %s FROM %s;" , columns, table );
        return query;
    }

    /**
     * Method for generating a select query with condition
     * @param columns Which columns to select from e.g., "id,name" or "*"
     * @param table Which table to select from e.g., "Comics"
     * @param condition Define condition e.g., "id==10"
     * @return Query string
     * @author Can Karabey
     */
    public static String select(String columns, String table, String condition){
        String query = String.format("SELECT %s FROM %s WHERE %s;" , columns, table, condition );
        return query;
    }

    /**
     * Method for generating insert query without specifying columns
     * @param table Which table to insert to e.g., "Comics"
     * @param values Which values to insert e.g., "1,Berlin,2022"
     * @return Query string
     * @author Can Karabey
     */
    public static String insert(String table, String values){
        String query = String.format("INSERT INTO %s VALUES(%s);", table,values);
        return query;
    }

    /**
     * Method for generating insert query while specifying columns
     * @param table Which table to insert to e.g., "Comics"
     * @param columns Which columns to insert e.g., "Id,Name"
     * @param values Which values to insert e.g., "1,Berlin,2022"
     * @return Query string
     * @author Can Karabey
     */
    public static String insert(String table, String columns, String values){
        String query = String.format("INSERT INTO %s(%s) VALUES(%s);", table,columns,values);
        return query;
    }

    /**
     * Method for generating delete query
     * @param table From which table to delete from e.g., "Comics"
     * @param condition Which conditions to delete e.g., "Id==1"
     * @return Query string
     * @author Can Karabey
     */
    public static String delete(String table, String condition){
        String query = String.format("DELETE FROM %s WHERE %s;",table,condition);
        return query;
    }

    /**
     * Method for generating update query string
     * @param table From which table to update e.g., "Comics"
     * @param columns_values New values for columns e.g., "Id=1,Name=Can"
     * @param condition Condition for updating e.g., "Id=0"
     * @return Query string
     * @author Can Karabey
     */
    public static String update(String table, String columns_values, String condition){
        String query = String.format("UPDATE %s SET %s WHERE %s;", table,columns_values,condition);
        return query;
    }

    /**
     * Method for generating count query
     * @param column Which column to count e.g., "Name" or "*"
     * @param table From which table to count e.g., "Comics"
     * @return Query string
     * @author Can Karabey
     */
    public static String count(String column , String table){
        String query = String.format("SELECT COUNT(%s) FROM %s;", column,table);
        return query;
    }

    /**
     * Method for generating avg query string
     * @param column From which column to calculate the avg from e.g., "Age"
     * @param table Which table to use e.g., "Comics"
     * @return Query string
     * @author Can Karabey
     */
    public static String avg(String column , String table){
        String query = String.format("SELECT AVG(%s) FROM %s;", column,table);
        return query;
    }

    /**
     * Method for generating max query
     * @param column From which column to calculate the max from e.g., "Age"
     * @param table Which table to use e.g., "Comics"
     * @return Query string
     * @author Can Karabey
     */
    public static String max(String column , String table){
        String query = String.format("SELECT MAX(%s) FROM %s;", column,table);
        return query;
    }

    /**
     * Method for generating max query
     * @param column From which column to calculate the min from e.g., "Age"
     * @param table Which table to use e.g., "Comics"
     * @return Query string
     * @author Can Karabey
     */
    public static String min(String column , String table){
        String query = String.format("SELECT MIN(%s) FROM %s;", column,table);
        return query;
    }
}