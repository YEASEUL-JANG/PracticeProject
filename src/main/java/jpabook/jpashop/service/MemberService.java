package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //데이터변경은 기본적으로 필요하며 조회에서는 트랜잭션에 읽기전용설정을 하는것이 성능적으로 좋다.
@RequiredArgsConstructor //final 필드만으로 생성자를 만들어줌.
public class MemberService {
    //일반적으로 필드주입을 많이 사용하긴 한다. 하지만 권장되는 방법은 생성자주입
//    @Autowired
//    private MemberRepository memberRepository;

    //생성자 주입을 사용하면 생성과 동시에 셋팅되기 때문에 변경할 수 없고
    //테스트시에도 유리하다.
    private final MemberRepository memberRepository;

    //@Autowired 생략도 된다.
//    public MemberService(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }

    //회원가입
    @Transactional
    public Long join(Member member){
        //중복회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId(); //id는 영속성 컨텍스트에 저장될 때 키값으로서 저장되기 때문에
        //id를 반환하면 값이 있다는 보장이 됨.
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findmembers = memberRepository.findByName(member.getName());
        if(!findmembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");

        }
    }
    //회원 전체조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 단건조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
