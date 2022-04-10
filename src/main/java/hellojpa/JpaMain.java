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
            Item item = new Item();
            item.setName("item1");
            em.persist(item);

            Member member = new Member();
            member.setUsername("TEST1");
            em.persist(member);

            Order order = new Order();
            order.setMember(member);
            em.persist(order);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setItem(item);
            orderItem.setCount(1);
            orderItem.setOrderPrice(1000);
            em.persist(orderItem);

            em.flush();
            em.clear();

            OrderItem orderItem1 = em.find(OrderItem.class, orderItem.getId());
//            System.out.println("orderItem1.toString() = " + orderItem1.toString());
            System.out.println("=============");
            System.out.println("orderItem1.order = " + orderItem1.getOrder());
            System.out.println("=============");
            System.out.println("orderItem1.item = " + orderItem1.getItem());

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
