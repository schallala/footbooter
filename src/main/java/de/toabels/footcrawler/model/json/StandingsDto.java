/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.toabels.footcrawler.model.json;

/**
 *
 * @author abels
 */
public class StandingsDto {
    private final int rank;
    private final String teamName;
    private final int matches;
    private final int won;
    private final int draw;
    private final int lost;
    private final String  goals;
    private final int diff;
    private final int points;    

    public StandingsDto(int rank, String teamName, int matches, int won, int draw, int lost, String goals, int diff, int points) {
        this.rank = rank;
        this.teamName = teamName;
        this.matches = matches;
        this.won = won;
        this.draw = draw;
        this.lost = lost;
        this.goals = goals;
        this.diff = diff;
        this.points = points;
    }

    public int getRank() {
        return rank;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getMatches() {
        return matches;
    }

    public int getWon() {
        return won;
    }

    public int getDraw() {
        return draw;
    }

    public int getLost() {
        return lost;
    }

    public String getGoals() {
        return goals;
    }

    public int getDiff() {
        return diff;
    }

    public int getPoints() {
        return points;
    }

    
}
