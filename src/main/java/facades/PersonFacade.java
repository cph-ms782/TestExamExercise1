package facades;

import dto.HobbyOutDTO;
import dto.PersonInDTO;
import dto.PersonOutDTO;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 */
public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Person getPerson(int personID) {
        EntityManager em = emf.createEntityManager();
        try {
            Person p = em.find(Person.class, personID);
            return p;
        } finally {
            em.close();
        }
    }

    // Get information about a person (address, hobbies etc) given a phone number
    public List<Person> getPersonByPhoneNumber(String phoneNumber) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Person> query = (TypedQuery<Person>) em.createQuery("SELECT c FROM Person c JOIN c.phones p WHERE p.phoneNumber = :phoneNumber");
            query.setParameter("phoneNumber", phoneNumber);
            List<Person> results = query.getResultList();
            return results;
        } finally {
            em.close();
        }
    }

    // Get all persons with a given hobby
    public void getAllPersonsWithHobby(Hobby hobby) {

    }

    // Get all persons living in a given city (i.e. 2800 Lyngby)
    public List<Person> getAllPersonsWithZipCode(int zipcode) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Person> query
                    = em.createQuery("SELECT p from Person p JOIN p.address a JOIN a.cityInfo c WHERE c.zipCode = :zipCode", Person.class).setParameter("zipCode", zipcode);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public PersonOutDTO addPerson(PersonInDTO newPerson) {
        EntityManager em = emf.createEntityManager();
        Person person;

        try {
            person = new Person(newPerson.getEmail(), newPerson.getFirstName(),
                    newPerson.getLastName());

            if (newPerson.getStreet() != null && !newPerson.getStreet().isEmpty()) {
                em.getTransaction().begin();
                CityInfo cityInfo = new CityInfo(newPerson.getZipCode(), newPerson.getCity());
                em.persist(cityInfo);
                em.getTransaction().commit();
                em.getTransaction().begin();
                Address address = new Address(newPerson.getStreet(), newPerson.getAdditionalInfo(), cityInfo);
                em.persist(address);
                em.getTransaction().commit();
                person.setAddress(address);
            }

            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();

            PersonOutDTO personOUT = new PersonOutDTO(
                    person.getEmail(),
                    person.getFirstName(),
                    person.getLastName()
            );
            return personOUT;
        } finally {
            em.close();
        }
    }

    public String fillUp() {

        EntityManager em = emf.createEntityManager();

        Person p1, p2;
        Hobby h1, h2, h3;
        Address a1, a2;
        CityInfo c1, c2;
        Phone phone1, phone2, phone3;

        try {
            em.getTransaction().begin();
            c1 = new CityInfo(2100, "KBH Ø");
            c2 = new CityInfo(2300, "KBH S");
            em.persist(c1);
            em.persist(c2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            h1 = new Hobby("Cykling", "Cykling på hold");
            h2 = new Hobby("Film", "Gyserfilm");
            h3 = new Hobby("Film", "Dramafilm");
            em.persist(h1);
            em.persist(h2);
            em.persist(h3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            a1 = new Address("Testgade", "dejligt sted", c1);
            a2 = new Address("Testvej", "fint sted", c2);
            em.persist(a1);
            em.persist(a2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        em = emf.createEntityManager();

        try {
            p1 = new Person("email", "Gurli", "Mogensen", a1);
            p2 = new Person("mail", "Gunnar", "Hjorth", a2);
            em.getTransaction().begin();
            em.persist(p1);
            em.persist(p2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        em = emf.createEntityManager();

        try {
            phone1 = new Phone("1234", "hjemmetelefon", p1);
            phone2 = new Phone("5678", "mobil", p1);
            phone3 = new Phone("4321", "arbejdstelefon", p2);
            em.getTransaction().begin();
            em.persist(phone1);
            em.persist(phone2);
            em.persist(phone3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return "{\"status\":\"filled\"}";
    }

    /**
     * empty production database
     *
     * @return
     */
    public String emptyDB() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Phone.deleteAllRows").executeUpdate();
            em.createNamedQuery("CityInfo.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return "{\"status\":\"emptied\"}";
    }

}
