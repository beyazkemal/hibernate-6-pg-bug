package tr.gov.tubitak.bilgem.hibernatetest.em;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kemal Beyaz
 * @date 5/24/2022
 */
public class PersonEntityManager {

    private final EntityManagerFactory emf;

    public PersonEntityManager(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Person... persons) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();

            for (Person person : persons) {
                em.persist(person);
            }
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }

    public List<Person> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();

            CriteriaQuery<Person> query = cb.createQuery(Person.class);
            Root<Person> from = query.from(Person.class);
            List<Order> orders = new ArrayList<>();

            CriteriaQuery<Person> criteriaQuery = query.select(from).orderBy(orders);
            TypedQuery<Person> typedQuery = em.createQuery(criteriaQuery);
            List<Person> resultList = typedQuery.getResultList();

            tx.commit();
            return resultList;
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Person> getFilteredPersons(int firstResultOffset, int maxResultCount) {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();

            CriteriaQuery<Person> query = cb.createQuery(Person.class);
            Root<Person> from = query.from(Person.class);
            List<Order> orders = new ArrayList<>(
            );

            CriteriaQuery<Person> criteriaQuery = query.select(from).orderBy(orders);
            TypedQuery<Person> typedQuery = em.createQuery(criteriaQuery);
            typedQuery.setFirstResult(firstResultOffset);
            typedQuery.setMaxResults(maxResultCount);
            List<Person> resultList = typedQuery.getResultList();

            tx.commit();
            return resultList;
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void deleteAll() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();


            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }
}
