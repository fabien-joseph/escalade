package fr.fabien.escalade.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication (scanBasePackages = {"fr.fabien.escalade.*"})
@ComponentScan("fr.fabien.escalade.*")
@EntityScan ("fr.fabien.escalade.*")
@EnableJpaRepositories ("fr.fabien.escalade.*")
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }
}