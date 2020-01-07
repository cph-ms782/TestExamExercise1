package facades;

import dto.PersonDTO;
import entities.Address;
import entities.Hobby;
import entities.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
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

    public PersonDTO getPersonByID(int personID) {
        EntityManager em = emf.createEntityManager();
        try {
            Person p = em.find(Person.class, personID);
            PersonDTO pDTO = new PersonDTO(p.getEmail(), p.getPhone(), p.getFirstName(), p.getLastName(), p.getAddress().getStreet(), p.getAddress().getZip());
            pDTO.setPersonID(p.getPersonID());
            return pDTO;
        } finally {
            em.close();
        }
    }

    public PersonDTO getPersonByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        PersonDTO pDTO = null;
        try {
            Query query = em.createNamedQuery("Person.findByEmail");
            query.setParameter("email", email);
            List<Person> result = query.getResultList();
            if (result.size() == 1) {
                Person p = result.get(0);
                pDTO = new PersonDTO(p.getPersonID(), p.getEmail(), p.getPhone(), p.getFirstName(), p.getLastName(), p.getAddress().getStreet(), p.getAddress().getZip());
            }
            return pDTO;
        } finally {
            em.close();
        }
    }

    public PersonDTO getPersonByPhone(String phone) {
        EntityManager em = emf.createEntityManager();
        PersonDTO pDTO = null;
        try {
            Query query = em.createNamedQuery("Person.findByPhone");
            query.setParameter("phone", phone);
            List<Person> result = query.getResultList();
            if (result.size() == 1) {
                Person p = result.get(0);
                pDTO = new PersonDTO(p.getPersonID(), p.getEmail(), p.getPhone(), p.getFirstName(), p.getLastName(), p.getAddress().getStreet(), p.getAddress().getZip());
            }
            return pDTO;
        } finally {
            em.close();
        }
    }

    public List<PersonDTO> getPersonsByHobbyName(String name) {
        EntityManager em = emf.createEntityManager();
        List<PersonDTO> persons = new ArrayList();
        try {
            Query query = em.createNamedQuery("Person.findByHobbyNames");
            query.setParameter("name", name);
            List<Person> result = query.getResultList();
            if (result.size() > 0) {
                for (Person p : result) {
                    persons.add(
                            new PersonDTO(
                                    p.getPersonID(),
                                    p.getEmail(),
                                    p.getPhone(),
                                    p.getFirstName(),
                                    p.getLastName(),
                                    p.getAddress().getStreet(),
                                    p.getAddress().getZip()
                            )
                    );
                }
            }
            return persons;
        } finally {
            em.close();
        }
    }

    /**
     * Read person data
     */
    /**
     * Create person data
     */
    public PersonDTO createPerson(String email, String phone, String firstName, String lastName, String street, String city, int zip) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Address a = new Address(street, city, zip);
            em.persist(a);
            Person p = new Person(email, phone, firstName, lastName, a);
            em.persist(p);
            em.getTransaction().commit();
            PersonDTO pDTO = new PersonDTO(p.getPersonID(), email, phone, firstName, lastName, street, zip);
            return pDTO;
        } finally {
            em.close();
        }
    }

    /**
     * Create person data
     */
    public PersonDTO changePerson(int personID, String email, String phone, String firstName, String lastName, String street, String city, int zip) {
        EntityManager em = emf.createEntityManager();
        try {
            Person changedPerson = em.find(Person.class, personID);
            changedPerson.setEmail(email);
            changedPerson.setPhone(phone);
            changedPerson.setFirstName(firstName);
            changedPerson.setLastName(lastName);
            changedPerson.setAddress(new Address(street, city, zip));
            em.getTransaction().begin();
            em.merge(changedPerson);
            em.getTransaction().commit();
//            Query query = em.createQuery(
//                    "UPDATE Person SET email = :email, phone = :phone, firstName = :firstName"
//                            + ", lastName = :lastName WHERE id = :id");
//            int updateCount = query
//                    .setParameter(email, email)
//                    .setParameter(phone, phone)
//                    .setParameter(firstName, firstName)
//                    .setParameter(lastName, lastName)
//                    .setParameter(personID, personID)
//                    .executeUpdate();
            PersonDTO pDTO = new PersonDTO(changedPerson.getPersonID(), email, phone, firstName, lastName, street, zip);
            return pDTO;
        } finally {
            em.close();
        }
    }

    /**
     * delete person data
     *
     * @param personID
     * @return
     */
    public boolean deletePerson(int personID) {
        boolean deleted = false;

        return deleted;
    }

    /**
     * CRUD HOBBY
     */
    /**
     * create hobby
     *
     * @param hobbyTitle
     * @param description
     * @return
     */
    public Hobby createHobby(String hobbyTitle, String description) {

        return new Hobby();
    }

    /**
     * change Hobby
     *
     * @param hobbyID
     * @return
     */
    public Hobby changeHobby(int hobbyID) {

        return new Hobby();
    }

    /**
     * delete hobby
     *
     * @param hobbyID
     * @return
     */
    public boolean deleteHobby(int hobbyID) {
        boolean deleted = false;

        return deleted;
    }

    /**
     * CRUD Addresses
     */
    /**
     * create address
     *
     * @param hobbyTitle
     * @param description
     * @return
     */
    public Address createAddress(String street, String zip, String city) {

        return new Address();
    }

    /**
     * change Address
     *
     * @param addressID
     * @return
     */
    public Address changeAddress(int addressID) {

        return new Address();
    }

    /**
     * delete Address
     *
     * @param addressID
     * @return
     */
    public boolean deleteAddress(int addressID) {
        boolean deleted = false;

        return deleted;
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
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return "{\"status\":\"emptied\"}";
    }

    /**
     * fill DB with persons
     *
     * @return
     */
    public String fillUp() {
        EntityManager em = emf.createEntityManager();
        Person p1, p2, p3;
        Hobby h1, h2, h3, h4;
        Address a1, a2;

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

        return "{\"status\":\"filled\"}";
    }

    public List<PersonDTO> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Person> query
                    = em.createQuery("Select p from Person p", Person.class);
            List<Person> persons = query.getResultList();
            List<PersonDTO> pDTOs = new ArrayList<>();
            for (Person person : persons) {
                PersonDTO pDTO = new PersonDTO(person.getEmail(), person.getPhone(), person.getFirstName(), person.getLastName(), person.getAddress().getStreet(), person.getAddress().getZip());
                pDTO.setPersonID(person.getPersonID());
                pDTOs.add(pDTO);

            }
            return pDTOs;
        } finally {
            em.close();
        }
    }

}
