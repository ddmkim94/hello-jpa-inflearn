package hellojpa.jpabook.jpashop.domain;

import hellojpa.jpabook.jpashop.domain.item.Book;
import hellojpa.jpabook.jpashop.domain.item.Item;
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
            Book book = new Book();
            book.setIsbn("1234");
            book.setPrice(10000);
            em.persist(book);

            OrderItem orderItem = new OrderItem();
            orderItem.setItem(book);
            orderItem.setOrderPrice(10000);
            orderItem.setCount(10);

            Delivery delivery = new Delivery();
            delivery.setDeliveryStatus(DeliveryStatus.COMP);
            delivery.setCity("seoul");
            delivery.setStreet("road");
            delivery.setZipcode("02231");

            Order order = new Order();
            order.setOrderDate(LocalDateTime.now());
            order.addOrderItem(orderItem);
            order.addDelivery(delivery);

            Member member = new Member();
            member.setName("member1");
            member.setCity("seoul");
            member.addOrder(order); // 연관관계 편의 메서드
            em.persist(member);

            em.flush();
            em.clear();

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
