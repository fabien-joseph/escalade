package fr.fabien.escalade.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication (scanBasePackages = {"fr.fabien.escalade.*"})
@ComponentScan("fr.fabien.escalade.*")
@EntityScan ("fr.fabien.escalade.*")
@EnableJpaRepositories ("fr.fabien.escalade.*")
public class App extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
        return app.sources(App.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}