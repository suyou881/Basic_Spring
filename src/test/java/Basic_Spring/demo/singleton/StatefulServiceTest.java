package Basic_Spring.demo.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    public void statefulServiceSingleton(){
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //Thread1: A request 10000
        statefulService1.order("userA", 10000);
        //Thread2: B request 20000
        statefulService2.order("userB", 20000);

        //ThreadA: inquire order price
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);  // it shows 20000...
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}