package dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "HobbyInfo")
public class HobbyInDTO {

    private int hobbyID;
    
    @Schema(required = true, example = "Films (Horror)")
    private String name;
    
    @Schema(required = true, example = "Films filled with horror")
    private String description;

    public HobbyInDTO() {
    }

    public HobbyInDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public HobbyInDTO(int hobbyID, String name, String description) {
        this.hobbyID = hobbyID;
        this.name = name;
        this.description = description;
    }

    public int getHobbyID() {
        return hobbyID;
    }

    public void setHobbyID(int hobbyID) {
        this.hobbyID = hobbyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
