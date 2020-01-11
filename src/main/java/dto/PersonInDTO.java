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
