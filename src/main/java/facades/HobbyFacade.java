package facades;

import dto.HobbyInDTO;
import dto.HobbyOutDTO;
import entities.Hobby;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 */
public class HobbyFacade {

    private static HobbyFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private HobbyFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static HobbyFacade getHobbyFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Hobby getHobby(int hobbyID) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Hobby.class, hobbyID);
        } finally {
            em.close();
        }
    }

    public HobbyOutDTO addHobby(HobbyInDTO newHobby) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Hobby(newHobby.getName(), newHobby.getDescription()));
            em.getTransaction().commit();
            HobbyOutDTO hobOut = new HobbyOutDTO(newHobby.getName(), newHobby.getDescription());
            return hobOut;
        } finally {
            em.close();
        }
    }

    public HobbyOutDTO editHobby(HobbyInDTO hobbyWithChanges) {
        EntityManager em = emf.createEntityManager();
        try {
            Hobby hobbyToEdit = em.find(Hobby.class, hobbyWithChanges.getHobbyID());
            if (hobbyWithChanges.getName() != null && !hobbyWithChanges.getName().equals("")) { // .isEmpty() virkede ikke her
                hobbyToEdit.setName(hobbyWithChanges.getName());
            }
            if (hobbyWithChanges.getDescription() != null && !hobbyWithChanges.getDescription().equals("")) {
                hobbyToEdit.setDescription(hobbyWithChanges.getDescription());
            }

            em.getTransaction().begin();
            em.merge(hobbyToEdit);
            em.getTransaction().commit();
            HobbyOutDTO hobOut = new HobbyOutDTO(hobbyToEdit.getName(), hobbyToEdit.getDescription());
            return hobOut;
        } finally {
            em.close();
        }
    }

    public String deleteHobby(int hobbyID) {
        EntityManager em = emf.createEntityManager();
        try {
            Hobby hobbyToDelete = em.find(Hobby.class, hobbyID);

            em.getTransaction().begin();
            em.remove(hobbyToDelete);
            em.getTransaction().commit();
            return "{\"msg\": \"Hobby #" +hobbyID + " deleted!\"}";
        } finally {
            em.close();
        }
    }

    // Get all hobbies
    public List<Hobby> getHobbies() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Hobby> query = (TypedQuery<Hobby>) em.createQuery("SELECT h FROM Hobby h");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
