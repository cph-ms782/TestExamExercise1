package facades;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.MatchDTO;
import dto.StandingsDTO;
import dto.TeamDTO;
import dto.TeamMemberDTO;
import entities.ParserOfDates;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class ApiFacade {

    private static ApiFacade instance;
    private static List<TeamDTO> teamList = new ArrayList();
    private static List<StandingsDTO> standingsList = new ArrayList();
    private static List<TeamMemberDTO> teamMembersList = new ArrayList();
    private static Boolean isActivatedOnce = false;

    //Private Constructor to ensure Singleton
    private ApiFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static ApiFacade getApiFacade() {
        if (instance == null) {
            instance = new ApiFacade();
        }
        return instance;
    }

    private ParserOfDates getDates(String date) {
        String pattern = "(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2}):(\\d{2})Z";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(date);
        if (m.find()) {
            m.group(1);
            m.group(2);
            m.group(3);
            m.group(4);
            m.group(5);
        } else {
            System.out.println("NO MATCH");
        }
        return new ParserOfDates(m.group(1), m.group(2), m.group(3), m.group(4), m.group(5));
    }

    private String getFootballApi(String urlApi) throws MalformedURLException, IOException {
        URL url = new URL(urlApi);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("X-Auth-Token", "82e97af72ae34501ab126fc871128f61");
        String jsonStr;
        try (Scanner scan = new Scanner(con.getInputStream(), "UTF-8")) {
            jsonStr = null;
            if (scan.hasNext()) {
                jsonStr = scan.nextLine();
            }
        }
        return jsonStr;
    }

    public List<MatchDTO> getAllDataMatches(int id, Boolean isPlayed) throws IOException, InterruptedException, ExecutionException {

        List<String> URLS = new ArrayList();

        URLS.add("http://api.football-data.org/v2/teams/" + id + "/matches/");

        return getSeasonMatches(URLS, isPlayed);
    }

    public List<TeamDTO> getAllTeams() throws IOException, InterruptedException, ExecutionException {

        System.out.println("------------> getAllTeams");
        List<String> URLS = new ArrayList();
        URLS.add("http://api.football-data.org/v2/competitions/PL/teams?season=2019");
        System.out.println("isActivatedOnce" + isActivatedOnce);
        if (isActivatedOnce) {
            return teamList;
        }
        isActivatedOnce = true;
        return getAllTeamsData(URLS);
    }

    public List<MatchDTO> getSeasonMatches(List<String> URLS, Boolean isPlayed) throws ProtocolException, IOException, InterruptedException, ExecutionException {

        List<MatchDTO> seasonMatches = new ArrayList();
        Queue<Future<JsonObject>> queue = new ArrayBlockingQueue(URLS.size());

        ExecutorService workingJack = Executors.newCachedThreadPool();
        for (String url : URLS) {
            Future<JsonObject> future;
            future = workingJack.submit(() -> {
                JsonObject jsonObject = new JsonParser().parse(getFootballApi(url)).getAsJsonObject();
                return jsonObject;
            });
            queue.add(future);
        }
        while (!queue.isEmpty()) {
            Future<JsonObject> cpo = queue.poll();
            if (cpo.isDone()) {
                try {
                    System.out.println("inde i koden (getSeasonMatches)");
                    // CHANGE WHEN USING OTHER API
                    // USE OTHER DTO FOR WHAT YOU NEED TO EXTRACT
                    for (JsonElement match : cpo.get().get("matches").getAsJsonArray()) {
                        String status = match.getAsJsonObject().get("status").getAsString();
                        if (status != null) {
                            if (isPlayed && status.equals("FINISHED")) {
                                JsonObject elHomeTeam = (JsonObject) match.getAsJsonObject().get("homeTeam");
                                JsonObject elAwayTeam = (JsonObject) match.getAsJsonObject().get("awayTeam");
                                JsonObject elScore = (JsonObject) match.getAsJsonObject().get("score");
                                JsonObject elFullTime = (JsonObject) elScore.getAsJsonObject("fullTime");
                                seasonMatches.add(new MatchDTO(
                                        elHomeTeam.get("name").getAsString(),
                                        elFullTime.get("homeTeam").getAsString(),
                                        elAwayTeam.get("name").getAsString(),
                                        elFullTime.get("awayTeam").getAsString(),
                                        elScore.get("winner").getAsString(),
                                        getDates(match.getAsJsonObject().get("utcDate").getAsString()).toDate()
                                ));
                            } else if (!isPlayed && status.equals("SCHEDULED")) {
                                JsonObject elHomeTeam = (JsonObject) match.getAsJsonObject().get("homeTeam");
                                JsonObject elAwayTeam = (JsonObject) match.getAsJsonObject().get("awayTeam");
                                ParserOfDates pDate = getDates(match.getAsJsonObject().get("utcDate").getAsString());
                                String printDate = null;
                                if (pDate != null && pDate.getHour().equals("00")) {
                                    printDate = pDate.toDate();
                                } else {
                                    printDate = pDate.toString();
                                }
                                seasonMatches.add(new MatchDTO(
                                        elHomeTeam.get("name").getAsString(),
                                        "",
                                        elAwayTeam.get("name").getAsString(),
                                        "",
                                        "",
                                        printDate
                                ));
                            }
                        }
                    }
                } catch (NullPointerException ex) {
                    System.out.println("NullPointerException: " + ex);
                }
            } else {
                queue.add(cpo);
            }
        }
        workingJack.shutdown();
        for (int i = 0; i < seasonMatches.size(); i++) {
            System.out.println(seasonMatches.get(i));
        }
        return seasonMatches;
    }

    public List<TeamDTO> getAllTeamsData(List<String> URLS) throws ProtocolException, IOException, InterruptedException, ExecutionException {

        System.out.println("teamList size: " + teamList.size());
        if (teamList.size() == 0) {
            Queue<Future<JsonObject>> queue = new ArrayBlockingQueue(URLS.size());
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .serializeNulls()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();
            ExecutorService workingJack = Executors.newCachedThreadPool();
            for (String url : URLS) {
                Future<JsonObject> future;
                future = workingJack.submit(() -> {
                    JsonObject jsonObject = new JsonParser().parse(getFootballApi(url)).getAsJsonObject();
                    return jsonObject;
                });
                queue.add(future);
            }
            while (!queue.isEmpty()) {
                Future<JsonObject> cpo = queue.poll();
                if (cpo.isDone()) {
                    System.out.println("inde i getAllTeamsData");
                    for (JsonElement el : cpo.get().get("teams").getAsJsonArray()) {
                        teamList.add(new TeamDTO(
                                el.getAsJsonObject().get("name").getAsString(),
                                el.getAsJsonObject().get("crestUrl").getAsString(),
                                Integer.parseInt(el.getAsJsonObject().get("id").getAsString()),
                                el.getAsJsonObject().get("shortName").getAsString(),
                                el.getAsJsonObject().get("tla").getAsString(),
                                el.getAsJsonObject().get("address").getAsString(),
                                el.getAsJsonObject().get("website").getAsString(),
                                el.getAsJsonObject().get("clubColors").getAsString(),
                                el.getAsJsonObject().get("venue").getAsString()
                        ));
                    }
                } else {
                    queue.add(cpo);
                }
            }
            workingJack.shutdown();
            for (int i = 0; i < teamList.size(); i++) {
                System.out.println(teamList.get(i));
            }
        }
        System.out.println("results size: " + teamList.size());
        return teamList;
    }

    public List<StandingsDTO> getStandings() throws IOException, InterruptedException, ExecutionException {

        System.out.println("------------> getStandings");
        List<String> URLS = new ArrayList();
        URLS.add("http://api.football-data.org/v2/competitions/PL/standings");

        return getSeasonStandings(URLS);
    }

    public List<StandingsDTO> getSeasonStandings(List<String> URLS) throws ProtocolException, IOException, InterruptedException, ExecutionException {

        standingsList.clear();
        System.out.println("standingsList size: " + standingsList.size());
        if (standingsList.size() == 0) {

            Queue<Future<JsonObject>> queue = new ArrayBlockingQueue(URLS.size());
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .serializeNulls()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();
            ExecutorService workingJack = Executors.newCachedThreadPool();
            for (String url : URLS) {
                Future<JsonObject> future;
                future = workingJack.submit(() -> {
                    JsonObject jsonObject = new JsonParser().parse(getFootballApi(url)).getAsJsonObject();
                    return jsonObject;
                });
                queue.add(future);
            }
            while (!queue.isEmpty()) {
                Future<JsonObject> cpo = queue.poll();
                if (cpo.isDone()) {
                    try {
                        System.out.println("inde i getSeasonStandings");
                        // CHANGE WHEN USING OTHER API
                        // USE OTHER DTO FOR WHAT YOU NEED TO EXTRACT
                        JsonArray jArrayStandings = cpo.get().get("standings").getAsJsonArray();
                        JsonElement jArrayStandingsToTable = jArrayStandings.get(0);
                        JsonArray jArrayTable = (JsonArray) jArrayStandingsToTable.getAsJsonObject().get("table");

                        System.out.println("StandingsDTO følger");
                        for (JsonElement teamStanding : jArrayTable) {
                            System.out.println("team == " + teamStanding.getAsJsonObject().get("team").toString());
                            System.out.println("teamID ======= " + teamStanding.getAsJsonObject().get("team").getAsJsonObject().get("id").toString());
                            standingsList.add(new StandingsDTO(
                                    teamStanding.getAsJsonObject().get("position").getAsString(),
                                    teamStanding.getAsJsonObject().get("team").getAsJsonObject().get("id").getAsString(),
                                    teamStanding.getAsJsonObject().get("playedGames").getAsString(),
                                    teamStanding.getAsJsonObject().get("won").getAsString(),
                                    teamStanding.getAsJsonObject().get("draw").getAsString(),
                                    teamStanding.getAsJsonObject().get("lost").getAsString(),
                                    teamStanding.getAsJsonObject().get("points").getAsString(),
                                    teamStanding.getAsJsonObject().get("goalsFor").getAsString(),
                                    teamStanding.getAsJsonObject().get("goalsAgainst").getAsString(),
                                    teamStanding.getAsJsonObject().get("goalDifference").getAsString(),
                                    teamStanding.getAsJsonObject().get("team").getAsJsonObject().get("crestUrl").getAsString()
                            ));

                        }
                    } catch (NullPointerException ex) {
                        System.out.println("NullPointerException: " + ex);
                    }
                } else {
                    queue.add(cpo);
                }
            }
            workingJack.shutdown();
            for (int i = 0; i < standingsList.size(); i++) {
                System.out.println(standingsList.get(i));
            }
        }
        System.out.println("standingsList size: " + standingsList.size());
        return standingsList;
    }

    public List<TeamMemberDTO> getTeamMembers(int id) throws IOException, InterruptedException, ExecutionException {

        List<String> URLS = new ArrayList();

        URLS.add("http://api.football-data.org/v2/teams/" + id + "");

        return getAllTeamMembers(URLS);
    }

    public List<TeamMemberDTO> getAllTeamMembers(List<String> URLS) throws ProtocolException, IOException, InterruptedException, ExecutionException {

        teamMembersList.clear();
        System.out.println("teamMembersList size: " + teamMembersList.size());
        if (teamMembersList.size() == 0) {
            Queue<Future<JsonObject>> queue = new ArrayBlockingQueue(URLS.size());
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .serializeNulls()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();
            ExecutorService workingJack = Executors.newCachedThreadPool();
            for (String url : URLS) {
                Future<JsonObject> future;
                future = workingJack.submit(() -> {
                    JsonObject jsonObject = new JsonParser().parse(getFootballApi(url)).getAsJsonObject();
                    return jsonObject;
                });
                queue.add(future);
            }
            while (!queue.isEmpty()) {
                Future<JsonObject> cpo = queue.poll();
                if (cpo.isDone()) {
                    try {
                        System.out.println("inde i getAllTeamMembers");
                        // CHANGE WHEN USING OTHER API
                        // USE OTHER DTO FOR WHAT YOU NEED TO EXTRACT
                        JsonArray jArrayTeamMembers = cpo.get().get("squad").getAsJsonArray();

                        System.out.println("TeamMemberDTO følger");
                        for (JsonElement teamMember : jArrayTeamMembers) {
                            String pos = teamMember.getAsJsonObject().get("role").getAsString();

                            if (pos.equals("PLAYER")) {
                                teamMembersList.add(new TeamMemberDTO(
                                        teamMember.getAsJsonObject().get("name").getAsString(),
                                        teamMember.getAsJsonObject().get("position").getAsString(),
                                        getDates(teamMember.getAsJsonObject().get("dateOfBirth").getAsString()).toDate(),
                                        teamMember.getAsJsonObject().get("countryOfBirth").getAsString(),
                                        teamMember.getAsJsonObject().get("nationality").getAsString(),
                                        teamMember.getAsJsonObject().get("role").getAsString()
                                ));
                            } else {
                                teamMembersList.add(new TeamMemberDTO(
                                        teamMember.getAsJsonObject().get("name").getAsString(),
                                        getDates(teamMember.getAsJsonObject().get("dateOfBirth").getAsString()).toDate(),
                                        teamMember.getAsJsonObject().get("countryOfBirth").getAsString(),
                                        teamMember.getAsJsonObject().get("nationality").getAsString(),
                                        teamMember.getAsJsonObject().get("role").getAsString()
                                ));
                            }
                        }
                    } catch (NullPointerException ex) {
                        System.out.println("NullPointerException: " + ex);
                    }
                } else {
                    queue.add(cpo);
                }
            }
            workingJack.shutdown();
            for (int i = 0; i < teamMembersList.size(); i++) {
                System.out.println(teamMembersList.get(i));
            }
        }
        System.out.println("teamMembersList size: " + teamMembersList.size());
        return teamMembersList;
    }
    
}
