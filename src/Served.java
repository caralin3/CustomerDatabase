/**
 *
 */
public class Served {
    private int servedId;
    private int customerId;

    public Served(int servedId, int customerId) {
        this.servedId = servedId;
        this.customerId = customerId;
    }

    public int getServedId() {
        return servedId;
    }

    public int getCustomer() {
        return customerId;
    }
}
