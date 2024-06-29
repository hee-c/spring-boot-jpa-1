package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext
//    private EntityManager em;

//    @Autowired -> 원래 이렇게하면 못찾는데 최신 스프링 부트에서는 @PersistenceContext 대신 @Autowired를 사용해도 찾아준다.
//    @Autowired를 사용할 수 있기 때문에 @RequiredArgsConstructor를 사용할 수 있다.
//    왜냐면 @Autowired는 생성자가 하나만 있으면 자동으로 주입해주기 때문이다.
//    private EntityManager em;

    //    @RequiredArgsConstructor를 사용하면 final이 붙은 필드만 생성자를 만들어준다.
    private final EntityManager em;

//    이렇게도 사용할 수 있음. 이러면 테스트할때 em 변경하기 용이함.
//    그런데 처음 constructor 된 이후에 변경할 일이 없기 때문에 이렇게 안쓰는게 낫다.
//    public void setEntityManager(EntityManager em) {
//        this.em = em;
//    }

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
