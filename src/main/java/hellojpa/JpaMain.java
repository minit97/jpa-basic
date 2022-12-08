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
            // 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member2 member = new Member2();
            member.setUsername("member1");

//            team.getMember2s().add(member); // mappedBy는 읽기 전용 // jpa에서 insert, update 할때 사용 X
            // 연관관계의 주인에 값 설정
            member.setTeam(team);   //**
            em.persist(member);

//            team.addMember(member);
//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId());  // 1차 캐시
            List<Member2> member2s = findTeam.getMember2s();
            for (Member2 m : member2s) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }

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
