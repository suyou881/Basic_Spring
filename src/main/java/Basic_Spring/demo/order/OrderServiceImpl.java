package Basic_Spring.demo.order;

import Basic_Spring.demo.discount.DiscountPolicy;
import Basic_Spring.demo.member.Member;
import Basic_Spring.demo.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
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
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    //수정자 의존관계 테스트용 코드\
   /*@Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }*/

        @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //for testing
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
