package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    //EntityManager: JPA에서 엔티티를 조작, DB와 통신을 수행하는 인터페이스
    //엔티티의 영속성 컨텍스트 관리, DB로의 변경 사항 자동 동기화
    //엔티티의 CRUD 기능 제공
    //@PersistenceContext: JPA 표준 스펙, 영속성 컨텍스트를 주입하는 표준 애노테이션
    @PersistenceContext
    EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
