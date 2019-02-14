package fr.fabien.escalade.webapp;

import fr.fabien.escalade.business.topo.TopoManagement;
import fr.fabien.escalade.consumer.topo.TopoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication (scanBasePackages = {"fr.fabien.escalade"})
@ComponentScan("fr.fabien.escalade.business")
@EntityScan ("fr.fabien.escalade.model")
@EnableJpaRepositories ("fr.fabien.escalade.consumer")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    TopoManagement topoManagement;

    @PostConstruct
    public void init() {
        topoManagement.findAll();
    }
}

