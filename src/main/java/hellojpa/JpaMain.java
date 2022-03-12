package hellojpa;

import hellojpa.jpabook.jpashop.domain.Address;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); // 엔티티 매니저
        EntityTransaction tx = em.getTransaction();

        tx.begin(); // jpa에서 데이터를 변경하는 작업은 반드시 트랜잭션 안에서 이루어져야함

        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(20);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setAge(30);
            em.persist(member2);

            // DTO를 따로 만들어서 쿼리문을 만드는 방법
            List<UserDTO> members = em.createQuery("select new hellojpa.UserDTO(m.username, m.age) from Member m", UserDTO.class)
                    .getResultList();

            for (UserDTO m : members) {
                System.out.println("username = " + m.getUsername());
                System.out.println("age = " + m.getAge());
            }

            tx.commit(); // DB에 영구 반영! => 쿼리문이 나가는
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // em은 내부적으로 DB Connection을 사용해서 동작하기 때문에 사용 후 닫아줘야함
        }
        emf.close();
    }
}
