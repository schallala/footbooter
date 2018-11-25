/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.toabels.footcrawler.rest.service;

import de.toabels.footcrawler.model.json.MatchDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 *
 * @author abels
 */
@Service
public class ResultService {

    private static final Logger LOG = Logger.getLogger(ResultService.class.getName());

    public List<MatchDto> listMatches(String date) throws IOException {
        String url = "http://www.kicker.de/news/live-news/livescores/" + date + "/livescores_fussball.html";
        LOG.log(Level.INFO, "Fetching {0}...", url);
        List<MatchDto> resultList = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("tr");
        LOG.log(Level.INFO, "Found {0} row elements.", links.size());
        String startTime = "";
        String teamOne = "";
        String teamTwo = "";
        String combinedResult = "";
        String competitionKey = "";
        String competitionLink = "";
        int counterRow = 0;

        for (Element row : links) {
            String matchLink = "";
            String status = "";
            boolean found = false;
            Elements cells = row.select("td");
            if (!cells.isEmpty() && cells.get(0).hasClass("first")) {
                startTime = cells.get(0).text().isEmpty() ? startTime : cells.get(0).text();
                if(!cells.get(2).text().isEmpty()){
                    competitionKey = cells.get(2).text();
                    competitionLink = cells.get(2).select("a[href]").get(0).attr("abs:href");
                }
                found = true;
                teamOne = cells.get(3).text();
                teamTwo = cells.get(5).text();
                combinedResult = cells.get(6).text();
                Elements link = cells.get(7).select("a[href]");
                if (!link.isEmpty()) {
                    matchLink = link.get(0).attr("abs:href");
                    status = cells.get(7).text();
                    status = "Live".equals(status) ? status : "FINISHED";
                }else{
                    status = "Scheduled";
                }
                counterRow++;
            }
            if (found) {
                resultList.add(new MatchDto(teamOne, teamTwo, startTime, date, status, matchLink, combinedResult));
                LOG.log(Level.INFO, "Zeit: {0}, [{6}, {7}] {1} gegen {2} - {3}, Status: {4}, Matchlink = {5}",
                    new Object[]{startTime, teamOne, teamTwo, combinedResult, status, matchLink, competitionKey, competitionLink});
            }
        }
        LOG.log(Level.INFO, "Found {0} matches.", counterRow);
        return resultList;
    }

    //competition=weltmeisterschaft
    //http://www.kicker.de/news/fussball/europameisterschaft/spiele/europameisterschaft/2018/2/0/spieltag.html    
    //http://www.kicker.de/news/fussball/bundesliga/spieltag/1bundesliga/2018-19/-1/0/spieltag.html    
}
