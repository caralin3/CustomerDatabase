import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class ServedTable {
    private static int index;

    public static void createServedTable(Connection conn) {
        try {
            Statement stmt;
            String query = "CREATE TABLE IF NOT EXISTS served("
                    + "servedId INT,"
                    + "PRIMARY KEY (servedId),"
                    + "FOREIGN KEY(customerId) REFERENCES customer(customerId)"
                    + ");";
            stmt = conn.createStatement();
            stmt.execute(query);
            index = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void selectServed(Connection conn, Customer customer) {
        int servedId = customer.getCustomerId();
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();
        String foodItem = customer.getOrders().getFoodItem();

        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM served";
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                int _servedId = res.getInt(servedId);
                String _firstName = res.getString(firstName);
                String _lastName = res.getString(lastName);
                String _foodItem = res.getString(foodItem);

                System.out.print("Id: " + _servedId);
                System.out.print("First Name: " + _firstName);
                System.out.print("Last Name: " + _lastName);
                System.out.println("Order: " + _foodItem);
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertServed(Connection conn, Customer customer) {
        try {
            Statement insertCustomer = conn.createStatement();
            insertCustomer.execute("" +
                            "INSERT INTO served(servedId, customer) "
                            + "VALUES (" + index
                            + ","
                            + customer.getCustomerId()
                            + ");"
            );
            index++;
            selectServed(conn, customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
