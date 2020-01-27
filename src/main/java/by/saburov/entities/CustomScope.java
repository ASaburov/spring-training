package by.saburov.entities;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomScope implements Scope {
    private AtomicInteger counter;
    private Map<String, Object> scopedObjects
            = Collections.synchronizedMap(new HashMap<String, Object>());
    private Map<String, Runnable> destructionCallbacks
            = Collections.synchronizedMap(new HashMap<String, Runnable>());
    private LocalTime timeLimit;

    public CustomScope() {
        setTimeLimit();
    }


    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        if(checkTime()){
            scopedObjects.clear();
        }

        if(!scopedObjects.containsKey(name)) {
            scopedObjects.put(name, objectFactory.getObject());
            System.out.println("There is no " + name + " bean in the scope. Creating the new one");
        }
        return scopedObjects.get(name);
    }

    @Override
    public Object remove(String name) {
        destructionCallbacks.remove(name);
        return scopedObjects.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        destructionCallbacks.put(name, callback);
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }


    public void setTimeLimit(){
        LocalTime current = LocalTime.now();
        timeLimit = current.plusSeconds(3);
    }

    public boolean checkTime(){
        LocalTime current = LocalTime.now();
        return current.isAfter(timeLimit);
    }


}
