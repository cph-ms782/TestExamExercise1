package rest;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import dto.AirportsDTO;
import dto.MatchDTO;
import dto.TeamAirportDTO;
import facades.AirportFacade;
import java.io.IOException;
import java.net.ProtocolException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 */
@Path("air")
public class AirportResource {

    AirportFacade facade = AirportFacade.getAirportFacade();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello airport\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("airports")
    public AirportsDTO getAllAirports() throws IOException, ProtocolException, ExecutionException, InterruptedException {
        List<String> data = facade.getAllAirports();
        return new AirportsDTO(data);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("nearestairport/{team}")
    public String getNearestAirport(@PathParam("team") String team) throws IOException, ProtocolException, ExecutionException, InterruptedException {
        String data = facade.getNearestAirportFromTeam(team);
        return "{\"airport\":\"" + data + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("nearestteam/{airportcode}")
    public TeamAirportDTO getNearestTeam(@PathParam("airportcode") String airportcode) throws IOException, ProtocolException, ExecutionException, InterruptedException {
        List<String> data = facade.getNearestTeamfromAirport(airportcode);
        return new TeamAirportDTO(data);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("nearestmatch/{airportcode}")
    public List<MatchDTO> getNearestMatch(@PathParam("airportcode") String airportcode) throws IOException, ProtocolException, ExecutionException, InterruptedException {

        // diff may not exceed 10 days
        LocalDate date = LocalDate.now(); // Create a date object
        LocalDate newDate = date.plusDays(10);
        String fromDate = date.toString();
        String toDate = newDate.toString();
        System.out.println("airportcode: " + airportcode);
        System.out.println("fromDate: " + fromDate);
        System.out.println("toDate: " + toDate);
        List<MatchDTO> data = facade.getAllMatchDatesNearAirport(airportcode, fromDate, toDate);
        return data;
    }
}
