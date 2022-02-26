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
            Member member1 = new Member(600L, "member1");
            Member member2 = new Member(700L, "member2");
            em.persist(member1);
            em.persist(member2);
            /*
                영속성 컨텍스트(1차 캐시)에 엔티티를 저장하고 쓰기 지연 저장소에 SQL을 쌓아둠
                ==> 영속성 컨텍스트에 엔티티와 쿼리를 쌓아둔다고 생각하면됨!!
             */

            System.out.println("====== SQL 실행 시작!! ======");
            tx.commit(); // DB에 영구 반영! => 쿼리문이 나가는 시점
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // em은 내부적으로 DB Connection을 사용해서 동작하기 때문에 사용 후 닫아줘야함
        }
        emf.close();
    }
}
