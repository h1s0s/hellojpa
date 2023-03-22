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
public class JpaMain6 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //JPQL 문법

            //////////
            //경로 표현식
            //////////
            //점을 찍어 객체 그래프를 탐색하는것
            //select m.username -> 1. 상태 필드(state filed) : 단순히 값을 저장하기 위한 필드
            //from Member m
            //join m.team t -> 2. 단일 값 연관 필드(association filed):연관관계를 위한 필드 (@ManyToOne, @OntToOne, 대상이 엔티티)
            //join m.orders o -> 컬렉션 값 연관 필드(association filed):연관관계를 위한 필드 (@OneToMany, @ManyToMany, 대상이 컬렉션)
            //where t.name = '팀A'

            //상태필드 : 경로 탐색의 끝, 더이상 탐색X (ex. m.username 여기서 .을 찍어서 더 들어갈 수 없다는 뜻)
            //단일값 연관경로 : 묵시적 내부 조인 발생. 탐색O (ex, m.team.name 가능)
            //컬렉션 값 연관경로 : 묵시적 내부 조인 발생, 탐색X FROM절에서 명시적 조인을 통해 별칭을 얻으면 별칭을 통해 탐색 가능

            //묵시적 내부조인은 튜닝하기 어렵기 때문에 사용하지 않는것이 좋음

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
