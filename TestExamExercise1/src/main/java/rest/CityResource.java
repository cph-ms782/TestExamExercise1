package rest;

import dto.CitiesDTO;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;

/**
 */
@Path("city")
public class CityResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello city\"}";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("cities")
    public List<CitiesDTO> getCities() {
        
        List<CitiesDTO> cities = new ArrayList<>();
        cities.add(new CitiesDTO("Liverpool"));
        cities.add(new CitiesDTO("Manchester"));
        cities.add(new CitiesDTO("Birmingham"));
        cities.add(new CitiesDTO("Southampton"));
        cities.add(new CitiesDTO("Leeds"));
        
        return cities;
    }
    
    

}
