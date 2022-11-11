package Basic_Spring.demo.order;

import Basic_Spring.demo.discount.DiscountPolicy;
import Basic_Spring.demo.discount.FixDiscountPolicy;
import Basic_Spring.demo.member.Member;
import Basic_Spring.demo.member.MemberRepository;
import Basic_Spring.demo.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
