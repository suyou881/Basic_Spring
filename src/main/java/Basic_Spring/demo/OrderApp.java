package Basic_Spring.demo;

import Basic_Spring.demo.member.Grade;
import Basic_Spring.demo.member.Member;
import Basic_Spring.demo.member.MemberServiceImpl;
import Basic_Spring.demo.member.MemberService;
import Basic_Spring.demo.order.Order;
import Basic_Spring.demo.order.OrderService;
import Basic_Spring.demo.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
//        MemberService memberServie = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);
    }
}
