package rest;

import dto.MatchDTO;
import dto.MatchesDTO;
import dto.StandingsDTO;
import dto.TeamDTO;
import dto.TeamMemberDTO;
import facades.ApiFacade;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 */
@Path("fb")
public class FootballResource {

    ApiFacade facade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello football\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("teams")
    public List<TeamDTO> getTeams() throws IOException, ProtocolException, ExecutionException, InterruptedException {
        facade = ApiFacade.getApiFacade();
        List<TeamDTO> data = facade.getAllTeams();
        facade = null;
        return data;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("allteammatchdates/{id}")
    public List<MatchDTO> getMatchDates(@PathParam("id") int id) throws IOException, ProtocolException, ExecutionException, InterruptedException {
        facade = ApiFacade.getApiFacade();
        List<MatchDTO> data = facade.getAllDataMatches(id, false);
        facade = null;
        return data;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("allteammatchresults/{id}")
    public List<MatchDTO> getMatchResults(@PathParam("id") int id) throws IOException, ProtocolException, ExecutionException, InterruptedException {
        facade = ApiFacade.getApiFacade();
        List<MatchDTO> data = facade.getAllDataMatches(id, true);
        facade = null;
        return data;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("standings")
    public List<StandingsDTO> getStandings() throws IOException, ProtocolException, ExecutionException, InterruptedException {
        facade = ApiFacade.getApiFacade();
        List<StandingsDTO> data = facade.getStandings();
        facade = null;
        return data;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    @Path("teammembers/{id}")
    public List<TeamMemberDTO> getTeamMembers(@PathParam("id") int id) throws IOException, ProtocolException, ExecutionException, InterruptedException {
        facade = ApiFacade.getApiFacade();
        List<TeamMemberDTO> data = facade.getTeamMembers(id);
        return data;
    }

}
