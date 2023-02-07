package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 1. JPQL
//            List<Member2> resultList = em.createQuery("select m From Member2 as m where m.username like '%kim%'", Member2.class).getResultList();
//            for (Member2 member2 : resultList) {
//                System.out.println("member2 = " + member2.getId());
//            }

            // 2. Criteria
//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Member2> query = cb.createQuery(Member2.class);
//
//            Root<Member2> m = query.from(Member2.class);
//            CriteriaQuery<Member2> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
//            List<Member2> resultList = em.createQuery(cq).getResultList();

            Member2 member2 = new Member2();
            member2.setUsername("member1");
            em.persist(member2);

            // flush -> commit, query

            em.flush();

            // 결과0
            // db.conn.executeQuery("select * from member");

            tx.commit();;
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        // 웹어플리케이션이면 WAS가 내려갈때 엔티티매니저팩토리를 닫아줘야한다.
        emf.close();


    }
}
