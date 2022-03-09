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

            Address address = new Address("seoul", "sagajungro", "1557");

            Member member = new Member();
            member.setName("박은빈");
            member.setAddress(address);
            member.setPeriod(new Period(LocalDateTime.of(2020, 1, 1, 1, 1), LocalDateTime.now()));
            em.persist(member);

            Address newAddress = new Address("대전", address.getStreet(), address.getZipcode());
            member.setAddress(newAddress);

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
