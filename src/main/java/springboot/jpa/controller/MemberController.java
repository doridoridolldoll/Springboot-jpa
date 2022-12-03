package springboot.jpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import springboot.jpa.dto.MemberDto;
import springboot.jpa.entity.Address;
import springboot.jpa.entity.Member;
import springboot.jpa.entity.item.Book;
import springboot.jpa.form.MemberForm;
import springboot.jpa.repository.ItemRepository;
import springboot.jpa.repository.MemberRepository;
import springboot.jpa.service.MemberService;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        MemberDto member = new MemberDto();
        model.addAttribute("member", member);
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String join(@Valid MemberDto dto) {
        Address address = new Address(dto.getCity(), dto.getStreet(), dto.getZipcode());

        Member member = Member.builder()
                .name(dto.getName())
                .address(address)
                .build();

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "/members/memberList";
    }

    @PostConstruct
    public void init() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        for (int i = 0; i < 10; i++) {
            Address address = new Address("부산시" + i, "남구" + i, "4857" + i);
            Member member = Member.builder()
                    .name("member" + i)
                    .address(address)
                    .build();

            memberRepository.save(member);

            double math = Math.floor(Math.random() * 100);
            Book book = Book.builder()
                    .name("book" + (int) math)
                    .price((int) (1000 + (i * math)))
                    .stockQuantity((int) math)
                    .author(generatedString)
                    .isbn(String.valueOf((int) math * 100))
                    .build();

            itemRepository.save(book);
        }
    }
}
