package by.saburov.entities;

public class Order {
    private final Integer orderNumber;

    public Order(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                '}';
    }
}
