package Basic_Spring.demo;

import Basic_Spring.demo.discount.FixDiscountPolicy;
import Basic_Spring.demo.member.MemberServiceImpl;
import Basic_Spring.demo.member.MemberService;
import Basic_Spring.demo.member.MemoryMemberRepository;
import Basic_Spring.demo.order.OrderService;
import Basic_Spring.demo.order.OrderServiceImpl;

public class AppConfig {

    /*
    * memberService, orderService 를 보면 인터페이스에만 의존하고 있다.
    * */

    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(
                new MemoryMemberRepository(), new FixDiscountPolicy()
        );
    }
}
