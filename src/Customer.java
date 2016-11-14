/**
 * file: Customer.java
 */
public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String foodItem;
    private int orderId;

    public Customer(int customerId, String firstName, String lastName, String foodItem) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.foodItem = foodItem;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFoodItem() {
        return foodItem;
    }

//    public int getOrderId() {
//        return orderId;
//    }
}
