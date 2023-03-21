package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
public class JpaMain4 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //JPQL 문법

            //////////
            //조인
            //////////
            em.createQuery("select m from Member m join m.team t", Member.class);//이너조인
            em.createQuery("select m from Member m left join m.team t", Member.class);//아우터조인
            em.createQuery("select count(m) from Member m, Team t where m.username = t.name", Member.class);//세타조인

            //////////
            //On절 (조인 대상 필터링)
            //////////
            //Ex) 회원과 팀을 조인하면서, 팀 이름이 A인 팀만 조인
            em.createQuery("select m, t from Member m Left Join m.team t on t.name = 'A' ", Member.class);

            //////////
            //연관관계가 없는 엔티티 외부 조인
            //////////
            //Ex) 회원의 이름과 팀의 이름이 같은 대상 외부 조인
            em.createQuery("select m, t from Member m Left Join Team t on m.username = t.name", Member.class);//SQL과 똑같음

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
