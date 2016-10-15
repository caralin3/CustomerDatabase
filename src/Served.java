/**
 *
 */
public class Served {
    private int servedId;
    private Customer customer;

    public Served(int servedId, Customer customer) {
        this.servedId = servedId;
        this.customer = customer;
    }

    public int getServedId() {
        return servedId;
    }

    public Customer getCustomer() {
        return customer;
    }
}
