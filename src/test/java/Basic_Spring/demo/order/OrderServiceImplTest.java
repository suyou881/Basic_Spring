package Basic_Spring.demo.order;

import Basic_Spring.demo.discount.DiscountPolicy;
import Basic_Spring.demo.discount.FixDiscountPolicy;
import Basic_Spring.demo.member.Grade;
import Basic_Spring.demo.member.Member;
import Basic_Spring.demo.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder(){
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());

        Order item = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(item.getDiscountPrice()).isEqualTo(1000);
    }
}