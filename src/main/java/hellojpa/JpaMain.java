package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            // 비영속
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloA");
//            // 영속
//            em.persist(member);
//
//            Member findMember1 = em.find(Member.class, 1L);
//            Member findMember2 = em.find(Member.class, 1L);
//            System.out.println(findMember2 == findMember1);

//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");

            // 대상이 테이블이 아닌 객체다!
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)  // 5번부터
//                    .setMaxResults(8)   // 8개 가져와라
//                    .getResultList();
//
//            for (Member member: result){
//                System.out.println("member.name = " + member.getName());
//            }

//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(150L, "A");
//
//            em.persist(member1);
//            em.persist(member2);

            Member member = new Member(200L, "member200");
            em.persist(member);
            
            em.flush();     // 커밋 전 쿼리확인 가능
//            em.detach(member);

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
