/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.toabels.footcrawler.rest.controller;

import de.toabels.footcrawler.model.json.MatchDto;
import de.toabels.footcrawler.rest.service.ResultService;
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
public class ResultController {

    @Autowired
    private ResultService resultService;

    @RequestMapping(value = "/results/{date}", method = GET)
    public List<MatchDto> getResults(@PathVariable("date") String date) {
        List<MatchDto> resultList = new ArrayList<>();
        try {
            resultList = resultService.listMatches(date);
        } catch (Exception ex) {
            System.out.println("ERROR!!!");
        }
        return resultList;
    }

}
