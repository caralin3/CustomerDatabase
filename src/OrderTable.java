import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class OrderTable {
    private static int index;
    private static int i;
    public static void createOrderTable(Connection conn) {
        try {
            Statement stmt;
            String query = "CREATE TABLE IF NOT EXISTS orders("
                    + "orderId INT,"
                    + "foodItem VARCHAR(100),"
                    + "PRIMARY KEY (orderId));";
            stmt = conn.createStatement();
            stmt.execute(query);
            index = 1;
            i = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectOrder(Connection conn, Orders orders) {
        int orderId = orders.getOrderId();
        String foodItem = orders.getFoodItem();

        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM order";
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                int _orderId = res.getInt(orderId);
                String _foodItem = res.getString(foodItem);

                System.out.print("Id: " + _orderId);;
                System.out.println("Order: " + _foodItem);
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertOrder(Connection conn, String foodItem) {
        try {
            Statement insertOrder = conn.createStatement();
            insertOrder.execute("" +
                            "INSERT INTO order"
                            + "VALUES (" + index
                            + ","
                            + foodItem
                            + ");"
            );
            index++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteOrder(Connection conn, Orders orders) {
        try {
            System.out.println("Creating delete statement...");
            Statement deleteOrder = conn.createStatement();
            deleteOrder.execute("" +
                    "DELETE FROM order WHERE orderId = " + i);
            index--;
            i++;

            selectOrder(conn, orders);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
