package dto;

import entities.Person;
import io.swagger.v3.oas.annotations.media.Schema;

public class PersonDTO {
    @Schema(required = true, example = "1")
    private int personID;
    
    @Schema(required = true, example = "info@sandersolutions.dk")
    private String email;
    
    @Schema(required = true, example = "+4528154687")
    private String phone;
    
    @Schema(required = true, example = "Gũnther")
    private String firstName;
    
    @Schema(required = true, example = "Steiner")
    private String lastName;
    
    @Schema(required = true, example = "Jacobsvej")
    private String street;
    
    @Schema(required = true, example = "1234")
    private int zip;

    public PersonDTO() {
    }

    public PersonDTO(Person person) {
        firstName = person.getFirstName();
        lastName = person.getLastName();
        email = person.getEmail();
    }

    public PersonDTO(String email, String firstName, String lastName, String street, int zip) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.zip = zip;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "PersonDTO{" + "personID=" + personID + ", email=" + email + ", phone=" + phone + ", firstName=" + firstName + ", lastName=" + lastName + ", street=" + street + ", zip=" + zip + '}';
    }

    
}
