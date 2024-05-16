package jpabook.jpashop;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    //EntityManager를 통한 모든 데이터 변경은 항상 트랜잭션 안에서 이루어져야 함 -> @Transactional 사용!
    //@Transactional가 테스트에 있으면, 테스트 수행 후 롤백해버림(DB에서 확인X) -> @Rollback(false)로 롤백안하고 커밋!
    @Test
    @Transactional
    @Rollback(false)
    public void testMember() {
        Member member = new Member();
        member.setUsername("wooki");
        Long savedId = memberRepository.save(member);

        Member findMember = memberRepository.find(savedId);

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        //같은 트랜잭션에서 저장, 조회 -> 같은 영속성 컨텍스트임 -> 같은 영속성 컨텍스트에서 id가 같으면 같은 엔티티로 식별!
        //hibernate에서 select 쿼리를 날리지조차 않음! -> 1차 캐시에서 찾아와버리니까
        assertThat(findMember).isEqualTo(member);
    }
}
