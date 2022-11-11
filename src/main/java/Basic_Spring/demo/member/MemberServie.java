package Basic_Spring.demo.member;

public interface MemberServie {
    void join(Member member);

    Member findMember(Long memberId);
}
