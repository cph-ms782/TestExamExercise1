package rest;

import dto.HobbyInDTO;
import dto.HobbyOutDTO;
import dto.PersonOutDTO;
import entities.Hobby;
import facades.HobbyFacade;
import utils.EMF_Creator;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

@OpenAPIDefinition(
        info = @Info(
                title = "Hobby API",
                version = "0.1",
                description = "API to get info about hobbies.",
                contact = @Contact(name = "Gũnther Steiner", email = "info@simonskodebiks.dk")
        ),
        tags = {
            @Tag(name = "hobby", description = "API related to Hobby Info")

        },
        servers = {
            @Server(
                    description = "For Local host testing",
                    url = "http://localhost:8080/testexamexercise1"
            ),
            @Server(
                    description = "Server API",
                    url = "https://sandersolutions.com/testexamexercise1/"
            )

        }
)

@Path("hobby")
public class HobbyResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);

    private static final HobbyFacade FACADE = HobbyFacade.getHobbyFacade(EMF);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello Hobby\"}";
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Hobby info by ID",
            tags = {"hobby"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonOutDTO.class))),
                @ApiResponse(responseCode = "200", description = "The Requested hobby"),
                @ApiResponse(responseCode = "403", description = "Not authenticated - do login"),
                @ApiResponse(responseCode = "404", description = "Person not found")})
    public PersonOutDTO getHobbyInfo(@PathParam("id") int personID) {
        if (personID > 0 && personID == 99999) {
            // for test
            return new PersonOutDTO("info@simonskodebiks.dk", "Gũnther", "Steiner");
        } else {
            // here should be something real :-)
            return new PersonOutDTO("info@simonskodebiks.dk", "Gũnther", "Steiner");
        }
    }

    @PUT
    @Path("edit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit existing hobby", tags = {"hobby"},
            responses = {
                @ApiResponse(responseCode = "200", description = "The edited person"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public HobbyOutDTO editPerson(HobbyInDTO hobbyWithChanges) {
        if (hobbyWithChanges.getHobbyID()== 0 ) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        return FACADE.editHobby(hobbyWithChanges);
    }

    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new hobby to a person", tags = {"hobby"},
            responses = {
                @ApiResponse(responseCode = "200", description = "Person with added hobby"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public HobbyOutDTO addHobby(HobbyInDTO newHobby) {
        if (newHobby.getName()== null || newHobby.getDescription()== null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        
        return FACADE.addHobby(newHobby);
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete Hobby ",
            tags = {"hobby"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonOutDTO.class))),
                @ApiResponse(responseCode = "200", description = "The Requested hobby"),
                @ApiResponse(responseCode = "403", description = "Not authenticated - do login"),
                @ApiResponse(responseCode = "404", description = "Person not found")})
    public String deleteHobby(@PathParam("id") int hobbyID) {
            return FACADE.deleteHobby(hobbyID);
    }

//    Get all hobbies
    @GET
    @Path("hobbies")
//    @RolesAllowed({"user", "admin"})
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all hobbies",
            tags = {"person"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonOutDTO.class))),
                @ApiResponse(responseCode = "200", description = "The Requested hobbies"),
                @ApiResponse(responseCode = "403", description = "Not authenticated - do login"),
                @ApiResponse(responseCode = "404", description = "Hobbies not found")})
    public List<Hobby> getAllHobbies() {
        List<HobbyOutDTO> p = new ArrayList();
        return FACADE.getHobbies();
    }

}
