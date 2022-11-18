package Basic_Spring.demo.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Year;
import java.util.UUID;

@Component
//scope 이 request 이기 때문에 그냥 스프링을 돌리면 에러가 난다.
//Error creating bean with name 'myLogger': Scope 'request' is not active for the current thread
//request 가 요청이 들어온 다음에 컨테이너에서 사용할 수 있는데 요청 들어오기 전에 사용하려고 해서 난 에러.
//어떻게 해결할까? provider 를 사용하면 된다.
@Scope(value = "request")
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid+"]" + " [" + requestURL+"] " +message);
    }

    @PostConstruct
    void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid+"] request scope bean create: " + this);
    }


    @PreDestroy
    void close(){
        System.out.println("[" + uuid+"] request scope bean close: " + this);
    }

}
