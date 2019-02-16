package fr.fabien.escalade.model.topo;

import fr.fabien.escalade.model.utilisateur.Utilisateur;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Topo {
    // ----- Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private Date date;
    private Integer departement;
    private String statut;
    private Integer note;

    @ManyToOne
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "topo")
    private Set<Site> sites;

    // ----- Constructors

    public Topo() {
    }

    public Topo(String nom, Date date, Integer departement, String statut, Set<Site> sites, Integer note) {
        this.nom = nom;
        this.date = date;
        this.departement = departement;
        this.statut = statut;
        this.sites = sites;
        this.note = note;
    }
    // ----- Getters / Setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDepartement() {
        return departement;
    }

    public void setDepartement(Integer departement) {
        this.departement = departement;
    }

    public String getStatut() {
        return statut;
    }

    public Integer getNote() {
        return note;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Set<Site> getSites() {
        return sites;
    }

    public void setSites (Set<Site> sites) {
        this.sites = sites;
    }

    public void setNote(Integer note) {
        this.note = note;
    }


    // ----- Methodes
    public void ajoutSite(Site site) {
        this.sites.add(site);
    }

    public void supprimerSite (Site site) {
        this.sites.remove(site);
    }
}
