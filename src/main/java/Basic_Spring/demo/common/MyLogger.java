package Basic_Spring.demo.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
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

//두번째 방법은 프록시 방식을 사용하면 된다.
//proxyMode = ScopedProxyMode.TARGET_CLASS 를 추가해주자.
//적용대상이 인터페이스라면 INTERFACE, 클래스라면 TARGET_CLASS 를 선택
//이렇게 하면 MyLogger의 가짜 프록시 클래스를 만들고, HTTP request 와 상관 없이 프록시 클래스를 다른 빈에 미리 주입할 수 있다.
//How? CGLIB 라는 라이브러리로 내 클래스를 상속 받은 가짜 프록시 객체를 만들어서 주입한다.
//가짜 프록시 객체는 요청이 오면 그때 내부에 진짜 myLogger를 찾는 방법을 알고 있다.
//다형성을 이용해서 가짜 프록시 객체가 원본 클래스를 상속 받았기 때문에, 클라이언트 입장에서는 그냥 사용하면 된다.

//provider, 프록시 모두 핵심 아이디어는 진짜 객체 조회를 꼭 필요한 시점까지 지연처리 한다는 점이다.
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
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
