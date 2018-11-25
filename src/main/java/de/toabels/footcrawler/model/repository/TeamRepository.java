/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.toabels.footcrawler.model.repository;

import de.toabels.footcrawler.model.db.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abels
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
