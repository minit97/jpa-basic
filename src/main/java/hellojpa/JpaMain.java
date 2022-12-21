package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member2 member1 = new Member2();
            member1.setUsername("member1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member2 refMember = em.getReference(Member2.class, member1.getId());
            System.out.println("refMember = " + refMember.getClass());  //Proxy
//            refMember.getUsername();    // 프록시 강제 초기화

            Hibernate.initialize(refMember);    // 강제초기화

//            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));   // 초기화 여부

//            Member2 findMember = em.find(Member2.class, member1.getId());
//            System.out.println("findMember = " + findMember.getClass());
//
//            System.out.println("refMember == findMember: " + (refMember == findMember));    //true  // JPA에선 타입 == 을 true로 보장?

            //
//            Member2 findMember = em.find(Member2.class, member2.getId());
//            Member2 findMember = em.getReference(Member2.class, member2.getId());           // 사용시점에 DB에 쿼리를 날림
//            System.out.println("findMember = " + findMember.getClass());                    // class hellojpa.Member2$HibernateProxy$HYA9ddRQ : 프록시 클래스
//            System.out.println("findMember = " + findMember.getId());                       // 위에서 이미 알고 있기에 쿼리문 X
//            System.out.println("findMember.getUsername() = " + findMember.getUsername());   // DB 조회 쿼리


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        // 웹어플리케이션이면 WAS가 내려갈때 엔티티매니저팩토리를 닫아줘야한다.
        emf.close();


    }
}
