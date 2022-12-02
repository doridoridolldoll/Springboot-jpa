package springboot.jpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springboot.jpa.entity.Address;
import springboot.jpa.entity.Member;
import springboot.jpa.form.MemberForm;
import springboot.jpa.repository.MemberRepository;
import springboot.jpa.service.MemberService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String join(@Valid MemberForm form) {
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member newMember = Member.builder()
                .name(form.getName())
                .address(address)
                .build();

        memberService.join(newMember);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "/members/memberList";
    }
}
