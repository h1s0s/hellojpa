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
public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //JPQL 문법

            //////////
            //쿼리 요청
            //////////
            //대소문자는 java 필드, 클래스에 맞게 사용해야 함
            //나머지 문법은 대소문자 구분하지 않음
            //테이블명이 아닌 엔티티 이름사용, 별칭 필수

            //TypedQuery : 반환타입이 명확할 때 사용
            //Query: 반환타입이 명확하지 않을 때 사용
            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
            List<Member> query1List = query1.getResultList();//리스트로 반환 받을때 : 결과가 없으면 빈 리스트를 반환(NullPointException 걱정 X)
            Member query1One = query1.getSingleResult();//객체 1개로 반환 받을때 : 결과가 정확히 하나여야함 결과가 없으면 NoResultException, 둘 이상히면 NonUniqueResultException

            Query query2 = em.createQuery("select m.username, m.age from Member m");

            //주의, 조회하는 시점부터 영속성 컨테이너에서 관리함

            ///////////////
            //파라미터 바인딩
            ///////////////
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            Member singleResult = em.createQuery("select m from Member m where m.username = :username", Member.class)
                                    .setParameter("username", "member1")//":키값" 에 값 바인딩
                                    .getSingleResult();
            System.out.println("singleResult = " + singleResult);

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
