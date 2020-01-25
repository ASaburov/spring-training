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
        applicationContext.refresh();


        OrderService orderService = applicationContext.getBean(OrderService.class);
        orderService.createOrder(new Order(456));
        orderService.createOrder(new Order(457));
//        applicationContext.getBean(OrderService.class);
//        applicationContext.getBean(OrderService.class);
//        applicationContext.getBean(OrderService.class);
//        new Thread(()->{
//            applicationContext.getBean(OrderService.class);
//        }).start();
//        new Thread(()->{
//            applicationContext.getBean(OrderService.class);
//        }).start();
//        orderService.createOrder(new Order(123));
    }
}
