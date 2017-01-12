import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * file: CustomerTable.java
 */
public class CustomerTable {

    public static void createCustomerTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS customer("
                + "customerId INT,"
                + "firstName VARCHAR(100),"
                + "lastName VARCHAR(100),"
                + "foodItem VARCHAR(100),"
                + "PRIMARY KEY (customerId),"
                + "FOREIGN KEY (foodItem) REFERENCES orders(foodItem)"
                + ");";

        Statement stmt;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Adds a single person to the database
     * @param conn
     * @param id
     * @param fName
     * @param lName
     * @param foodItem
     */
    public static void addCustomer(Connection conn, int id, String fName, String lName, String foodItem){

        // SQL insert statement
        String query = String.format("INSERT INTO customer "
                + "VALUES(%d,\'%s\',\'%s\',\'%s\');", id, fName, lName, foodItem);
        try {
            // Create and execute the query
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAllCustomer(Connection conn){

        // SQL insert statement
        String query = String.format("DELETE FROM customer");
        try {
            // Create and execute the query
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomer(Connection conn, int customerId){
        // SQL insert statement
        String query = String.format("DELETE FROM customer WHERE customerId = " + customerId);
        try {
            // Create and execute the query
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Makes a query to the person table
     * with given columns and conditions
     *
     * @param conn
     * @param columns: columns to return
     * @param whereClauses: conditions to limit query by
     * @return
     */
    public static ResultSet queryCustomerTable(Connection conn,
                                               ArrayList<String> columns,
                                               ArrayList<String> whereClauses){
        StringBuilder sb = new StringBuilder();

        /**
         * Start the select query
         */
        sb.append("SELECT ");

        /**
         * If we gave no columns just give them all to us
         *
         * other wise add the columns to the query
         * adding a comma top seperate
         */
        if(columns.isEmpty()){
            sb.append("* ");
        }
        else{
            for(int i = 0; i < columns.size(); i++){
                if(i != columns.size() - 1){
                    sb.append(columns.get(i) + ", ");
                }
                else{
                    sb.append(columns.get(i) + " ");
                }
            }
        }

        /**
         * Tells it which table to get the data from
         */
        sb.append("FROM customer ");

        /**
         * If we gave it conditions append them
         * place an AND between them
         */
        if(!whereClauses.isEmpty()){
            sb.append("WHERE ");
            for(int i = 0; i < whereClauses.size(); i++){
                if(i != whereClauses.size() -1){
                    sb.append(whereClauses.get(i) + " AND ");
                }
                else{
                    sb.append(whereClauses.get(i));
                }
            }
        }

        /**
         * close with semi-colon
         */
        sb.append(";");

        //Print it out to verify it made it right
        System.out.println("Query: " + sb.toString());
        try {
            /**
             * Execute the query and return the result set
             */
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Queries and print the table
     * @param conn
     */
    public static void printCustomerTable(Connection conn){
        String query = "SELECT * FROM customer;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()){
                System.out.printf("Customer %d: %s %s\n",
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
