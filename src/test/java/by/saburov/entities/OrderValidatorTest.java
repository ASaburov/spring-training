package by.saburov.entities;


import org.junit.Test;

public class OrderValidatorTest {
    NonThreadSafeValidator nonThreadSafeValidator = new NonThreadSafeValidator();
    OrderValidator orderValidator = new OrderValidator(()-> nonThreadSafeValidator);

    @Test
    public void testValidate(){
        orderValidator.isValid(new Order(123));
    }

}
