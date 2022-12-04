package springboot.jpa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.jpa.dto.MemberDto;
import springboot.jpa.entity.Member;
import springboot.jpa.repository.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long join(MemberDto dto) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        Member entity = dtoToEntity(dto);
        validateDuplicateMember(entity);
        memberRepository.save(entity);
        return entity.getId();
    }

    @Override
    public void validateDuplicateMember(Member member) {
        Member findMembers = memberRepository.findByEmail(member.getEmail());
        String email = findMembers.getEmail();
        if (email == member.getEmail()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    /**
     * 상세 정보 조회
     * Security 지정 서비스이므로 필수 구현
     *
     * @param email
     * @return 사용자의 계정정보와 권한을 갖는 UserDetails 인터페이스 반환
     * @throws UsernameNotFoundException
     */
    @Override
    public Member loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(email);
    }
}
