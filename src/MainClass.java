///**
// * Main
// */
//
//import java.sql.*;
//import java.util.Random;
//
//public class MainClass{
//
//    private Connection conn;
//    private static boolean quit;
//    private static MainClass mc;
//    private static int count;
//    /**
//     * Create a database connection with the given params
//     * @param user: user name for the owner of the database
//     * @param password: password of the database owner
//     */
//    public void createConnection(String user, String password) {
//        try {
//
//            // This needs to be on the front of your location
//            String url = "jdbc:h2:~/test";
//
//            // This tells it to use the h2 driver
//            Class.forName("org.h2.Driver");
//
//            // creates the connection
//            conn = DriverManager.getConnection(url, user, password);
//
//        } catch (SQLException | ClassNotFoundException e) {
//            //You should handle this better
//            e.printStackTrace();
//        }
//    }
//
//    public Connection getConnection(){
//        return conn;
//    }
//
//
//    public static class populate implements Runnable {
//        private String firstName;
//        private String lastName;
//        private Orders orders;
//        public populate (String firstName, String lastName, Orders orders) {
//            this.firstName = firstName;
//            this.lastName = lastName;
//            this.orders = orders;
//        }
//
//        public void run() {
//            mc = new MainClass();
//            try {
//                while(!quit) {
////                    CustomerTable.insertCustomer(mc.getConnection(), firstName, lastName, orders);
//                    OrdersTable.insertOrder(mc.getConnection(), orders.getFoodItem());
//                }
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static class service implements Runnable {
//        private int id;
//        private String firstName;
//        private String lastName;
//        private Orders orders;
//        public service (int id, String firstName, String lastName, Orders orders) {
//            this.id = id;
//            this.firstName = firstName;
//            this.lastName = lastName;
//            this.orders = orders;
//        }
//
//        public void run() {
//            mc = new MainClass();
//            try {
//                while(!quit) {
//               //     ServedTable.insertServed(mc.getConnection(), new Customer(id, firstName, lastName, orders));
//                  //  CustomerTable.deleteCustomer(mc.getConnection(), new Customer(id, firstName, lastName, orders));
//                    OrdersTable.deleteOrder(mc.getConnection(), orders);
//                }
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void quitLoop() {
//        if(count >= 1000)
//            quit = true;
//    }
//    public static void main(String[] args) {
//        mc = new MainClass();
//
////        String location = "../_db/threadBase";
//        String user = "username";
//        String password = "password1";
//
//        mc.createConnection(user, password);  // create connection
//
//        quit = false;
//        try {
//            CustomerTable.createCustomerTable(mc.getConnection());  // create customer table
//            OrdersTable.createOrderTable(mc.getConnection());
//            ServedTable.createServedTable(mc.getConnection());
//
//            String[] first = new String[] {"Abigail", "Abraham", "Aidan", "Alex", "Amy", "Andrea", "Andrew", "Ashley",
//                    "Barbra", "Bianca", "Billy", "Blake", "Bob", "Bonnie", "Brandon", "Brenda", "Britney", "Camille",
//                    "Candice", "Carl", "Caroline", "Catherine", "Charles", "Chloe", "Chris", "Claire", "Clarence", "Cody",
//                    "Cory", "Courtney", "Danny", "Ellie", "Emily", "Emma", "Frank", "George", "Gordon", "Grace", "Haley",
//                    "Hannah", "Hillary", "Isabelle", "Jackson", "James", "Jason", "Jennifer", "Jessica", "Jimmy", "Joe",
//                    "John", "John", "Julia", "Kate", "Kelly", "Kimberly", "Lauren", "Lily", "Lincoln", "Lisa", "Lucy",
//                    "Luke", "Luther", "Mary", "Max", "Michael", "Michelle", "Miley", "Monica", "Morton", "Ned", "Nick",
//                    "Oliver", "Oscar", "Patrick", "Percy", "Phoebe", "Rachel", "Rebecca", "Richard", "Rick", "Roxanne",
//                    "Ryan", "Sam", "Sarah", "Sheen", "Shelby", "Simon", "Sophie", "Spencer", "Susie", "Tanya", "Tessa",
//                    "Thomas", "Tim", "Toby", "Todd", "Victoria", "Willow", "Xavier", "Zeke"};
//
//            String[] last = new String[] {"Abbot", "Adams", "Allan", "Andrews","Arthur", "Baez", "Baldwin", "Bell",
//                    "Black", "Brown", "Bryant", "Burke", "Cameron", "Campbell", "Carpenter", "Carson",
//                    "Cassidy", "Chambers", "Chan", "Clifford", "Coleman", "Cook", "Cullen", "Davis", "Edwards",
//                    "Evans", "Finster", "Fisher", "Fitzgerald", "Fitzpatrick", "Gabriel", "Geller", "Gilbert",
//                    "Godwin", "Goldberg", "Gonzales", "Goodman", "Graham", "Green", "Griffin", "Harper", "Harrison",
//                    "Hart", "Hathaway", "Holt", "Jefferson", "Johnson", "Jones", "Kahn", "Kennedy", "King", "Krieger",
//                    "Lambert", "Lawrence", "Lee", "Lewis", "Lloyd", "Long", "Lopez", "Martin", "Mason", "Matthews",
//                    "McDonald", "McGuire", "Mendoza", "Miller", "Mitchell", "Moore", "Neutron", "Nichols", "O'Neal",
//                    "O'Reilly", "Parker", "Perez", "Perry", "Peterson", "Potter", "Roberts", "Robertson", "Robinson",
//                    "Rodriguez", "Sanchez", "Singer", "Singh", "Smith", "Swan", "Taylor", "Thompson", "Turner",
//                    "Underwood", "Vasquez", "Waldorf", "Wayne", "Wesley", "White", "Williams", "Wilson", "Wolfe",
//                    "Wong", "Wright"};
//
//            String[] food = new String[] {"Apple", "Bagel", "Banana", "Beef Stew", "Brownie", "Burger", "Cake",
//                    "Calzone", "Cereal", "Chicken", "Chili", "Chocolate", "Coffee", "Cookie", "Croissant", "Cupcake",
//                    "Doughnut", "Fish", "Fries", "Hot Chocolate", "Ice Cream", "Lasagna", "Macaroni and Cheese",
//                    "Milkshake", "Muffin", "Omelet", "Pancakes", "Panini", "Pasta", "Pie", "Pizza", "Pork",
//                    "Potatoes", "Pudding", "Quesadilla", "Ravioli", "Rice", "Root Beer Float", "Salad", "Sandwich",
//                    "Scone", "Shrimp", "Soup", "Steak", "Stir fry", "Taco", "Toast", "Turkey", "Waffles", "Watermelon"};
//
//            Orders[] orderses = new Orders[50];
//            int temp = 0;
//            for (String s : food) {
//                Orders o = new Orders(s);
//                orderses[temp] = o;
//                temp++;
//            }
//
//            // 3 random numbers generated, if first[rand1] and last[rand2] not in database, add them to
//            int[] rand = new int[3];
//            Random rn = new Random();
//            for (int i: rand) {
//                rand[i] = rn.nextInt(100);
//            }
//
//            int i = 1;
////            while(!quit) {
//                if (!CustomerTable.selectCustomerName(mc.getConnection(),
//                        new Customer(i, first[rand[0]], last[rand[1]], orderses[rand[2] % 50].getFoodItem()))) {
//                    CustomerTable.insertCustomer(mc.getConnection(), "bob", "marley", orderses[rand[2] % 50].getFoodItem());
//                  //  Thread t1 = new Thread(new populate(first[rand[0]], last[rand[1]], orderses[rand[2 % 50]]));
////                    t1.start();
////                    Thread t2 = new Thread(new service(i, first[rand[0]], last[rand[1]], orderses[rand[2 % 50]]));
////                    t2.start();
////                    t1.join();
////                    t2.join();
////                    i++;
////                    count++;
////                }
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
