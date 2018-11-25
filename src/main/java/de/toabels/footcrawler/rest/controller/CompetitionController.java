/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.toabels.footcrawler.rest.controller;

import de.toabels.footcrawler.model.json.MatchDto;
import de.toabels.footcrawler.model.json.TeamDto;
import de.toabels.footcrawler.rest.service.CompetitionService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abels
 */
@RestController
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @RequestMapping(value = "/competition/{competition}/matches/{matchDay}", method = GET)
    public List<MatchDto> getMatches(@PathVariable("competition") String competition,
         @PathVariable("matchDay") String matchDay) throws IOException {
        return competitionService.listMatches("bundesliga/vereine/1-bundesliga/", "2018-19");
    }

    @RequestMapping(value = "/competition/test", method = GET)
    public List<MatchDto> initCompetitions() {
        competitionService.initCompetitionMap();
        return null;
    }

    @RequestMapping(value = "/standings", method = GET)
    public List<TeamDto> getStandings() throws IOException {
        return competitionService.getStandings();
    }

    @RequestMapping(value = "/competition/{competition}/teams", method = GET)
    public List<TeamDto> getTeams(@PathVariable("competition") String competition) throws IOException {
        return competitionService.listTeams("bundesliga/vereine/1-bundesliga/", "2018-19");
    }

    @RequestMapping(value = "/competition/list", method = GET)
    public List<MatchDto> listCompetitions() {
        return null;
    }
}
