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
public class JpaMain2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //JPQL 문법

            //////////
            //프로젝션
            //select절에 조회할 대상을 지정하는 것
            //프로젝션의 대상 : 엔티티, 임베디드 타입, 스칼라 타입(숫자, 문자 등 기본 데이터 타입)
            //////////
            TypedQuery<Member> query;
            query = em.createQuery("select m from Member m", Member.class);//엔티티 프로젝션 -> 엔티티를 조회한다
            List<Member> result = query.getResultList();
            /*
            query = em.createQuery("select m.team from Member m", Team.class);//엔티티 프로젝션 -> 엔티티 안에있는 엔티티를 조회한다
            이 쿼리의 경우, 조인이 실행하는데(묵시적 조인) 위의 쿼리 보다는
            query = em.createQuery("select t from Member m jpint m.team t", Team.class);
            아래의 쿼리로 작성하는것이 낫다. 왜냐하면 개발자가 조인을 예측을 할 수 있어야 하기 때문임

            tip. DISTINCT로 중복 제거 가능

            query = em.createQuery("select m.address from Member m", Member.class);//임베디드 타입 프로젝션 -> 임베디드를 조회한다

            query = em.createQuery("select m.username, m.age from Member m");//스칼라 타입 프로젝션 -> 스칼라를 조회한다
            반환타입이 명확하지 않기 때문에 TypedQuery가 아닌 Query로 가져옴
            Object[]로 받아서 TypedQuery로 사용해도 됨

            혹은 값을 받아오는 Dto를 생성하여 이렇게 사용 할 수 있음
            query = em.createQuery("select new jpql.MemberDto(m.username, m.age) from Member m", MemberDto.class);
             */

            ///////////////
            //
            ///////////////

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
