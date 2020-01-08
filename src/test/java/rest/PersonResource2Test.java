package rest;

import entities.Address;
import entities.Hobby;
import entities.Person;
import facades.PersonFacade;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//@Disabled
public class PersonResource2Test {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static PersonFacade facade;
    private EntityManager em;

    private Person p1, p2, p3;
    private Hobby h1, h2, h3, h4;
    private Address a1, a2;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.CREATE);

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @BeforeEach
    public void setUp() {
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
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/person").then().statusCode(200);
    }

    @Test
    public void testDummyMsg() throws Exception {
        given()
                .contentType("application/json")
                .get("/person/").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("msg", equalTo("Hello person"));
    }

    /**
     * Test of getPersonInfo method, of class PersonResource.
     */
    //@Test
    public void testGetPersonInfo() {
        System.out.println("getPersonInfo");
        given()
                .contentType("application/json")
                .get("/person/99999").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("email", equalTo("info@simonskodebiks.dk"))
                .body("firstName", equalTo("Gũnther"))
                .body("lastName", equalTo("Steiner"));
    }

    /**
     * Test of getPersonInfoByPhoneNumber method, of class PersonResource.
     */
//    @Test
    public void testGetPersonInfoByPhoneNumber() {
        System.out.println("getPersonInfoByPhoneNumber");
        given()
                .contentType("application/json")
                .get("/person/phone/1111").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("email", equalTo("info@simonskodebiks.dk"))
                .body("firstName", equalTo("Gũnther"))
                .body("lastName", equalTo("Steiner"))
                .body("hobbies.name", hasItems("Cykling", "Film"));
    }

    /**
     * Test of getCountPersonByHobby method, of class PersonResource.
     */
//    @Test
    public void testGetCountPersonByHobby() {
        System.out.println("getCountPersonByHobby");
        PersonResource instance = new PersonResource();
//        instance.getCountPersonByHobby();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllZipCodes method, of class PersonResource.
     */
    //@Test
    public void testGetAllZipCodes() {
        given()
                .contentType("application/json")
                .get("/person/zipcode/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("city", hasItems("ABC", "DEF"))
                .body("zipCode", hasItems(1234, 5678));
    }
    
//    @Test
    public void testGetAllPersons() {
        given()
                .contentType("application/json")
                .get("/person/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("firstName", hasItems("Gurli", "Gunnar"))
                .body("zipcode", hasItems(2100, 2300));
    }

}
