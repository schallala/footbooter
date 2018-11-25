/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.toabels.footcrawler.model.db;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author abels
 */
@Entity
@Table(name = "competition_team")
@XmlRootElement
public class CompetitionTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "competition_id")
    private Long competitionId;

    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "matches_played")
    private String matchesPlayed;

    @Column(name = "rank")
    private String rank;

    @Column(name = "points")
    private String points;

    @Column(name = "goals_made")
    private String goalsMade;

    @Column(name = "goals_conceeded")
    private String goalsConceeded;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(String matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getGoalsMade() {
        return goalsMade;
    }

    public void setGoalsMade(String goalsMade) {
        this.goalsMade = goalsMade;
    }

    public String getGoalsConceeded() {
        return goalsConceeded;
    }

    public void setGoalsConceeded(String goalsConceeded) {
        this.goalsConceeded = goalsConceeded;
    }

}
