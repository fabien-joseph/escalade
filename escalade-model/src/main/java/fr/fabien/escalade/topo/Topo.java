package fr.fabien.escalade.topo;

import java.util.Date;
import java.util.List;

public class Topo {
    // ----- Attributs
    private String nom;
    private Date date;
    private Integer departement;
    private String statut;
    private List<Site> sites;
    private Integer note;

    // ----- Constructors
    public Topo() {
    }

    public Topo(String nom, Date date, Integer departement, String statut, List<Site> sites, Integer note) {
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

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public List<Site> getSites() {
        return sites;
    }

    public Integer getNote() {
        return note;
    }

    // ----- Methodes
    public void ajoutSite(Site site) {
        this.sites.add(site);
    }

    public void supprimerSite (Site site) {
        this.sites.remove(site);
    }

    public void calculMoyenne() {
        int i = 0;
        int valeurTotale = 0;

        while (i < sites.size()) {
            valeurTotale = sites.get(i).getNote();
            i++;
        }
        this.note = valeurTotale / i;
    }
}
