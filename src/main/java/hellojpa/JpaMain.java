package hellojpa;

import hellojpa.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); // 엔티티 매니저
        EntityTransaction tx = em.getTransaction();

        tx.begin(); // jpa에서 데이터를 변경하는 작업은 반드시 트랜잭션 안에서 이루어져야힘

        try {
            Member findMember1 = em.find(Member.class, 100L); // 데이터베이스 조회 후 1차 캐시에 저장
            Member findMember2 = em.find(Member.class, 100L); // 1차 캐시 조회
            System.out.println(findMember1 == findMember2);

            tx.commit(); // DB에 영구 반영! => 쿼리문이 나가는 시점
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // em은 내부적으로 DB Connection을 사용해서 동작하기 때문에 사용 후 닫아줘야함
        }
        emf.close();
    }
}
