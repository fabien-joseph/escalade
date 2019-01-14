package fr.fabien.escalade.topo;

import java.util.Date;
import java.util.List;

public class Secteur {
    // ----- Attributs
    private String nom;
    private Date date;
    private String cotationMin;
    private String cotationMax;
    private Integer hauteurMin;
    private Integer hauteurMax;
    private List<Voie> voies;
    private Integer note;

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

    public String getCotationMin() {
        return cotationMin;
    }

    public void setCotationMin(String cotationMin) {
        this.cotationMin = cotationMin;
    }

    public String getCotationMax() {
        return cotationMax;
    }

    public void setCotationMax(String cotationMax) {
        this.cotationMax = cotationMax;
    }

    public Integer getHauteurMin() {
        return hauteurMin;
    }

    public void setHauteurMin(Integer hauteurMin) {
        this.hauteurMin = hauteurMin;
    }

    public Integer getHauteurMax() {
        return hauteurMax;
    }

    public void setHauteurMax(Integer hauteurMax) {
        this.hauteurMax = hauteurMax;
    }

    public List<Voie> getVoies() {
        return voies;
    }

    public Integer getNote() {
        return note;
    }

    // ----- Methodes
    public void ajoutSite(Voie voie) {
        this.voies.add(voie);
    }

    public void supprimerSite (Voie voie) {
        this.voies.remove(voie);
    }

    public void calculMoyenne() {
        int i = 0;
        int valeurTotale = 0;

        while (i < voies.size()) {
            valeurTotale = voies.get(i).getNote();
            i++;
        }
        this.note = valeurTotale / i;
    }
}
