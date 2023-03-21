package jpql;

import javax.persistence.*;
import java.util.List;

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
public class JpaMain3 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //JPQL 문법

            //////////
            //페이징
            //////////
            List<Member> resultList = em.createQuery("select m from Member m order by m.age desc", Member.class)
                                        .setFirstResult(0)//시작 위치
                                        .setMaxResults(10)//갯수
                                        .getResultList();
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
