package Basic_Spring.demo.beanfind;

import Basic_Spring.demo.AppConfig;
import Basic_Spring.demo.member.MemberService;
import Basic_Spring.demo.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("look up by bean name")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(memberService.getClass());
    }

    @Test
    @DisplayName("look up by bean type")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(memberService.getClass());
    }

    @Test
    @DisplayName("look up by bean name")
    void findBeanByName2(){
        //MemberServiceImpl 로 조회할 수 있지만 좋은 코드는 아니다.
        //이전에 말했지만, 역활에 의존해야 한다. 구현이 아니라
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(memberService.getClass());
    }

    //NoSuchBeanDefinitionException: No bean named 'xxxxx' available
    @Test
    @DisplayName("no result with looking up by name")
    void findBeanByNameX(){
        //MemberService xxxxx = ac.getBean("xxxxx", MemberService.class);
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> {
            ac.getBean("xxxxx", MemberService.class);
        });
    }
}
