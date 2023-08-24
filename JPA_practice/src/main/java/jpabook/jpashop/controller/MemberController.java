package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm",new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    //@Valid : 객체에서 설정한 notnull이나 null, min 등의 유효성 검사를 체크해줌
    //BindingResult : 넘어온 객체에 오류가 있으면 보통 팅기지만 BindingResult 처리를하면 여기에 담은 후 아래 코드를 실행함.
    //Member 엔티티를 파라미터로 받을수도 있지만 validation처리라던지, 실질적으로 필요한 data와 엔티티가 맞지 않을 수 있어서 form데이터를 따로 만드는것이 좋다.
    public String create(@Valid MemberForm form, BindingResult result){
        if(result.hasErrors()) {
            return "members/createMemberForm"; //에러를 해당 화면에 뿌리기 가능
        }
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
