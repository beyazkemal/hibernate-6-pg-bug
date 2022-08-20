package tr.gov.tubitak.bilgem.hibernatetest;

import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeAll;

/**
 * @author Kemal Beyaz
 * @date 5/24/2022
 */
class TestCasesWithPG extends AbstractTestCases {

    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("person-persistence-pg");
    }

}