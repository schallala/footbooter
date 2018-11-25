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
public class MatchDto {

    private final String teamOne;
    private final String teamTwo;
    private final String kickoffTime;
    private final String kickoffDate;
    private final String matchState;
    private final String matchLink;
    private final String finalResult;

    public MatchDto(String teamOne, String teamTwo, String kickoffTime, String kickoffDate, String matchState,
        String matchLink, String finalResult) {
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.kickoffTime = kickoffTime;
        this.kickoffDate = kickoffDate;
        this.matchState = matchState;
        this.matchLink = matchLink;
        this.finalResult = finalResult;
    }

    public String getTeamOne() {
        return teamOne;
    }

    public String getTeamTwo() {
        return teamTwo;
    }

    public String getKickoffTime() {
        return kickoffTime;
    }

    public String getKickoffDate() {
        return kickoffDate;
    }

    public String getMatchState() {
        return matchState;
    }

    public String getMatchLink() {
        return matchLink;
    }

    public String getFinalResult() {
        return finalResult;
    }

}
