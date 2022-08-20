package tr.gov.tubitak.bilgem.hibernatetest;

import jakarta.persistence.Persistence;
import tr.gov.tubitak.bilgem.hibernatetest.em.Person;
import tr.gov.tubitak.bilgem.hibernatetest.em.PersonEntityManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kemal Beyaz
 * @date 5/24/2022
 */
public class Main {

    private static final Map<String, String> persistenceOptions;

    static {
        persistenceOptions = new HashMap<>();
        persistenceOptions.put("hibernate.connection.url", "jdbc:postgresql://0.0.0.0:5432/postgres");
        persistenceOptions.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        persistenceOptions.put("hibernate.connection.username", "postgres");
        persistenceOptions.put("hibernate.connection.password", "mysupersecretpassword");
        persistenceOptions.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        persistenceOptions.put("hibernate.hbm2ddl.auto", "create-drop");
    }

    public static void main(String[] args) {
        var entityManagerFactory =
                Persistence.createEntityManagerFactory("person-persistence", persistenceOptions);
        var personEntityManager = new PersonEntityManager(entityManagerFactory);

        var person1 = new Person("Ahmet Yılmaz", 1850);
        var person2 = new Person("Mehmet Akdeniz", 150);
        var person4 = new Person("Ali Kılıç", 170);
        var person3 = new Person("Veli Altı", 1500);
        personEntityManager.save(person1, person2, person3, person4);
        System.out.println("Saved!");

        List<Person> filteredPersons = personEntityManager.getFilteredPersons(0, 3);
        System.out.println("Count: " + filteredPersons.size());
        filteredPersons.forEach(p -> System.out.println(p.getName()));
    }
}
