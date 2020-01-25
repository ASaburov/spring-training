package by.saburov.entities;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class NonThreadSafeValidator {
    public NonThreadSafeValidator() {
        System.out.println("NonThreadSafeValidator created");
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public boolean isValid(Order order) {
        int i = atomicInteger.incrementAndGet();
        if(i == 2){
            throw new IllegalStateException("NonThreadSafeValidator is called twice");
        }
        return true;
    }
}
