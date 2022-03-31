package hellojpa;

import hellojpa.jpabook.jpashop.domain.Address;

import javax.persistence.*;
import java.util.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); // 엔티티 매니저
        EntityTransaction tx = em.getTransaction();

        tx.begin(); // jpa에서 데이터를 변경하는 작업은 반드시 트랜잭션 안에서 이루어져야함

        try {
            // Map<Integer, List<Member>> paging = paging(em);

            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Team teamC = new Team();
            teamC.setName("팀C");
            em.persist(teamC);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setAge(2);
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setAge(3);
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setAge(4);
            member3.setTeam(teamB);
            em.persist(member3);

            Member member4 = new Member();
            member4.setUsername("회원4");
            member4.setAge(5);
            em.persist(member4);

            // 이 시점에 FLUSH 실행!(DB와 동기화, JPQL이 실행되기 때문에 FLUSH가 호출됨)
            int result = em.createQuery("update Member m set m.age = m.age * 10").executeUpdate();

            em.clear();

            Member findMember = em.find(Member.class, member1.getId());
            System.out.println("findMember = " + findMember.getAge());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // em은 내부적으로 DB Connection을 사용해서 동작하기 때문에 사용 후 닫아줘야함
        }
        emf.close();
    }

    private static Map<Integer, List<Member>> paging(EntityManager em) {
        for (int i = 1; i <= 100; i++) {
            Member member = new Member();
            member.setUsername("member" + i);
            member.setAge(i);
            em.persist(member);
        }

        // <페이지 번호, 멤버 목록>
        Map<Integer, List<Member>> paging = new HashMap<>();

        int page = 1;
        int count = -10;

        while (count < 90) {
            List<Member> result = em.createQuery("select m from Member m order by m.id desc", Member.class)
                    .setFirstResult(count + 10)
                    .setMaxResults(10)
                    .getResultList();

            List<Member> members = new ArrayList<>(result);
            paging.put(page, members);
            page++;
            count += 10;
        }
        return paging;
    }
}
