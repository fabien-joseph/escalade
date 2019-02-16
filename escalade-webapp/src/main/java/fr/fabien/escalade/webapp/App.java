package fr.fabien.escalade.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication (scanBasePackages = {"fr.fabien.escalade"})
@ComponentScan("fr.fabien.escalade.business")
@EntityScan ("fr.fabien.escalade.model")
@EnableJpaRepositories ("fr.fabien.escalade.consumer")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}