package fr.fabien.escalade.webapp;

import fr.fabien.escalade.business.commentaire.CommentaireManagement;
import fr.fabien.escalade.business.topo.SecteurManagement;
import fr.fabien.escalade.business.topo.SiteManagement;
import fr.fabien.escalade.business.topo.TopoManagement;
import fr.fabien.escalade.model.commentaire.Commentaire;
import fr.fabien.escalade.model.topo.Secteur;
import fr.fabien.escalade.model.topo.Site;
import fr.fabien.escalade.model.topo.Topo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@SpringBootApplication (scanBasePackages = {"fr.fabien.escalade"})
@ComponentScan("fr.fabien.escalade.*")
@EntityScan ("fr.fabien.escalade.model")
@EnableJpaRepositories ("fr.fabien.escalade.consumer")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

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
}