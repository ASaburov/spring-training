package by.saburov.entities;

import org.springframework.beans.factory.ObjectFactory;

import javax.inject.Provider;

public class OrderValidator {
    private int minOrderNumber = 1;

    private final Provider<NonThreadSafeValidator> nonThreadSafeValidator;

    public OrderValidator (Provider <NonThreadSafeValidator> nonThreadSafeValidator) {
        this.nonThreadSafeValidator = nonThreadSafeValidator;
    }

    public boolean isValid(Order order) {
        if (order.getOrderNumber() < minOrderNumber) {
            return false;

        }

        nonThreadSafeValidator.get().isValid(order);
        return true;
    }

    public void setMinOrderNumber(int minOrderNumber) {
        this.minOrderNumber = minOrderNumber;
    }
}
