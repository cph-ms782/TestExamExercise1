/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Mkhansen;
 */
public class StandingsDTO {
    
    private String position;
    private String teamID;
    private String playedGames;
    private String won;
    private String draw;
    private String lost;
    private String points;
    private String goalsFor;
    private String goalsAgainst;
    private String goalDifference;
    private String crestUrl;

    public StandingsDTO() {
    }

    public StandingsDTO(String position, String teamID, String playedGames, String won, String draw, String lost, String points, String goalsFor, String goalsAgainst, String goalDifference, String crestUrl) {
        this.position = position;
        this.teamID = teamID;
        this.playedGames = playedGames;
        this.won = won;
        this.draw = draw;
        this.lost = lost;
        this.points = points;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.goalDifference = goalDifference;
        this.crestUrl = crestUrl;
    }

    @Override
    public String toString() {
        return "StandingsDTO{" + "position=" + position + ", teamID=" + teamID + ", playedGames=" + playedGames + ", won=" + won + ", draw=" + draw + ", lost=" + lost + ", points=" + points + ", goalsFor=" + goalsFor + ", goalsAgainst=" + goalsAgainst + ", goalDifference=" + goalDifference + ", crestUrl=" + crestUrl + '}';
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
    
    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public String getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(String playedGames) {
        this.playedGames = playedGames;
    }

    public String getWon() {
        return won;
    }

    public void setWon(String won) {
        this.won = won;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public String getLost() {
        return lost;
    }

    public void setLost(String lost) {
        this.lost = lost;
    }

    public String getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(String goalsFor) {
        this.goalsFor = goalsFor;
    }

    public String getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(String goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public String getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(String goalDifference) {
        this.goalDifference = goalDifference;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.position);
        hash = 37 * hash + Objects.hashCode(this.teamID);
        hash = 37 * hash + Objects.hashCode(this.playedGames);
        hash = 37 * hash + Objects.hashCode(this.won);
        hash = 37 * hash + Objects.hashCode(this.draw);
        hash = 37 * hash + Objects.hashCode(this.lost);
        hash = 37 * hash + Objects.hashCode(this.points);
        hash = 37 * hash + Objects.hashCode(this.goalsFor);
        hash = 37 * hash + Objects.hashCode(this.goalsAgainst);
        hash = 37 * hash + Objects.hashCode(this.goalDifference);
        hash = 37 * hash + Objects.hashCode(this.crestUrl);
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
        final StandingsDTO other = (StandingsDTO) obj;
        if (!Objects.equals(this.position, other.position)) {
            return false;
        }
        if (!Objects.equals(this.teamID, other.teamID)) {
            return false;
        }
        if (!Objects.equals(this.playedGames, other.playedGames)) {
            return false;
        }
        if (!Objects.equals(this.won, other.won)) {
            return false;
        }
        if (!Objects.equals(this.draw, other.draw)) {
            return false;
        }
        if (!Objects.equals(this.lost, other.lost)) {
            return false;
        }
        if (!Objects.equals(this.points, other.points)) {
            return false;
        }
        if (!Objects.equals(this.goalsFor, other.goalsFor)) {
            return false;
        }
        if (!Objects.equals(this.goalsAgainst, other.goalsAgainst)) {
            return false;
        }
        if (!Objects.equals(this.goalDifference, other.goalDifference)) {
            return false;
        }
        if (!Objects.equals(this.crestUrl, other.crestUrl)) {
            return false;
        }
        return true;
    }

}
