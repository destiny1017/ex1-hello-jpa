package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member1 = new Member();
            member1.setUserName("member1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUserName("member2");
            em.persist(member2);

            em.flush();

            Member m1 = em.find(Member.class, member1.getId());
            Member m2 = em.find(Member.class, member1.getId());
            System.out.println("(m1 == m2) = " + (m1 == m2));
            m1.setUserName("member1.5");
            System.out.println("(m1 == m2) = " + (m1 == m2));
            
            em.flush();
            
            Member m3 = em.find(Member.class, member1.getId());
            System.out.println("(m2 == m3) = " + (m2 == m3));

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
