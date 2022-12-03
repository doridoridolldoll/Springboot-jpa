package springboot.jpa.service;

import springboot.jpa.dto.MemberDto;
import springboot.jpa.entity.Address;
import springboot.jpa.entity.Member;

import java.util.List;

public interface MemberService {

    Long join(Member member);
    void validateDuplicateMember(Member member);
    List<Member> findMembers();
    Member findOne(Long memberId);


    default MemberDto entityToDto(Member member) {
        MemberDto dto = MemberDto.builder()
                .name(member.getName())
                .city(member.getAddress().getCity())
                .street(member.getAddress().getStreet())
                .zipcode(member.getAddress().getZipcode())
                .orders(member.getOrders())
                .build();
        return dto;
    }

    default Member dtoToEntity(MemberDto dto) {
        Member entity = Member.builder()
                .name(dto.getName())
                .address(new Address(dto.getCity(), dto.getStreet(), dto.getZipcode()))
                .orders(dto.getOrders())
                .build();
        return entity;
    }
}
