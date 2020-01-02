package facades;

import dto.MatchDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 */
public class AirportFacade {

    private static AirportFacade instance;

    private AirportFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static AirportFacade getAirportFacade() {
        if (instance == null) {
            instance = new AirportFacade();
        }
        return instance;
    }

    public List<String> getAllAirports() {
        List<String> airports = new ArrayList();
        airports.add("London Heathrow Airport-LHR");
        airports.add("London Gatwick Airport-LGW");
        airports.add("London Luton Airport-LTN");
        airports.add("London Stansted Airport-STN");
        airports.add("Manchester Airport-MAN");
        return airports;
    }

    public List<String> getNearestTeamfromAirport(String airport) {
        List<String> teams = new ArrayList();
        switch (airport) {

            case "LGW":
                teams.add("Brighton & Hove Albion FC");
                teams.add("Crystal Palace FC");
                teams.add("Tottenham Hotspur FC");
                teams.add("West Ham United FC");
                break;

            case "LHR":
                teams.add("AFC Bournemouth");
                teams.add("Arsenal FC");
                teams.add("Chelsea FC");
                teams.add("Southampton FC");
                teams.add("Watford FC");
                break;

            case "STN":
                teams.add("Norwich City FC");
                break;

            case "MAN":
                teams.add("Aston Villa FC");
                teams.add("Burnley FC");
                teams.add("Everton FC");
                teams.add("Liverpool FC");
                teams.add("Manchester City FC");
                teams.add("Manchester United FC");
                teams.add("Newcastle United FC");
                teams.add("Sheffield United FC");
                teams.add("Wolverhampton Wanderers FC");
                break;

            case "LTN":
                teams.add("Leicester City FC");
                break;

            default:
                break;
        }
        return teams;
    }

    public String getNearestAirportFromTeam(String team) {
        String airport = null;
        switch (team) {
            case "AFC Bournemouth":
                airport = "London Heathrow Airport-LHR";
                break;

            case "Arsenal FC":
                airport = "London Heathrow Airport-LHR";
                break;

            case "Aston Villa FC":
                airport = "Manchester Airport-MAN";
                break;

            case "Brighton & Hove Albion FC ":
                airport = "London Gatwick Airport-LGW";
                break;

            case "Burnley FC":
                airport = "Manchester Airport-MAN";
                break;

            case "Chelsea FC":
                airport = "London Heathrow Airport-LHR";
                break;

            case "Crystal Palace FC":
                airport = "London Gatwick Airport-LGW";
                break;

            case "Everton FC":
                airport = "Manchester Airport-MAN";
                break;

            case "Leicester City FC":
                airport = "London Luton Airport-LTN";
                break;

            case "Liverpool FC":
                airport = "Manchester Airport-MAN";
                break;

            case "Manchester City FC":
                airport = "Manchester Airport-MAN";
                break;

            case "Manchester United FC":
                airport = "Manchester Airport-MAN";
                break;

            case "Newcastle United FC":
                airport = "Manchester Airport-MAN";
                break;

            case "Norwich City FC":
                airport = "London Stansted Airport-STN";
                break;

            case "Sheffield United FC":
                airport = "Manchester Airport-MAN";
                break;

            case "Southampton FC":
                airport = "London Heathrow Airport-LHR";
                break;

            case "Tottenham Hotspur FC":
                airport = "London Gatwick Airport-LGW";
                break;

            case "Watford FC":
                airport = "London Heathrow Airport-LHR";
                break;

            case "West Ham United FC":
                airport = "London Gatwick Airport-LGW";
                break;

            case "Wolverhampton Wanderers FC":
                airport = "Manchester Airport-MAN";
                break;

            default:
                airport = "City not found";
                break;

        }
        return airport;
    }

    public List<MatchDTO> getAllMatchDatesNearAirport(String airportCode, String fromDate, String toDate) throws IOException, InterruptedException, ExecutionException {
        ApiFacade facade = ApiFacade.getApiFacade();

        List<MatchDTO> results = new ArrayList();
        List<String> teams = getNearestTeamfromAirport(airportCode);
        List<String> URLS = new ArrayList();
        URLS.add("http://api.football-data.org/v2/matches?competitions=PL&dateFrom=" + fromDate + "&dateTo=" + toDate);
        List<MatchDTO> matches = facade.getSeasonMatches(URLS, false);

        for (MatchDTO match : matches) {
            for (String team : teams) {
                if (match.getHomeCity().equals(team)) {
                    results.add(match);
                }
            }
        }

        facade = null;
        System.out.println("getAllMatchDatesNearAirport - results near airport ("+airportCode+"): "+ results);
        return results;
    }
}
