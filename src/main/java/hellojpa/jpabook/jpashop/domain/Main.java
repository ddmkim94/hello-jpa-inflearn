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
            Order order1 = new Order();
            order1.setOrderDate(LocalDateTime.now());

            Member member = new Member();
            member.setName("member1");
            member.setCity("seoul");
            member.addOrder(order1);
            em.persist(member);
            em.persist(order1);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            // findMember.getOrders().remove(0);
            em.remove(findMember);

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
