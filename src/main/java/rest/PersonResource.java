package rest;

import dto.PersonFullOutDTO;
import dto.PersonInDTO;
import dto.PersonOutDTO;
import utils.EMF_Creator;
import facades.PersonFacade;
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
import java.util.concurrent.ExecutorService;
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
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
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

@Path("person")
public class PersonResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);

    private static final PersonFacade FACADE = PersonFacade.getPersonFacade(EMF);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello Person\"}";
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Person info by ID",
            tags = {"person"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonOutDTO.class))),
                @ApiResponse(responseCode = "200", description = "The Requested Person"),
                @ApiResponse(responseCode = "404", description = "Person not found")})

    public PersonOutDTO getPersonInfo(@PathParam("id") int personID) {
        if (personID > 0 && personID == 99999) {
            // for test
            return new PersonOutDTO("info@simonskodebiks.dk", "Gũnther", "Steiner");
        } else {
            // here should be something real :-)
            return new PersonOutDTO("info@simonskodebiks.dk", "Gũnther", "Steiner");
        }
    }

    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PersonOutDTO addPerson(PersonInDTO newPerson) {
        if (newPerson.getFirstName()== null || newPerson.getLastName()== null ||
                newPerson.getEmail()== null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        
        return FACADE.addPerson(newPerson);
    }

    @PUT
    @Path("edit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PersonOutDTO editPerson(PersonInDTO personWithChanges) {
        if (personWithChanges.getPersonID()== 0 ) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        return FACADE.editPerson(personWithChanges);
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteHobby(@PathParam("id") int personID) {
        if (personID== 0 ) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
            return FACADE.deletePerson(personID);
    }

    
    
//    Get all persons with a given hobby
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all Persons info",
            tags = {"person"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonOutDTO.class))),
                @ApiResponse(responseCode = "200", description = "The Requested Person"),
                @ApiResponse(responseCode = "404", description = "Persons not found")})

    public List<PersonOutDTO> getAllPersonsInfoByHobby() {
        List<PersonOutDTO> p = new ArrayList();
        p.add(new PersonOutDTO("info@simonskodebiks.dk", "Gũnther", "Steiner"));
        p.add(new PersonOutDTO("kontakt@simonskodebiks.dk", "Osvaldo", "Ardiles"));
        return p;
    }

    //    Get all persons with a given hobby
    @GET
    @Path("fill")
    @Produces(MediaType.APPLICATION_JSON)
    public String fillDB() {
        FACADE.fillUp();
        return "{\"msg\": \"DB filled\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("empty")
    public String emptyDB() {
        FACADE.emptyDB();
        return "{\"msg\": \"DB emptied\"}";
    }
    private ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();

    @GET
    @Path(value = "persons")
    @Produces(value = MediaType.APPLICATION_JSON)
    public void getAllPersons(@Suspended final AsyncResponse asyncResponse) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                asyncResponse.resume(doGetAllPersons());
            }
        });
    }

    private List<PersonFullOutDTO> doGetAllPersons() {
        List<PersonFullOutDTO> list = FACADE.getPersons();
        return list;
    }

}
