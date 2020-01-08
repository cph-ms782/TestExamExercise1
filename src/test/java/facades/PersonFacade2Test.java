package facades;

import entities.Address;
import entities.Hobby;
import entities.Person;
import utils.EMF_Creator;
import static facades.PersonFacadeTest.emf;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonFacade2Test {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    private Person p1, p2, p3;
    private Hobby h1, h2, h3, h4;
    private Address a1, a2;

    public PersonFacade2Test() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = PersonFacade.getPersonFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            a1 = new Address("Testgade", "Frederiksberg", 2000);
            a2 = new Address("Testvej", "Lyngby", 2800);
            em.persist(a1);
            em.persist(a2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        em = emf.createEntityManager();
        try {
            h1 = new Hobby("Aquarium", "All kinds of fishs");
            h2 = new Hobby("Skydiving", "Getting some fresh air");
            h3 = new Hobby("Film (Dramafilm)", "Drama film");
            h4 = new Hobby("Film (Gyserfilm)", "Uhyggelige film");

            p1 = new Person("email@email.com", "+454444444", "Gurli", "Mogensen", a1);
            p1.addHobby(h4);
            p1.addHobby(h2);
            p2 = new Person("mail@mail.com", "+453333333", "Gunnar", "Hjorth", a2);
            p2.addHobby(h1);
            p2.addHobby(h2);
            p3 = new Person("mail@mail.com", "+422222222", "Hansine", "Hjorth", a2);
            p3.addHobby(h3);
            em.getTransaction().begin();
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getPersonFacade method, of class PersonFacade.
     */
    @org.junit.Test
    public void testGetPersonFacade() {
        System.out.println("getPersonFacade");
        EntityManagerFactory _emf = null;
        PersonFacade result = PersonFacade.getPersonFacade(_emf);
        assertNotNull(result);
    }


}
