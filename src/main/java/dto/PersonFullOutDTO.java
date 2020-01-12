package dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "PersonInfo")
public class PersonFullOutDTO {

    private int personID;

    @Schema(required = true, example = "info@simonskodebiks.dk")
    private String email;

    @Schema(required = true, example = "Gũnther")
    private String firstName;

    @Schema(required = true, example = "Steiner")
    private String lastName;

    private String street;
    private String additionalInfo;
    private int zipCode;
    private String city;

    public PersonFullOutDTO() {
    }

    public PersonFullOutDTO(int personID, String email, String firstName, String lastName, String street, String additionalInfo, int zipCode, String city) {
        this.personID = personID;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

}
