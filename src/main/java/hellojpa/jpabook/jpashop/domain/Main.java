package hellojpa.jpabook.jpashop.domain;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        PersistenceUnitUtil persistenceUnitUtil = emf.getPersistenceUnitUtil();
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member = new Member();
            member.setName("member1");
            member.setCity("seoul");
            em.persist(member);

            Order order1 = new Order();
            order1.setOrderDate(LocalDateTime.now());
            order1.setMember(member);
            em.persist(order1);

            em.flush();
            em.clear();

            Order findOrder = em.find(Order.class, order1.getId());
            System.out.println(findOrder.getClass());
            System.out.println("==================");
            findOrder.getMember().getName(); // Member 객체 사용 시점에 proxy 초기화! (Member Select)
            System.out.println("==================");
            System.out.println(findOrder.getMember().getClass());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
