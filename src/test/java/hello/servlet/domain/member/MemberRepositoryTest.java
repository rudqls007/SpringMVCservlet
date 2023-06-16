package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class MemberRepositoryTest {


    MemberRepository memberRepository = MemberRepository.getInstance();

    // 테스트 하나씩 끝날 때마다 초기화 시켜줌.
    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }


    @Test
    void save(){
        //given
        Member member = new Member("hello", 20);
        //when
        Member savedMember = memberRepository.save(member);


        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }


    @Test
    void findAll(){

        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        // result 안에 멤버1, 멤버2 객체가 있냐? = contains
        assertThat(result).contains(member1, member2);
    }

}