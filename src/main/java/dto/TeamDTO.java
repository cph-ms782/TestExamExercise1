package dto;

import java.util.Objects;

/**
 *
 * @author simon, martin
 */
public class TeamDTO {

    private String name;
    private String crestUrl;
    private Integer teamID;
    private String shortName;
    private String tla;
    private String address;
//    private String phone;
    private String website;
//    private String email;
//    private String founded;
    private String clubColors;
    private String venue;

    public TeamDTO() {
    }

    public TeamDTO(String name, String crestUrl, Integer teamID) {
        this.name = name;
        this.crestUrl = crestUrl;
        this.teamID = teamID;
    }

    public TeamDTO(String name, String crestUrl, Integer teamID, String shortName, String tla, String address, String website, String clubColors, String venue) {
        this.name = name;
        this.crestUrl = crestUrl;
        this.teamID = teamID;
        this.shortName = shortName;
        this.tla = tla;
        this.address = address;
        this.website = website;
        this.clubColors = clubColors;
        this.venue = venue;
    }

    
    
//    public TeamDTO(String name, String crestUrl, Integer teamID, String shortName, String tla, String address, String phone, String website, String email, String founded, String clubColors, String venue) {
//        this.name = name;
//        this.crestUrl = crestUrl;
//        this.teamID = teamID;
//        this.shortName = shortName;
//        this.tla = tla;
//        this.address = address;
//        this.phone = phone;
//        this.website = website;
//        this.email = email;
//        this.founded = founded;
//        this.clubColors = clubColors;
//        this.venue = venue;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

    public Integer getTeamID() {
        return teamID;
    }

    public void setTeamID(Integer teamID) {
        this.teamID = teamID;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTla() {
        return tla;
    }

    public void setTla(String tla) {
        this.tla = tla;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getFounded() {
//        return founded;
//    }
//
//    public void setFounded(String founded) {
//        this.founded = founded;
//    }

    public String getClubColors() {
        return clubColors;
    }

    public void setClubColors(String clubColors) {
        this.clubColors = clubColors;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.crestUrl);
        hash = 89 * hash + Objects.hashCode(this.teamID);
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
        final TeamDTO other = (TeamDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.crestUrl, other.crestUrl)) {
            return false;
        }
        if (!Objects.equals(this.shortName, other.shortName)) {
            return false;
        }
        if (!Objects.equals(this.tla, other.tla)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
//        if (!Objects.equals(this.phone, other.phone)) {
//            return false;
//        }
//        if (!Objects.equals(this.website, other.website)) {
//            return false;
//        }
//        if (!Objects.equals(this.email, other.email)) {
//            return false;
//        }
//        if (!Objects.equals(this.founded, other.founded)) {
//            return false;
//        }
        if (!Objects.equals(this.clubColors, other.clubColors)) {
            return false;
        }
        if (!Objects.equals(this.venue, other.venue)) {
            return false;
        }
        if (!Objects.equals(this.teamID, other.teamID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TeamDTO{name=").append(name);
        sb.append(", crestUrl=").append(crestUrl);
        sb.append(", teamID=").append(teamID);
        sb.append(", shortName=").append(shortName);
        sb.append(", tla=").append(tla);
        sb.append(", address=").append(address);
        sb.append(", website=").append(website);
        sb.append(", clubColors=").append(clubColors);
        sb.append(", venue=").append(venue);
        sb.append('}');
        return sb.toString();
    }

   

}
