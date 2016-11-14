import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 */
public class DatabaseHandler {
    //The connection to the database
    private Connection conn;

    /**
     * Create a database connection with the given params
     * @param user: user name for the owner of the database
     * @param password: password of the database owner
     */
    public void createConnection(String user, String password){
        try {

            //This needs to be on the front of your location
            String url = "jdbc:h2:~/SQL/_db";

            //This tells it to use the h2 driver
            // Registers driver
            Class.forName("org.h2.Driver");

            // Creates the connection
            conn = DriverManager.getConnection(url, user, password);

        } catch (SQLException | ClassNotFoundException e) {
            //You should handle this better
            e.printStackTrace();
        }
    }


    /**
     * just returns the connection
     * @return returns class level connection
     */
    public Connection getConnection(){
        return conn;
    }

    /**
     * When your database program exits
     * you should close the connection
     */
    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        DatabaseHandler db = new DatabaseHandler();

        // Database credentials
        String USER = "username";
        String PASSWORD = "password";

        // Open a connection
        System.out.println("Connecting to database...");
        db.createConnection(USER, PASSWORD);

        String[] firstNames = new String[] {"Abigail", "Abraham", "Aidan", "Alex", "Amy", "Andrea", "Andrew", "Ashley",
                "Barbra", "Bianca", "Billy", "Blake", "Bob", "Bonnie", "Brandon", "Brenda", "Britney", "Camille",
                "Candice", "Carl", "Caroline", "Catherine", "Charles", "Chloe", "Chris", "Claire", "Clarence", "Cody",
                "Cory", "Courtney", "Danny", "Ellie", "Emily", "Emma", "Frank", "George", "Gordon", "Grace", "Haley",
                "Hannah", "Hillary", "Isabelle", "Jackson", "James", "Jason", "Jennifer", "Jessica", "Jimmy", "Joe",
                "John", "John", "Julia", "Kate", "Kelly", "Kimberly", "Lauren", "Lily", "Lincoln", "Lisa", "Lucy",
                "Luke", "Luther", "Mary", "Max", "Michael", "Michelle", "Miley", "Monica", "Morton", "Ned", "Nick",
                "Oliver", "Oscar", "Patrick", "Percy", "Phoebe", "Rachel", "Rebecca", "Richard", "Rick", "Roxanne",
                "Ryan", "Sam", "Sarah", "Sheen", "Shelby", "Simon", "Sophie", "Spencer", "Susie", "Tanya", "Tessa",
                "Thomas", "Tim", "Toby", "Todd", "Victoria", "Willow", "Xavier", "Zeke"};

        String[] lastNames = new String[] {"Abbot", "Adams", "Allan", "Andrews","Arthur", "Baez", "Baldwin", "Bell",
                "Black", "Brown", "Bryant", "Burke", "Cameron", "Campbell", "Carpenter", "Carson",
                "Cassidy", "Chambers", "Chan", "Clifford", "Coleman", "Cook", "Cullen", "Davis", "Edwards",
                "Evans", "Finster", "Fisher", "Fitzgerald", "Fitzpatrick", "Gabriel", "Geller", "Gilbert",
                "Godwin", "Goldberg", "Gonzales", "Goodman", "Graham", "Green", "Griffin", "Harper", "Harrison",
                "Hart", "Hathaway", "Holt", "Jefferson", "Johnson", "Jones", "Kahn", "Kennedy", "King", "Krieger",
                "Lambert", "Lawrence", "Lee", "Lewis", "Lloyd", "Long", "Lopez", "Martin", "Mason", "Matthews",
                "McDonald", "McGuire", "Mendoza", "Miller", "Mitchell", "Moore", "Neutron", "Nichols", "O'Neal",
                "O'Reilly", "Parker", "Perez", "Perry", "Peterson", "Potter", "Roberts", "Robertson", "Robinson",
                "Rodriguez", "Sanchez", "Singer", "Singh", "Smith", "Swan", "Taylor", "Thompson", "Turner",
                "Underwood", "Vasquez", "Waldorf", "Wayne", "Wesley", "White", "Williams", "Wilson", "Wolfe",
                "Wong", "Wright"};

        String[] foodItems = new String[] {"Apple", "Bagel", "Banana", "Beef Stew", "Brownie", "Burger", "Cake",
                "Calzone", "Cereal", "Chicken", "Chili", "Chocolate", "Coffee", "Cookie", "Croissant", "Cupcake",
                "Doughnut", "Fish", "Fries", "Hot Chocolate", "Ice Cream", "Lasagna", "Macaroni and Cheese",
                "Milkshake", "Muffin", "Omelet", "Pancakes", "Panini", "Pasta", "Pie", "Pizza", "Pork",
                "Potatoes", "Pudding", "Quesadilla", "Ravioli", "Rice", "Root Beer Float", "Salad", "Sandwich",
                "Scone", "Shrimp", "Soup", "Steak", "Stir fry", "Taco", "Toast", "Turkey", "Waffles", "Watermelon"};

        // 3 random numbers generated, if first[rand1] and last[rand2] not in database, add them to
        int[] rand = new int[3];
        Random rn = new Random();
        rand[0] = rn.nextInt(50);
        rand[1] = rn.nextInt(100);
        rand[2] = rn.nextInt(100);


        try {
            Statement stmt;
//            System.out.println("Deleting table in given database...");
//            stmt = db.getConnection().createStatement();
//
//            String sql = "DROP TABLE served ";
//
//            stmt.executeUpdate(sql);
//            System.out.println("Table  deleted in given database...");
            // Creating tables
            CustomerTable.createCustomerTable(db.getConnection());
            OrdersTable.createOrdersTable(db.getConnection());
            ServedTable.createServedTable(db.getConnection());

            OrdersTable.addOrder(db.getConnection(), 1, foodItems[rand[0]]);
            OrdersTable.addOrder(db.getConnection(), 2, "Pasta");
            OrdersTable.addOrder(db.getConnection(), 3, "Pizza");

            CustomerTable.addCustomer(db.getConnection(), 1, firstNames[rand[1]], lastNames[rand[2]], foodItems[rand[0]]);
            CustomerTable.addCustomer(db.getConnection(), 2, "Frank", "Cassidy", "Pizza");
            CustomerTable.addCustomer(db.getConnection(), 3, "Danny", "Coleman", "Pasta");

            System.out.println("Serving customer...");
            ServedTable.addServedCustomer(db.getConnection(), 1, firstNames[rand[1]], lastNames[rand[2]],foodItems[rand[0]]);

            CustomerTable.printCustomerTable(db.getConnection());
            OrdersTable.printOrdersTable(db.getConnection());
            ServedTable.printServedTable(db.getConnection());

            System.out.println("Deleting records...");
            ServedTable.deleteAllServedCustomer(db.getConnection());
            CustomerTable.deleteAllCustomer(db.getConnection());
            OrdersTable.deleteAllOrders(db.getConnection());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (db.getConnection() != null) {
            db.closeConnection();
        }
    }
}
