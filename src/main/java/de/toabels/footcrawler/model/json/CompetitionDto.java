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
public class CompetitionDto {

    private final Long id;
    private final String competitionKey;
    private final String title;
    private final String season;
    private final String country;
    private final String competitionType;
    
    public CompetitionDto(Long id, String competitionKey, String title, String season, String country,
        String competitionType) {
        this.id = id;
        this.competitionKey = competitionKey;
        this.title = title;
        this.season = title;
        this.country = country;
        this.competitionType = competitionType;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCompetitionKey() {
        return competitionKey;
    }

    public String getCountry() {
        return country;
    }

    public String getCompetitionType() {
        return competitionType;
    }

    public String getSeason() {
        return season;
    }

    
}
