/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Objects;

/**
 *
 * @author Mkhansen;
 */
public class TeamMemberDTO {
    
    private String name;
    private String position;
    private String dateOfBirth;
    private String countryOfBirth;
    private String nationality;
    private String role;

    public TeamMemberDTO() {
    }

    public TeamMemberDTO(String name, String dateOfBirth, String countryOfBirth, String nationality, String role) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.countryOfBirth = countryOfBirth;
        this.nationality = nationality;
        this.role = role;
    }

    public TeamMemberDTO(String name, String position, String dateOfBirth, String countryOfBirth, String nationality, String role) {
        this.name = name;
        this.position = position;
        this.dateOfBirth = dateOfBirth;
        this.countryOfBirth = countryOfBirth;
        this.nationality = nationality;
        this.role = role;
    }

    @Override
    public String toString() {
        return "TeamMemberDTO{" + "name=" + name + ", position=" + position + ", dateOfBirth=" + dateOfBirth + ", countryOfBirth=" + countryOfBirth + ", nationality=" + nationality + ", role=" + role + '}';
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + Objects.hashCode(this.position);
        hash = 17 * hash + Objects.hashCode(this.dateOfBirth);
        hash = 17 * hash + Objects.hashCode(this.countryOfBirth);
        hash = 17 * hash + Objects.hashCode(this.nationality);
        hash = 17 * hash + Objects.hashCode(this.role);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TeamMemberDTO other = (TeamMemberDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.position, other.position)) {
            return false;
        }
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
            return false;
        }
        if (!Objects.equals(this.countryOfBirth, other.countryOfBirth)) {
            return false;
        }
        if (!Objects.equals(this.nationality, other.nationality)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        return true;
    }
    
}
