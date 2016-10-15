/**
 *
 */
public class Orders {
    private int orderId;
    private String foodItem;

    public Orders(String foodItem) {
        this.foodItem = foodItem;
    }
    public Orders(int orderId, String foodItem) {
        this.orderId = orderId;
        this.foodItem = foodItem;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getFoodItem() {
        return foodItem;
    }
}
