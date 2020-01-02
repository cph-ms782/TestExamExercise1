package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author martin
 */
public class TeamAirportDTO {

    private List<String> teams = new ArrayList();

    public TeamAirportDTO() {
    }

    public TeamAirportDTO(List<String> team) {
        this.teams = team;
    }

    public List<String> getTeams() {
        return teams;
    }

    public void setTeams(List<String> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return "TeamAirportDTO{" + "team=" + teams + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final TeamAirportDTO other = (TeamAirportDTO) obj;
        if (!Objects.equals(this.teams, other.teams)) {
            return false;
        }
        return true;
    }

    
}
