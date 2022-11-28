package springboot.jpa.Repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import springboot.jpa.Entity.Member;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void saveMember() {
        Member member = new Member();
        member.setUsername("a");
        System.out.println("member = " + member);

        Member savedId = memberRepository.save(member);
        System.out.println("savedId.getId() = " + savedId.getId());

        Member findMember = memberRepository.findOne(savedId.getId());
        System.out.println("findMember = " + findMember);
        System.out.println("findMember.getId() = " + findMember.getId());

        Assertions.assertThat(savedId.getId()).isEqualTo(findMember.getId());
        Assertions.assertThat(savedId.getUsername()).isEqualTo(findMember.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);
    }

}