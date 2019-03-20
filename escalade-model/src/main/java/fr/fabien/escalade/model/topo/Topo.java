package fr.fabien.escalade.model.topo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Topo {
    // ----- Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private Date date;
    private String departement;
    private String statut;

    @ManyToOne
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "topo")
    private Set<Site> sites;

    // ----- Constructors

    public Topo() {
    }

    public Topo(String nom, Date date, String departement, String statut, Set<Site> sites) {
        this.nom = nom;
        this.date = date;
        this.departement = departement;
        this.statut = statut;
        this.sites = sites;
    }

    // ----- Methodes
    public void ajoutSite(Site site) {
        this.sites.add(site);
    }

    public void supprimerSite (Site site) {
        this.sites.remove(site);
    }
}
