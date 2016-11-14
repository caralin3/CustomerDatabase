import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * file: OrdersTable.java
 */
public class OrdersTable {

    public static void createOrdersTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS orders("
                + "orderId int,"
                + "foodItem VARCHAR(100),"
                + "PRIMARY KEY (orderId)"
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
     * @param foodItem
     */
    public static void addOrder(Connection conn,int id, String foodItem){

        // SQL insert statement
        String query = String.format("INSERT INTO orders "
                + "VALUES(%d,\'%s\');",id, foodItem);
        try {
            // Create and execute the query
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteOrder(Connection conn, int customerId){
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

    public static void deleteAllOrders(Connection conn){

        // SQL insert statement
        String query = String.format("DELETE FROM orders");
        try {
            // Create and execute the query
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Queries and print the table
     * @param conn
     */
    public static void printOrdersTable(Connection conn){
        String query = "SELECT * FROM orders;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()){
                System.out.printf("Order %d: %s\n",
                        result.getInt(1),
                        result.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
