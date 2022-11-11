package Basic_Spring.demo.order;

import Basic_Spring.demo.member.Grade;
import Basic_Spring.demo.member.Member;
import Basic_Spring.demo.member.MemberServiceImpl;
import Basic_Spring.demo.member.MemberServie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberServie memberServie = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();


    @Test
    public void createOrder(){
        //given
        Long memberId = 1L;

        //when
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberServie.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
