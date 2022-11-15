package Basic_Spring.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //AppConfig 에 @Configuration 어노테이션이 있다.
        //@Configuration 또한 componentScan 에 대상이 되기 때문에 충돌을 막으려고 exclude 함.
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
