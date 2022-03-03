package hellojpa.jpabook.jpashop.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

//            Member member = new Member();
//            member.setName("손님1");
//            em.persist(member);
//
//            Order order = new Order();
//            order.setOrderDate(LocalDateTime.now());
//            order.setStatus(OrderStatus.ORDER);
//            order.addMember(member);
//            em.persist(order);
//
//            Member findMember = em.find(Member.class, member.getId());
//            List<Order> orders = findMember.getOrders();
//            for (Order o : orders) {
//                System.out.println("o = " + o.getId());
//            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
