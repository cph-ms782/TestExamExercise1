package rest;

import dto.PersonDTO;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;

/**
 */
@Path("person")
public class PersonResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    PersonFacade facade;
    
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\": \"Hello person\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("fill")
    public String fillDB() {
        facade = PersonFacade.getPersonFacade(EMF);
        facade.fillUp();
        return "{\"msg\": \"DB filled\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("empty")
    public String emptyDB() {
        facade = PersonFacade.getPersonFacade(EMF);
        facade.emptyDB();
        return "{\"msg\": \"DB emptied\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("id/{personID}")
    public PersonDTO findByID(@PathParam("personID") int personID) {
        facade = PersonFacade.getPersonFacade(EMF);
        return facade.getPersonByID(personID);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("phone/{phone}")
    public PersonDTO findByPhone(@PathParam("phone") String phone) {
        facade = PersonFacade.getPersonFacade(EMF);
        return facade.getPersonByPhone(phone);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("email/{email}")
    public PersonDTO findByEmail(@PathParam("email") String email) {
        facade = PersonFacade.getPersonFacade(EMF);
        return facade.getPersonByEmail(email);
    }


    
    
    
    
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("{city}")
//    public List<MatchesDTO> getMatches(@PathParam("city") String city) {
//        List<MatchesDTO> matches = new ArrayList<>();
//        java.sql.Time sqlTime = new Time(new Date().getTime());
//        switch (city) {
//            case "Liverpool":
//                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 2),
//                        sqlTime.toLocalTime(),
//                        "Liverpool FC",
//                        "Arsenal FC",
//                        "Anfield Road"));
//                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 3),
//                        sqlTime.toLocalTime(),
//                        "Liverpool FC",
//                        "Burnley FC",
//                        "Anfield Road"));
//                break;
//
//            case "Manchester":
//                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 21),
//                        sqlTime.toLocalTime(),
//                        "Manchester FC",
//                        "Burnley FC",
//                        "Manchester Road"));
//                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 23),
//                        sqlTime.toLocalTime(),
//                        "Manchester FC",
//                        "Arsenal FC",
//                        "Manchester Road"));
//                break;
//
//            case "Birmingham":
//                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 10),
//                        sqlTime.toLocalTime(),
//                        "Birmingham FC",
//                        "Burnley FC",
//                        "Birmingham Road"));
//                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 12),
//                        sqlTime.toLocalTime(),
//                        "Birmingham FC",
//                        "Arsenal FC",
//                        "Birmingham Road"));
//                break;
//
//            case "Southampton":
//                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 3),
//                        sqlTime.toLocalTime(),
//                        "Southampton FC",
//                        "Burnley FC",
//                        "Southampton Road"));
//                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 13),
//                        sqlTime.toLocalTime(),
//                        "Southampton FC",
//                        "Arsenal FC",
//                        "Southampton Road"));
//                break;
//
//            default:
//                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 2),
//                        sqlTime.toLocalTime(),
//                        "Leeds FC",
//                        "Burnley FC",
//                        "Leeds Road"));
//                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 23),
//                        sqlTime.toLocalTime(),
//                        "Leeds FC",
//                        "Arsenal FC",
//                        "Leeds Road"));
//                break;
//
//        }
//
//        return matches;
//    }

}
