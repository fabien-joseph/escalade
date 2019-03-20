package fr.fabien.escalade.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication (scanBasePackages = {"fr.fabien.escalade.*"})
@ComponentScan("fr.fabien.escalade.*")
@EntityScan ("fr.fabien.escalade.*")
@EnableJpaRepositories ("fr.fabien.escalade.*")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

/*
    @Autowired
    TopoManagement topoManagement;

    @Autowired
    CommentaireManagement commentaireManagement;

    @Autowired
    SiteManagement siteManagement;

    @Autowired
    SecteurManagement secteurManagement;

    @PostConstruct
    public void init() {
        List<Commentaire> commentaires = commentaireManagement.findCommentairesByUtilisateur_id(1L);
        System.out.println("commentaire : " + commentaires.get(0).getCommentaire());

        List<Topo> topos = topoManagement.findToposByUtilisateur_id(1L);
        System.out.println("nom topo : " + topos.get(0).getNom());

        List<Site> sites = siteManagement.findSitesByTopo_id(1L);
        System.out.println("nom site : " + sites.get(0).getNom());

        List<Secteur> secteurs = secteurManagement.findSecteursBySite_id(1L);
        System.out.println("nom : " + secteurs.get(0).getNom());
    }
        */
}