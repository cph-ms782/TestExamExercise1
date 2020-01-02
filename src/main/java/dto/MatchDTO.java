/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Objects;

/**
 *
 * @author simon
 */
public class MatchDTO {

    /**
     *
     * @author simon
     */
    private String homeCity;
    private String homeScore;
    private String awayCity;
    private String awayScore;
    private String winner;
    private String utcDate;

    public MatchDTO() {
    }

    public MatchDTO(String homeCity, String homeScore, String awayCity, String awayScore, String winner, String utcDate) {
        this.homeCity = homeCity;
        this.homeScore = homeScore;
        this.awayCity = awayCity;
        this.awayScore = awayScore;
        this.winner = winner;
        this.utcDate = utcDate;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public String getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(String homeScore) {
        this.homeScore = homeScore;
    }

    public String getAwayCity() {
        return awayCity;
    }

    public void setAwayCity(String awayCity) {
        this.awayCity = awayCity;
    }

    public String getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(String awayScore) {
        this.awayScore = awayScore;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getUtcDate() {
        return utcDate;
    }

    public void setUtcDate(String utcDate) {
        this.utcDate = utcDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.homeCity);
        hash = 61 * hash + Objects.hashCode(this.homeScore);
        hash = 61 * hash + Objects.hashCode(this.awayCity);
        hash = 61 * hash + Objects.hashCode(this.awayScore);
        hash = 61 * hash + Objects.hashCode(this.winner);
        hash = 61 * hash + Objects.hashCode(this.utcDate);
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
        final MatchDTO other = (MatchDTO) obj;
        if (!Objects.equals(this.homeCity, other.homeCity)) {
            return false;
        }
        if (!Objects.equals(this.homeScore, other.homeScore)) {
            return false;
        }
        if (!Objects.equals(this.awayCity, other.awayCity)) {
            return false;
        }
        if (!Objects.equals(this.awayScore, other.awayScore)) {
            return false;
        }
        if (!Objects.equals(this.winner, other.winner)) {
            return false;
        }
        if (!Objects.equals(this.utcDate, other.utcDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MatchDTO{" + "homeCity=" + homeCity + ", homeScore=" + homeScore + ", awayCity=" + awayCity + ", awayScore=" + awayScore + ", winner=" + winner + ", utcDate=" + utcDate + '}';
    }

}
