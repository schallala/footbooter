/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.toabels.footcrawler.model.mapping;

import de.toabels.footcrawler.model.db.Team;
import de.toabels.footcrawler.model.json.TeamDto;
import org.springframework.stereotype.Component;

/**
 *
 * @author abels
 */
@Component
public class ModelMapper {
    public Team toEntity(TeamDto dto){
        Team team = new Team();
        team.setName(dto.getName());
        team.setCountry(dto.getCountry());
        team.setFlag(dto.getFlag());
//        team.setId(dto.getId());
        team.setInfoLink(dto.getInfoLink());
        return team;
    }
}
