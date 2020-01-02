package rest;

import dto.MatchDTO;
import dto.MatchesDTO;
import java.sql.Time;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@Path("match")
public class MatchResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello match\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{city}")
    public List<MatchesDTO> getMatches(@PathParam("city") String city) {
        List<MatchesDTO> matches = new ArrayList<>();
        java.sql.Time sqlTime = new Time(new Date().getTime());
        switch (city) {
            case "Liverpool":
                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 2),
                        sqlTime.toLocalTime(),
                        "Liverpool FC",
                        "Arsenal FC",
                        "Anfield Road"));
                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 3),
                        sqlTime.toLocalTime(),
                        "Liverpool FC",
                        "Burnley FC",
                        "Anfield Road"));
                break;

            case "Manchester":
                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 21),
                        sqlTime.toLocalTime(),
                        "Manchester FC",
                        "Burnley FC",
                        "Manchester Road"));
                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 23),
                        sqlTime.toLocalTime(),
                        "Manchester FC",
                        "Arsenal FC",
                        "Manchester Road"));
                break;

            case "Birmingham":
                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 10),
                        sqlTime.toLocalTime(),
                        "Birmingham FC",
                        "Burnley FC",
                        "Birmingham Road"));
                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 12),
                        sqlTime.toLocalTime(),
                        "Birmingham FC",
                        "Arsenal FC",
                        "Birmingham Road"));
                break;

            case "Southampton":
                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 3),
                        sqlTime.toLocalTime(),
                        "Southampton FC",
                        "Burnley FC",
                        "Southampton Road"));
                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 13),
                        sqlTime.toLocalTime(),
                        "Southampton FC",
                        "Arsenal FC",
                        "Southampton Road"));
                break;

            default:
                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 2),
                        sqlTime.toLocalTime(),
                        "Leeds FC",
                        "Burnley FC",
                        "Leeds Road"));
                matches.add(new MatchesDTO(LocalDate.of(2019, Month.DECEMBER, 23),
                        sqlTime.toLocalTime(),
                        "Leeds FC",
                        "Arsenal FC",
                        "Leeds Road"));
                break;

        }

        return matches;
    }

}
