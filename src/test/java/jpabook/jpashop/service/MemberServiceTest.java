package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional //test에서 트랜잭션은 롤백기능까지 포함되어있다.
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Test
    @Rollback(false) //롤백을하지않고 commit을 함 -> db에 insert
    public void 회원가입() throws Exception {
        Member member = new Member();
        member.setName("kim");
        Long saveId = memberService.join(member);

        //같은 영속성 컨텍스트에서 조회하기 때문에 true 가 나옴.
        assertEquals(member, memberRepository.findOne(saveId));

    }

    @Test(expected = IllegalStateException.class) //try-catch문 생략가능
    public void 중복_회원_예외() throws Exception{
    Member member1 = new Member();
    member1.setName("kim");
    Member member2 = new Member();
    member2.setName("kim");

    memberService.join(member1);
    memberService.join(member2);
//    try {
//        memberService.join(member2); //예외가 발생해야함.
//    }catch (IllegalStateException e) {
//        return;
//    }

        fail("예외가 발생해야 한다."); //이 문장이 찍히면 잘못된 것. (여기까지 오면 안됨)
    }
}