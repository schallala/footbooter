/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.toabels.footcrawler.rest.service;

import de.toabels.footcrawler.model.json.CompetitionDto;
import de.toabels.footcrawler.model.json.MatchDto;
import de.toabels.footcrawler.model.json.TeamDto;
import de.toabels.footcrawler.model.repository.TeamRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.toabels.footcrawler.model.mapping.ModelMapper;

/**
 *
 * @author abels
 */
@Service
public class CompetitionService {
    
    @Autowired
    private TeamRepository teamRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    private static final Logger LOG = Logger.getLogger(CompetitionService.class.getName());

    private static final String BASE = "http://www.kicker.de/news/fussball/";

    private static final String INT_LEAGUE_HOME = BASE + "intligen/intwettbewerbe/internationale-ligen.html";

    private static final String INT_CUP_HOME = BASE + "intligen/intpokale/internationale-pokale.html";

    private static final String START = "spieltag.html";

    private static final String TEAMS = "vereine-liste.html";

    private final Map<Long, CompetitionDto> competitionMap = new HashMap<>();

    public void initCompetitionMap() {
        // first add main competitions
        competitionMap.put(1L, new CompetitionDto(1L,
            "bundesliga/spieltag/1-bundesliga/", "Bundesliga", "2018/19", "Deutschland", "LEAGUE"));
        competitionMap.put(2L, new CompetitionDto(2L,
            "2bundesliga/spieltag/2-bundesliga/", "2. Liga", "2018/19", "Deutschland", "LEAGUE"));
        competitionMap.put(3L, new CompetitionDto(3L,
            "3liga/spieltag/3-liga/", "3. Liga", "2018-19", "Deutschland", "LEAGUE"));
        competitionMap.put(4L, new CompetitionDto(4L,
            "dfbpokal/spielrunde/dfb-pokal/", "DFB-Pokal", "2018/19", "Deutschland", "CUP"));
        competitionMap.put(5L, new CompetitionDto(5L,
            "chleague/spielrunde/champions-league/", "2018/19", "Championsleague", "INTERNATIONAL", "CUP"));
        competitionMap.put(6L, new CompetitionDto(6L,
            "uefa/spielrunde/europa-league/", "Europa-League", "2018/19", "INTERNATIONAL", "CUP"));
        competitionMap.put(7L, new CompetitionDto(7L,
            "weltmeisterschaft/spiele/weltmeisterschaft/", "WM", "2018", "INTERNATIONAL", "CUP"));
        competitionMap.put(8L, new CompetitionDto(8L,
            "em/spielplan/europameisterschaft/", "EM", "2020", "INTERNATIONAL", "CUP"));
        // international leagues 
        try {
            initCompetitionOverview(INT_LEAGUE_HOME);
            // international cups 
//            initCompetitionOverview(INT_CUP_HOME);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error fetching international competitions");
        }
    }

    private void initCompetitionOverview(String base) throws IOException {
        LOG.log(Level.INFO, "Fetching competitions from %s...", base);
        Document doc = Jsoup.connect(base).get();
        Elements links = doc.select("tr");
        LOG.log(Level.INFO, "Found {0} row elements.", links.size());
        int counter = 0;
        String country = "";
        for (Element row : links) {
            Elements cells = row.select("td");
            if (!cells.isEmpty() && cells.get(0).hasClass("first")) {
                boolean found = !cells.get(0).text().isEmpty();
                country = found ? cells.get(0).text() : country;
                String title = cells.get(1).text();
                String season = cells.get(2).text();
                // filter deprecatedm relegaton matches and cups
                if (found || "2018/19".equals(season)) {
                    counter++;
                    // extract competition base url part as key
                    String competitionKey = cells.get(1).select("a[href]").get(0).attr("abs:href");
                    competitionKey = competitionKey.substring(BASE.length(), competitionKey.indexOf(season.replace('/', '-')));
                    String spieltag = cells.get(3).text();
                    LOG.log(Level.INFO, "Land: {0}, Liga: {1}, Key: {2}", new Object[]{country, title, competitionKey});
                }
            }
        }
        LOG.log(Level.INFO, "Found {0} leagues.", counter);
    }

    public List<CompetitionDto> listCompetitions() {
        return null;
    }

