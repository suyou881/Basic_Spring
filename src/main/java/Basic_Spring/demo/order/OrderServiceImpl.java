package Basic_Spring.demo.order;

import Basic_Spring.demo.discount.DiscountPolicy;
import Basic_Spring.demo.discount.FixDiscountPolicy;
import Basic_Spring.demo.discount.RateDiscountPolicy;
import Basic_Spring.demo.member.Member;
import Basic_Spring.demo.member.MemberRepository;
import Basic_Spring.demo.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    /*
    * 현재 OrderServiceImpl 은 DiscountPolicy 인터페이스 뿐만 아니라
    * FixDiscountPolicy 구현 클래스도 의존하고 있다.
    * DIP 위반!
    * Dependency Inversion Principle ( 의존성 역전의 원칙 )
    *
    * 정책을 변경하려고 RateDiscountPolicy 로 변경하는 순간
    * OrderServiceImpl 소스 코드가 변경되야 한다.
    * OCP 위반!
    * Open Close Principle ( 개방폐쇄의 원칙 )
    * */
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
