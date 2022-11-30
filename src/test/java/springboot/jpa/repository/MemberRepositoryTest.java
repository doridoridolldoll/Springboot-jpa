package springboot.jpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import springboot.jpa.entity.Member;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void saveMember() {
        Member member = new Member();
        member.setName("memberA");
        System.out.println("member = " + member);

        Member savedId = memberRepository.save(member);
        System.out.println("savedId.getId() = " + savedId.getId());

        Member findMember = memberRepository.findOne(savedId.getId());
        System.out.println("findMember = " + findMember);
        System.out.println("findMember.getId() = " + findMember.getId());

        Assertions.assertThat(savedId.getId()).isEqualTo(findMember.getId());
        Assertions.assertThat(savedId.getName()).isEqualTo(findMember.getName());
        Assertions.assertThat(findMember).isEqualTo(member); //JPA 엔티티 동일성보장
    }

}