    public List<MatchDto> listMatches(String competitionKey, String season) throws IOException {
        String url = BASE + competitionKey + season + "/-1/0/" + START;
//        String url = "http://www.kicker.de/news/fussball/2bundesliga/spieltag/2-bundesliga/2018-19/-1/0/spieltag.html";
        LOG.log(Level.INFO, "Fetching %s...", url);
        List<MatchDto> resultList = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("tr");

        LOG.log(Level.INFO, "Found {0} row elements.", links.size());
        int counterRow = 0;

        String startTime = "";
        String status = "";
        for (Element row : links) {
            String matchLink = "";
            Elements cells = row.select("td");
            if (!cells.isEmpty() && cells.get(0).hasClass("first")) {
                startTime = cells.get(0).text().isEmpty() ? startTime : cells.get(1).text();
                boolean found = !startTime.isEmpty();
                boolean offset = !cells.get(2).text().isEmpty();
                String teamOne = offset ? cells.get(2).text() : cells.get(3).text();
                String teamTwo = offset ? cells.get(4).text() : cells.get(5).text();
                String combinedResult = offset ? cells.get(5).text() : cells.get(6).text();
                Elements link = offset ? cells.get(7).select("a[href]") : cells.get(8).select("a[href]");
                if (!link.isEmpty()) {
                    matchLink = link.get(0).attr("abs:href");
                    status = cells.get(6).text();
                    if ("Analyse".equals(status) || "Spielbericht".equals(status)) {
                        status = "Finished";
                    } else {
                        status = "Scheduled";
                    }
                }
                counterRow++;
                if (found) {
                    resultList.add(new MatchDto(teamOne, teamTwo, startTime, startTime, status, matchLink, combinedResult));
                    LOG.log(Level.INFO, "Zeit: {0}, {1} gegen {2} - {3}, Status: {4}, Matchlink = {5}",
                        new Object[]{startTime, teamOne, teamTwo, combinedResult, status, matchLink});
                }
            }
        }
        LOG.log(Level.INFO, "Found {0} matches.", counterRow);
        return resultList;
    }

    public List<TeamDto> listTeams(String competitionKey, String season) throws IOException {
        String url = BASE + competitionKey + season + "/" + TEAMS;
//        String url = "http://www.kicker.de/news/fussball/bundesliga/vereine/1-bundesliga/2018-19/vereine-liste.html";
        LOG.log(Level.INFO, "Fetching %s...", url);
        List<TeamDto> resultList = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("tr");
        
        LOG.log(Level.INFO, "Found {0} row elements.", links.size());
        int counterRow = 0;

        for (Element row : links) {
            Elements cells = row.select("td");
            if (!cells.isEmpty() && cells.get(0).hasClass("first")) {

                String teamName = cells.get(0).text();
                String flag = cells.get(0).select("img").attr("src");
                String infoLink = cells.get(0).select("a[href]").get(1).attr("abs:href");
                counterRow++;
//                    resultList.add(new MatchDto(teamOne, teamTwo, startTime, startTime, status, matchLink, combinedResult));
                byte[] bytes = Jsoup.connect(flag).ignoreContentType(true).execute().bodyAsBytes();
                LOG.log(Level.INFO, "Team-Name: {0}, Wappen: " + flag + "(" + bytes.length + " bytes)", teamName);
                TeamDto team = new TeamDto(teamName, "Deutschland", flag, infoLink);
                
                teamRepository.save(modelMapper.toEntity(team));
            }
        }
        LOG.log(Level.INFO, "Found {0} teams.", counterRow);
        return resultList;
    }

    public List<TeamDto> getStandings() throws IOException {
        String url = "http://www.kicker.de/news/fussball/bundesliga/spieltag/1-bundesliga/2018-19/spieltag.html";
        LOG.log(Level.INFO, "Fetching %s...", url);
        List<TeamDto> resultList = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Elements tables = doc.select("table");
        tables.stream().filter((table) -> ("tStat".equals(table.className()))).map((table) -> table.select("tr")).map((links) -> {
            LOG.log(Level.INFO, "Found {0} row elements.", links.size());
            int counterRow = 0;
            for (Element row : links) {
                Elements cells = row.select("td");
                if (!cells.isEmpty() && cells.get(0).hasClass("first")) {
                    String rank = cells.get(0).text();
                    String teamName = cells.get(2).text();
                    String matches = cells.get(4).text();
                    String won = cells.get(6).text();
                    String draw = cells.get(7).text();
                    String lost = cells.get(8).text();
                    String goals = cells.get(10).text();
                    String diff = cells.get(11).text();
                    String points = cells.get(13).text();
                    counterRow++;
                    //                    resultList.add(new MatchDto(teamOne, teamTwo, startTime, startTime, status, matchLink, combinedResult));
                    LOG.log(Level.INFO, "Team-Name: {1}, Rank {0} -  {2} - {3} - {4} - {5}, goals: {6} - {7}, points: {8}"
                        , new Object[]{rank, teamName, matches, won, draw, lost, goals, diff, points});
                }
            }
            return counterRow;
        }).forEachOrdered((counterRow) -> {
            LOG.log(Level.INFO, "Found {0} teams.", counterRow);
        });
        return resultList;
    }
}


