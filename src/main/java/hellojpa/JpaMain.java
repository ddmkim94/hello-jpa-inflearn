package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); // 엔티티 매니저
        EntityTransaction tx = em.getTransaction();

        tx.begin(); // jpa에서 데이터를 변경하는 작업은 반드시 트랜잭션 안에서 이루어져야함

        try {

            Team team = new Team();
            team.setName("SKT T1 K");
            em.persist(team); // 쿼리 안나감

            Member member = new Member();
            member.setUsername("페이커");
            member.setAge(2002);
            member.setTeamId(team.getId());
            em.persist(member); // 쿼리 안나감

            Member findMember = em.find(Member.class, member.getId());
            Long teamId = findMember.getTeamId();

            Team findTeam = em.find(Team.class, teamId);
            System.out.println("findTeam = " + findTeam.getName());

            // 쿼리가 persist 시점에 나가는 경우 -> Only IDENTITY 전략
            System.out.println("========================");
            tx.commit(); // DB에 영구 반영! => 쿼리문이 나가는
            System.out.println("========================");
            // 시점
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // em은 내부적으로 DB Connection을 사용해서 동작하기 때문에 사용 후 닫아줘야함
        }
        emf.close();
    }
}
