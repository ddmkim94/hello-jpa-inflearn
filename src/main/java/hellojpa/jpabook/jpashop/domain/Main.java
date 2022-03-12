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
            Member member = new Member();
            member.setName("박은빈");
            member.getAddressHistory().add(new AddressEntity("seoul", "sagajungro", "1557"));
            member.getAddressHistory().add(new AddressEntity("busan", "sungro", "1547"));
            member.setPeriod(new Period(LocalDateTime.of(2020, 1, 1, 1, 1), LocalDateTime.now()));
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
