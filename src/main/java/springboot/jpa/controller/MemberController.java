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

    @GetMapping("/members/login")
    public String loginForm() {
        return "members/login";
    }

    @PostMapping("/members/new")
    public String join(@Valid Member dto) {
        memberService.join(dto);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "/members/memberList";
    }

//    @PostConstruct
//    public void init() {
//        int leftLimit = 48; // numeral '0'
//        int rightLimit = 122; // letter 'z'
//        int targetStringLength = 10;
//
//        for (int i = 0; i < 10; i++) {
//            Address address = new Address("부산시" + i, "남구" + i, "4857" + i);
//
//            Member member = Member.builder()
//                    .name("member" + i)
//                    .email("email" + i)
//                    .password("12")
//                    .address(address)
//                    .build();
//
//            memberRepository.save(member);
//
//            Random random = new Random();
//            String generatedString = random.ints(leftLimit, rightLimit + 1)
//                    .filter(a -> (a <= 57 || a >= 65) && (a <= 90 || a >= 97))
//                    .limit(targetStringLength)
//                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                    .toString();
//
//            double math = Math.floor(Math.random() * 100);
//            Book book = Book.builder()
//                    .name("book" + (int) math)
//                    .price((int) (1000 + (i * math)))
//                    .stockQuantity((int) math)
//                    .author(generatedString)
//                    .isbn(String.valueOf((int) math * 100))
//                    .build();
//
//            itemRepository.save(book);
//        }
//    }
}
