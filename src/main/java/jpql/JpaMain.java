package jpql;

import javax.persistence.*;
import java.util.Collection;
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

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setType(MemberType.ADMIN);
            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

//            String query =  "select m.username from Member m ";                   // 상태 필드
//            String query =  "select m.team.name from Member m ";                  // 단일 값 연관 경로
            String query =  "select m.username from Team t join t.members m";            // 컬렉션 값 연관 경로
            List<Collection> result = em.createQuery(query, Collection.class)
                    .getResultList();

            System.out.println("result = " + result);

            tx.commit();;
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
