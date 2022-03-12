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
            Address address1 = new Address("서울", "망우로20길 86", "02514");
            Address address2 = new Address("서울", "망우로20길 86", "02514");

            System.out.println("equals(), hashcode() 생성 후 주소 비교 : " + address1.equals(address2));

            Member member = new Member();
            member.setName("박은빈");
            member.setAddress(new Address("서울", "망우로20길 86", "02514"));
            em.persist(member);

            System.out.println("===================");
            tx.commit();
            System.out.println("===================");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
