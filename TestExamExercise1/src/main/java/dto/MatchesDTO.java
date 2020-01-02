package dto;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author martin
 */
public class MatchesDTO {
    
    private LocalDate date; 
    private LocalTime time; 
    private String homeCity; 
    private String awayCity; 
    private String stadium; 

    public MatchesDTO() {
    }

    public MatchesDTO(LocalDate date, LocalTime time, String homeCity, String awayCity, String stadium) {
        this.date = date;
        this.time = time;
        this.homeCity = homeCity;
        this.awayCity = awayCity;
        this.stadium = stadium;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MatchesDTO{date=").append(date);
        sb.append(", time=").append(time);
        sb.append(", homeCity=").append(homeCity);
        sb.append(", awayCity=").append(awayCity);
        sb.append(", stadium=").append(stadium);
        sb.append('}');
        return sb.toString();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public String getAwayCity() {
        return awayCity;
    }

    public void setAwayCity(String awayCity) {
        this.awayCity = awayCity;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.date);
        hash = 53 * hash + Objects.hashCode(this.time);
        hash = 53 * hash + Objects.hashCode(this.homeCity);
        hash = 53 * hash + Objects.hashCode(this.awayCity);
        hash = 53 * hash + Objects.hashCode(this.stadium);
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
        final MatchesDTO other = (MatchesDTO) obj;
        if (!Objects.equals(this.homeCity, other.homeCity)) {
            return false;
        }
        if (!Objects.equals(this.awayCity, other.awayCity)) {
            return false;
        }
        if (!Objects.equals(this.stadium, other.stadium)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return true;
    }



}
