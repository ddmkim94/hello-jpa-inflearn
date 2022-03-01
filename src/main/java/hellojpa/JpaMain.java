package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); // 엔티티 매니저
        EntityTransaction tx = em.getTransaction();

        tx.begin(); // jpa에서 데이터를 변경하는 작업은 반드시 트랜잭션 안에서 이루어져야함

        try {

            Team team = new Team();
            team.setName("SKT T1 K");
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("페이커");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("박은빈");
            member2.setTeam(team);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("이정현");
            member3.setTeam(team);
            em.persist(member3);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member1.getId());
            List<Member> members = findMember.getTeam().getMembers();
            for (Member m : members) {
                System.out.println(m.getUsername());
            }

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
