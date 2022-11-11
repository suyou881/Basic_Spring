package Basic_Spring.demo;

import Basic_Spring.demo.member.Grade;
import Basic_Spring.demo.member.Member;
import Basic_Spring.demo.member.MemberServiceImpl;
import Basic_Spring.demo.member.MemberServie;
import Basic_Spring.demo.order.Order;
import Basic_Spring.demo.order.OrderService;
import Basic_Spring.demo.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberServie memberServie = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberServie.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);
    }
}
