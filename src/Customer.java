/**
 *
 */
public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private Orders orders;

    public Customer(int customerId, String firstName, String lastName, Orders orders) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.orders = orders;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() { return lastName; }

    public Orders getOrders() {
        return orders;
    }

    public int getCustomerId() {
        return customerId;
    }
}
