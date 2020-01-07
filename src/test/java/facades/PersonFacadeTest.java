package facades;

import dto.PersonDTO;
import entities.Address;
import entities.Hobby;
import entities.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import utils.EMF_Creator;

/**
 *
 * @author martin
 */
public class PersonFacadeTest {

    static PersonFacade facade;
    static EntityManagerFactory emf;
    Person p1, p2, p3;
    Hobby h1, h2, h3, h4;
    Address a1, a2;

    public PersonFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = PersonFacade.getPersonFacade(emf);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
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

    @After
    public void tearDown() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Test of getPersonFacade method, of class PersonFacade.
     */
    @Test
    public void testGetPersonFacade() {
        System.out.println("getPersonFacade");
        EntityManagerFactory _emf = null;
        PersonFacade result = PersonFacade.getPersonFacade(_emf);
        assertNotNull(result);
    }

    /**
     * Test of getPersonByEmail method, of class PersonFacade.
     */
    @Test
    public void testGetPersonByEmail() {
        System.out.println("getPersonByEmail");
        String email = "email@email.com";
        PersonFacade instance = facade;
        PersonDTO result = instance.getPersonByEmail(email);
        PersonDTO expResult = new PersonDTO(result.getPersonID(), "email@email.com", "+454444444",
                "Gurli", "Mogensen", "Testgade", 2000);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPersonByID method, of class PersonFacade.
     */
    @Test
    public void testGetPersonByID() {
        System.out.println("getPersonByID");
        PersonDTO person = facade.getPersonByEmail("email@email.com");
        Integer personID = person.getPersonID();
        PersonDTO expResult = new PersonDTO(personID, "email@email.com", "+454444444",
                "Gurli", "Mogensen", "Testgade", 2000);
        PersonDTO result = facade.getPersonByID(personID);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPersonByPhone method, of class PersonFacade.
     */
    @Test
    public void testGetPersonByPhone() {
        System.out.println("getPersonByPhone");
        String phone = "+454444444";
        PersonFacade instance = facade;
        PersonDTO result = instance.getPersonByPhone(phone);
        PersonDTO expResult = new PersonDTO(result.getPersonID(), "email@email.com", "+454444444",
                "Gurli", "Mogensen", "Testgade", 2000);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPersonsByHobby method, of class PersonFacade.
     */
    @Test
    public void testGetPersonsByHobbyName() {
        System.out.println("getPersonsByHobbyName");
        String hobby = "Skydiving";
        PersonFacade instance = facade;
        List<Integer> expResult = new ArrayList();
        List<Integer> result = new ArrayList();
        expResult.add(p2.getPersonID());
        expResult.add(p1.getPersonID());
        instance.getPersonsByHobbyName(hobby).forEach((personDTO) -> {
            result.add(personDTO.getPersonID());
        });
        assertThat("List equality without order", 
            expResult, containsInAnyOrder(result.toArray()));
    }

    /**
     * Test of createPerson method, of class PersonFacade.
     */
    @Test
    public void testCreatePerson() {
        System.out.println("createPerson");
        String email = "jdhfkj@fdjkgh.dk";
        String phone = "+4588888888";
        String firstName = "Børge";
        String lastName = "Knudsen";
        String street = "Hansevej 7";
        String city = "Roskilde";
        int zip = 4000;
        PersonFacade instance = facade;
        PersonDTO result = instance.createPerson(email, phone, firstName, lastName, street, city, zip);
        PersonDTO expResult = new PersonDTO(result.getPersonID(), email, phone, firstName, lastName, street, zip);
        assertEquals(expResult, result);
    }

    /**
     * Test of changePerson method, of class PersonFacade.
     */
    @Test
    public void testChangePerson() {
        System.out.println("changePerson");
        String email = "jdhfgks@dfh.com";
        String phone = "+4577777777";
        String firstName = "Gurli";
        String lastName = "Mogensen";
        String street = "";
        String city = "";
        int zip = 0;
        PersonFacade instance = facade;
        PersonDTO result = instance.getPersonByPhone("+454444444");
        result = instance.changePerson(result.getPersonID(), email, phone, firstName, lastName, street, city, zip);
        PersonDTO expResult = new PersonDTO(result.getPersonID(), email, phone, firstName, lastName, street, zip);
        assertEquals(expResult, result);
    }

    /**
     * Test of deletePerson method, of class PersonFacade.
     */
//    @Test
    public void testDeletePerson() {
        System.out.println("deletePerson");
        int personID = 0;
        PersonFacade instance = null;
        boolean expResult = false;
        boolean result = instance.deletePerson(personID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createHobby method, of class PersonFacade.
     */
//    @Test
    public void testCreateHobby() {
        System.out.println("createHobby");
        String hobbyTitle = "";
        String description = "";
        PersonFacade instance = null;
        Hobby expResult = null;
        Hobby result = instance.createHobby(hobbyTitle, description);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeHobby method, of class PersonFacade.
     */
//    @Test
    public void testChangeHobby() {
        System.out.println("changeHobby");
        int hobbyID = 0;
        PersonFacade instance = null;
        Hobby expResult = null;
        Hobby result = instance.changeHobby(hobbyID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteHobby method, of class PersonFacade.
     */
//    @Test
    public void testDeleteHobby() {
        System.out.println("deleteHobby");
        int hobbyID = 0;
        PersonFacade instance = null;
        boolean expResult = false;
        boolean result = instance.deleteHobby(hobbyID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createAddress method, of class PersonFacade.
     */
//    @Test
    public void testCreateAddress() {
        System.out.println("createAddress");
        String street = "";
        String zip = "";
        String city = "";
        PersonFacade instance = null;
        Address expResult = null;
        Address result = instance.createAddress(street, zip, city);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeAddress method, of class PersonFacade.
     */
//    @Test
    public void testChangeAddress() {
        System.out.println("changeAddress");
        int addressID = 0;
        PersonFacade instance = null;
        Address expResult = null;
        Address result = instance.changeAddress(addressID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAddress method, of class PersonFacade.
     */
//    @Test
    public void testDeleteAddress() {
        System.out.println("deleteAddress");
        int addressID = 0;
        PersonFacade instance = null;
        boolean expResult = false;
        boolean result = instance.deleteAddress(addressID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of emptyDB method, of class PersonFacade.
     */
//    @Test
    public void testEmptyDB() {
        System.out.println("emptyDB");
        PersonFacade instance = null;
        String expResult = "";
        String result = ""; //instance.emptyDB();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fillUp method, of class PersonFacade.
     */
//    @Test
    public void testFillUp() {
        System.out.println("fillUp");
        PersonFacade instance = null;
        String expResult = "";
        String result = instance.fillUp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllPersons method, of class PersonFacade.
     */
//    @Test
    public void testGetAllPersons() {
        System.out.println("getAllPersons");
        PersonFacade instance = null;
        List<PersonDTO> expResult = null;
        List<PersonDTO> result = instance.getAllPersons();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
