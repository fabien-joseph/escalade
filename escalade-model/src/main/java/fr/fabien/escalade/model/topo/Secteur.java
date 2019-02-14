package fr.fabien.escalade.model.topo;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Secteur {
    // ----- Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long secteur_id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "date")
    private Date date;

    @Column(name = "cotationmin")
    private String cotationMin;

    @Column(name = "cotationmax")
    private String cotationMax;

    @Column(name = "hauteurmin")
    private Integer hauteurMin;

    @Column(name = "hauteurmax")
    private Integer hauteurMax;

    @Column(name = "note")
    private Integer note;

    @Transient
    private List<Voie> voies;

    // ----- Constructors
    public Secteur() {
    }

    public Secteur(String nom, Date date, String cotationMin, String cotationMax, Integer hauteurMin,
                   Integer hauteurMax, List<Voie> voies, Integer note) {
        this.nom = nom;
        this.date = date;
        this.cotationMin = cotationMin;
        this.cotationMax = cotationMax;
        this.hauteurMin = hauteurMin;
        this.hauteurMax = hauteurMax;
        this.voies = voies;
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
