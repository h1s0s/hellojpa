package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * packageName  : hellojpa
 * fileName     : JpaMain
 * author       : sshan
 * date         : 2023-02-26
 * description  :
 * ========================================================
 * DATE            AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-02-26          sshan            최초생성
 */
public class JpaMain5 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //JPQL 문법

            //////////
            //서브쿼리
            //////////
            em.createQuery("select m from Member m where m.age > (select avg(m2.age) from Member m2)", Member.class);
            //exist, all|any|some : 모두 사용 가능
            //all 모두 만족하면 참, any, Some : 하나만 만족해도 참, In : 서브쿼리중 하나라도 같은것이 있으면 참

            //JPA 서브쿼리의 한계, From절의 서브쿼리는 현재 JPQL에서 불가능
            //JPA는 where, having절 에서만 서브쿼리 사용 가능, select절은 하이버네이트에서 지원

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
