import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class CustomerTable {
    private static int index;
    private static int i;

    public static void createCustomerTable(Connection conn) {
        try {
            Statement stmt;
            String query = "CREATE TABLE IF NOT EXISTS customer("
                    + "customerId INT,"
                    + "firstName VARCHAR(100),"
                    + "lastName VARCHAR(100),"
                    + "PRIMARY KEY (customerId),"
                    + "FOREIGN KEY (foodItem) REFERENCES orders(foodItem)"
                    + ");";
            stmt = conn.createStatement();
            stmt.execute(query);
            index = 1;
            i = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectCustomer(Connection conn, Customer customer) {
        int customerId = customer.getCustomerId();
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();
        String foodItem = customer.getOrders().getFoodItem();

        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM customer";
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                int _customerId = res.getInt(customerId);
                String _firstName = res.getString(firstName);
                String _lastName = res.getString(lastName);
                String _foodItem = res.getString(foodItem);

                System.out.print("Id: " + _customerId);
                System.out.print("First Name: " + _firstName);
                System.out.print("Last Name: " + _lastName);
                System.out.println("Order: " + _foodItem);
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean selectCustomerName(Connection conn, Customer customer) {
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();
        String tempFirst = firstName;
        String tempLast = lastName;
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT firstName, lastName FROM customer";
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                res.getString(firstName);
                res.getString(lastName);
                if(tempFirst.equals(res.getString(firstName)) && tempLast.equals(res.getString(lastName)))
                {
                    return true;
                }
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void insertCustomer(Connection conn, String _firstName, String _lastName, Orders _orders) {
        try {
            Statement insertCustomer = conn.createStatement();
            insertCustomer.execute("" +
                            "INSERT INTO customer"
                            + "VALUES (" + index
                            + ","
                            + _firstName
                            +","
                            + _lastName
                            + ","
                            + _orders.getFoodItem()
                            + ");"
            );
            index++;
            System.out.println("Order has been placed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomer(Connection conn, Customer customer) {
        try {
            System.out.println("Creating delete statement...");
            Statement stmt = conn.createStatement();
            stmt.execute("" +
                    "DELETE FROM customer WHERE customerId = " + i);
            index--;
            i++;

            selectCustomer(conn, customer);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
