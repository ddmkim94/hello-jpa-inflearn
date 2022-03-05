package hellojpa.jpabook.jpashop.domain;

import hellojpa.jpabook.jpashop.domain.item.Album;
import hellojpa.jpabook.jpashop.domain.item.Book;

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

            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("김영한");
            book.setPrice(10000);
            book.setStockQuantity(1000);
            book.setIsbn("121032312321388");
            em.persist(book);

            Album album = new Album();
            album.setName("ALBUM1");
            album.setArtist("AA");
            album.setPrice(10000);
            album.setEtc("앨범 상세 정보");
            em.persist(album);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
