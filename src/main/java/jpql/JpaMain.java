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

            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamB);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원2");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

            String query =  "select m From Member m join fetch m.team t";
            List<Team> result = em.createQuery(query, Team.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();

            for (Team team : result) {
//                System.out.println("member = " + member.getUsername() + "," + member.getTeam().getName());
                // 회원1, 팀A(SQL)
                // 회원2, 팀A(1차캐시)
                // 회원3, 팀B(SQL)

                // 회원 100명 -> N + 1 (1은 회원, N은 팀)

                System.out.println("team = " + team.getName()+"|members"+team.getMembers().size());
                for(Member member: team.getMembers()) {
                    System.out.println("-> member = " + member);
                }
            }

            tx.commit();;
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
