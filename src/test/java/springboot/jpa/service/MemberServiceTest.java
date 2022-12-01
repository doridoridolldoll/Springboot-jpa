package springboot.jpa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springboot.jpa.entity.Member;
import springboot.jpa.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {

        Member member = Member.builder()
                .name("memberA")
                .build();
        member.setName("memberA");

        Long savedId = memberService.join(member);

        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {

        Member member1 = Member.builder()
                .name("memberA")
                .build();

        Member member2 = Member.builder()
                .name("memberA")
                .build();

        memberService.join(member1);
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            return;
        }
//        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());
//        System.out.println("thrown = " + thrown.getMessage());

        fail("예외가 발생해야 한다.");
    }
}