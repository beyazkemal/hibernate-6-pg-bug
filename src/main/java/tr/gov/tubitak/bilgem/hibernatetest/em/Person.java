package tr.gov.tubitak.bilgem.hibernatetest.em;

import jakarta.persistence.*;

/**
 * @author Kemal Beyaz
 * @date 5/24/2022
 */
@Entity
@Table(name = "Person", indexes = @Index(columnList = "timex"))
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private long timex;

    public Person() {
    }

    public Person(String name, long timex) {
        this.name = name;
        this.timex = timex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimex() {
        return timex;
    }

    public void setTimex(long timex) {
        this.timex = timex;
    }
}
