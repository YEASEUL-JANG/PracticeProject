package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

//    @requestbody : json으로 온 body 안의 데이터를 Member객체로 바꿔준다.
    //v1 : 엔티티를 그대로 받는다거나 반환하는것은 유지보수에 좋지 않다.
    // - 기본적으로 엔티티 모든 값이 노출되며, 하나의 엔티티에 대한 다양한 api가 존재할 때 각각의 api를 위한 응답로직을 담기가 어렵다.
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }
    @GetMapping("/api/v1/members")
    public List<Member> membersV1(){
        return memberService.findMembers();
    }
//////////////////////////////////////////////////////////////////
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        member.setName(request.getName());
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @GetMapping("/api/v2/members")
    public Result membersV2(){
        List<MemberDto> collect = memberService.findMembers().stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());
        return new Result(collect.size(),collect);
    }
    //리스트를 바로 반환하면 배열타입으로 나가기 때문에 count나 다른 데이터 값을 추가할 수 있는 유연성이 떨어진다.
    //Result 객체로 한번 감싸서 나가는것이 좋다.

    @Data@AllArgsConstructor
    static class Result<T> {
        private  int count;
        private T data;
    }

    @Data@AllArgsConstructor
    static class MemberDto {
        private String name;
    }
    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse saveMemberV2(@PathVariable Long id,
                                             @RequestBody @Valid UpdateMemberRequest request ){
        memberService.update(id,request.getName());
        Member member = memberService.findOne(id);


        return new UpdateMemberResponse(member.getId(), member.getName());
    }


    @Data
    static class  UpdateMemberRequest {
        @NotEmpty
        private String name;
    }
    @Data
    @AllArgsConstructor
    static class  UpdateMemberResponse{
        private Long id;
        @NotEmpty
        private String name;

    }
    @Data
    static class  CreateMemberRequest {
        @NotEmpty
        private String name;
    }

    @Data
    static class  CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

}
