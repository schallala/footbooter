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
public class TeamDto {

    private String name;
    private String country;
    private String flag;
    private String infoLink;

    public TeamDto(String name, String country, String flag, String infoLink) {
        this.name = name;
        this.country = country;
        this.flag = flag;
        this.infoLink = infoLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }


}
