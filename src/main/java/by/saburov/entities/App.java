package by.saburov.entities;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericApplicationContextExtensionsKt;
import org.springframework.context.support.SimpleThreadScope;

public class App {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("app.xml");
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        new XmlBeanDefinitionReader(applicationContext).loadBeanDefinitions("app.xml");
        applicationContext.getBeanFactory().registerScope("thread", new SimpleThreadScope());
        applicationContext.getBeanFactory().registerScope("customScope", new CustomScope());
        applicationContext.refresh();

        OrderService orderService = applicationContext.getBean(OrderService.class);
        orderService.createOrder(new Order(456));

        try {
            System.out.println("Waiting..");
            Thread.sleep(5000);
            System.out.println("Ready");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        OrderService orderService1 = applicationContext.getBean(OrderService.class);
        orderService1.createOrder(new Order(457));
    }
}
