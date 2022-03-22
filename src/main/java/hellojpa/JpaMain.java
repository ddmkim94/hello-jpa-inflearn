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

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("박은빈");
            member.setAge(20);
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            String query = "select m.username from Team t inner join t.members m";
            List<String> result = em.createQuery(query, String.class)
                    .getResultList();

            for (String s : result) {
                System.out.println("s = " + s);
            }

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
