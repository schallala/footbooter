package de.toabels.footcrawler.infra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "de.toabels.footcrawler")
@EntityScan("de.toabels.footcrawler.model.db")
@EnableJpaRepositories("de.toabels.footcrawler.model.repository")
public class FootcrawlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootcrawlerApplication.class, args);
    }

}
