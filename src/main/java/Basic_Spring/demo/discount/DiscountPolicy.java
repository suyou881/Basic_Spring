package Basic_Spring.demo.discount;

import Basic_Spring.demo.member.Member;

public interface DiscountPolicy {

/*
* @return 할인 대상 금액
* */

    int discount(Member member, int price);
}
