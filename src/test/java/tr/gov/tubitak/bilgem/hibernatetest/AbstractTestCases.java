package tr.gov.tubitak.bilgem.hibernatetest;

import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tr.gov.tubitak.bilgem.hibernatetest.em.Person;
import tr.gov.tubitak.bilgem.hibernatetest.em.PersonEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Kemal Beyaz
 * @date 5/24/2022
 */
public abstract class AbstractTestCases {

    private static final List<Person> SAMPLE_PERSON_LIST = new ArrayList<>();
    static EntityManagerFactory emf;

    static {
        SAMPLE_PERSON_LIST.add(new Person("Ahmet Yilmaz", 1850));
        SAMPLE_PERSON_LIST.add(new Person("Ayse Akdeniz", 150));
        SAMPLE_PERSON_LIST.add(new Person("Mehmet Bozkurt", 150));
        SAMPLE_PERSON_LIST.add(new Person("Ali Kilic", 170));
        SAMPLE_PERSON_LIST.add(new Person("Veli Atli", 1500));
    }

    @Test
    void shouldSaveSamplePersonList() {
        var personEntityManager = new PersonEntityManager(emf);
        personEntityManager.save(SAMPLE_PERSON_LIST.toArray(Person[]::new));
        assertTrue(true);
    }

    @Test
    void shouldFetchAllPerson() {
        var personEntityManager = new PersonEntityManager(emf);
        List<Person> people = personEntityManager.getAllPersons();
        Assertions.assertEquals(SAMPLE_PERSON_LIST.size(), people.size());
    }

    @Test
    void shouldFetchFilteredPerson() {
        var personEntityManager = new PersonEntityManager(emf);
        List<Person> filteredPersons = personEntityManager.getFilteredPersons(0, 3);
        Assertions.assertEquals(3, filteredPersons.size());
    }
}
