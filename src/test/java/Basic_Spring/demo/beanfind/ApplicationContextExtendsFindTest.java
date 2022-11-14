package Basic_Spring.demo.beanfind;

import Basic_Spring.demo.discount.DiscountPolicy;
import Basic_Spring.demo.discount.FixDiscountPolicy;
import Basic_Spring.demo.discount.RateDiscountPolicy;
import Basic_Spring.demo.member.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("When looking up by parent type, if there are more than 2 child, " +
            "NoUniqueBeanDefinitionException error occurs")
    public void findBeanByParentTypeDuplicate() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> {
            ac.getBean(DiscountPolicy.class);
        });
    }

    @Test
    @DisplayName("When looking up by parent type, if there are more than 2 child, " +
            " should set the name of bean")
    public void findBeanByParentTypeBeanName() {
        DiscountPolicy discountPolicy = ac.getBean("discountPolicy1", DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("look up by a particular sub type")
    public void findBeanBySubType() {
        DiscountPolicy discountPolicy = ac.getBean( RateDiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("look up all by parent type - Object")
    public void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value = " + beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy discountPolicy1() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy discountPolicy2() {
            return new FixDiscountPolicy();
        }
    }
}

