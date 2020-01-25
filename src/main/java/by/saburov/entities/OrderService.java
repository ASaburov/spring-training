package by.saburov.entities;

public class OrderService {
    private final OrderValidator orderValidator;

    public OrderService(OrderValidator orderValidator) {
        this.orderValidator = orderValidator;
        System.out.println("OrderService created");
    }

    public void createOrder(Order order) {
        if (orderValidator.isValid(order)) {
            System.out.println("Order " + order + " is created");
        } else {
            System.out.println("Order " + order + " is not valid");
        }


    }
}
