/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.toabels.footcrawler.model.db;

import java.util.Date;
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
@Table(name = "competition_match")
@XmlRootElement
public class CompetitionMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "competition_id")
    private Long competitionId;

    @Column(name = "team1_id")
    private Long teamOneId;

    @Column(name = "team2_id")
    private Long teamTwoId;

    @Column(name = "kickoff_datetime")
    private Date kickoffDatetime;

    @Column(name = "state")
    private String matchState;

    @Column(name = "match_link")
    private String matchLink;

    @Column(name = "goals_team1_ht")
    private Integer goalTeamOneHalftime;

    @Column(name = "goals_team1_ft")
    private Integer goalTeamOneFulltime;

    @Column(name = "goals_team1_ot")
    private Integer goalTeamOneOvertime;

    @Column(name = "goals_team1_final")
    private Integer goalTeamOneFinal;

    @Column(name = "goals_team2_ht")
    private Integer goalTeamTwoHalftime;

    @Column(name = "goals_team2_ft")
    private Integer goalTeamTwoFulltime;

    @Column(name = "goals_team2_ot")
    private Integer goalTeamTwoOvertime;

    @Column(name = "goals_team2_final")
    private Integer goalTeamTwoFinal;

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

    public Long getTeamOneId() {
        return teamOneId;
    }

    public void setTeamOneId(Long teamOneId) {
        this.teamOneId = teamOneId;
    }

    public Long getTeamTwoId() {
        return teamTwoId;
    }

    public void setTeamTwoId(Long teamTwoId) {
        this.teamTwoId = teamTwoId;
    }

    public Date getKickoffDatetime() {
        return kickoffDatetime;
    }

    public void setKickoffDatetime(Date kickoffDatetime) {
        this.kickoffDatetime = kickoffDatetime;
    }

    public String getMatchState() {
        return matchState;
    }

    public void setMatchState(String matchState) {
        this.matchState = matchState;
    }

    public String getMatchLink() {
        return matchLink;
    }

    public void setMatchLink(String matchLink) {
        this.matchLink = matchLink;
    }

    public Integer getGoalTeamOneHalftime() {
        return goalTeamOneHalftime;
    }

    public void setGoalTeamOneHalftime(Integer goalTeamOneHalftime) {
        this.goalTeamOneHalftime = goalTeamOneHalftime;
    }

    public Integer getGoalTeamOneFulltime() {
        return goalTeamOneFulltime;
    }

    public void setGoalTeamOneFulltime(Integer goalTeamOneFulltime) {
        this.goalTeamOneFulltime = goalTeamOneFulltime;
    }

    public Integer getGoalTeamOneOvertime() {
        return goalTeamOneOvertime;
    }

    public void setGoalTeamOneOvertime(Integer goalTeamOneOvertime) {
        this.goalTeamOneOvertime = goalTeamOneOvertime;
    }

    public Integer getGoalTeamOneFinal() {
        return goalTeamOneFinal;
    }

    public void setGoalTeamOneFinal(Integer goalTeamOneFinal) {
        this.goalTeamOneFinal = goalTeamOneFinal;
    }

    public Integer getGoalTeamTwoHalftime() {
        return goalTeamTwoHalftime;
    }

    public void setGoalTeamTwoHalftime(Integer goalTeamTwoHalftime) {
        this.goalTeamTwoHalftime = goalTeamTwoHalftime;
    }

    public Integer getGoalTeamTwoFulltime() {
        return goalTeamTwoFulltime;
    }

    public void setGoalTeamTwoFulltime(Integer goalTeamTwoFulltime) {
        this.goalTeamTwoFulltime = goalTeamTwoFulltime;
    }

    public Integer getGoalTeamTwoOvertime() {
        return goalTeamTwoOvertime;
    }

    public void setGoalTeamTwoOvertime(Integer goalTeamTwoOvertime) {
        this.goalTeamTwoOvertime = goalTeamTwoOvertime;
    }

    public Integer getGoalTeamTwoFinal() {
        return goalTeamTwoFinal;
    }

    public void setGoalTeamTwoFinal(Integer goalTeamTwoFinal) {
        this.goalTeamTwoFinal = goalTeamTwoFinal;
    }
    
}
