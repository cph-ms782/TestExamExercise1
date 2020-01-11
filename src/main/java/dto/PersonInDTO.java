package dto;

import entities.Hobby;
import entities.Phone;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(name = "PersonInfo")
public class PersonInDTO {

    private int personID;

    @Schema(required = true, example = "info@simonskodebiks.dk")
    private String email;

    @Schema(required = true, example = "Gũnther")
    private String firstName;

    @Schema(required = true, example = "Steiner")
    private String lastName;

    private Integer addressID;
    private String street;
    private String additionalInfo;
    private int zipCode;
    private String city;

    private List<Integer> hobbyIDs;

    private List<Integer> phoneIDs;

    public PersonInDTO() {
    }

    public PersonInDTO(int personID, String email, String firstName, String lastName) {
        this.personID = personID;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonInDTO(int personID, String email, String firstName, String lastName, String street, String additionalInfo, int zipCode, String city) {
        this.personID = personID;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.zipCode = zipCode;
        this.city = city;
    }

    public PersonInDTO(
            String email,
            String firstName,
            String lastName,
            Integer addressID,
            List<Integer> hobbyIDs,
            List<Integer> phoneIDs
    ) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressID = addressID;
        this.hobbyIDs = hobbyIDs;
        this.phoneIDs = phoneIDs;
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

    public Integer getAddressID() {
        return addressID;
    }

    public void setAddressID(Integer addressID) {
        this.addressID = addressID;
    }

    public List<Integer> getHobbyIDs() {
        return hobbyIDs;
    }

    public void setHobbyIDs(List<Integer> hobbyIDs) {
        this.hobbyIDs = hobbyIDs;
    }

    public List<Integer> getPhoneIDs() {
        return phoneIDs;
    }

    public void setPhoneIDs(List<Integer> phoneIDs) {
        this.phoneIDs = phoneIDs;
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
