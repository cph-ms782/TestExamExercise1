package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author martin
 */
public class AirportsDTO {

    private List<String> airports = new ArrayList();

    public AirportsDTO() {
    }

    public AirportsDTO(List<String> airports) {
        this.airports = airports;
    }

    public List<String> getAirports() {
        return airports;
    }

    public void setAirports(List<String> airports) {
        this.airports = airports;
    }

    @Override
    public String toString() {
        return "AirportsDTO{" + "airports=" + airports + '}';
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
        final AirportsDTO other = (AirportsDTO) obj;
        if (!Objects.equals(this.airports, other.airports)) {
            return false;
        }
        return true;
    }

    
}
