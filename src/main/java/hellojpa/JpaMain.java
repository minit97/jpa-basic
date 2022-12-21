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
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member2 member1 = new Member2();
            member1.setUsername("member1");
            member1.setTeam(team);

            em.persist(member1);

            em.flush();
            em.clear();

            List<Member2> members = em.createQuery("select m from Member2 m join fetch m.team", Member2.class)
                    .getResultList();

//            Member2 m = em.find(Member2.class, member1.getId());
//            System.out.println("m = " + m.getTeam().getClass());    // proxy
//
//            System.out.println("==============");
//            m.getTeam().getName();  // 조회 쿼리
//            System.out.println("==============");

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
