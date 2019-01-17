package fr.fabien.escalade.model.topo;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Site {
    // ----- Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long site_id;

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

    @Column(name = "orientation")
    private String orientation;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "localisation")
    private String localisation;

    @Column(name = "note")
    private Integer note;

    private List<Secteur> secteurs;

    // ---- Constructors
    public Site() {
    }

    public Site(String nom, Date date, String cotationMin, String cotationMax, Integer hauteurMin, Integer hauteurMax,
                String orientation, String type, String description, String localisation, List<Secteur> secteurs,
                Integer note) {
        this.nom = nom;
        this.date = date;
        this.cotationMin = cotationMin;
        this.cotationMax = cotationMax;
        this.hauteurMin = hauteurMin;
        this.hauteurMax = hauteurMax;
        this.orientation = orientation;
        this.type = type;
        this.description = description;
        this.localisation = localisation;
        this.secteurs = secteurs;
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

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public List<Secteur> getSecteurs() {
        return secteurs;
    }

    public Integer getNote() {
        return note;
    }

    // ----- Methodes
    public void ajoutSecteur(Secteur secteur) {
        this.secteurs.add(secteur);
    }

    public void supprimerSecteur (Secteur secteur) {
        this.secteurs.remove(secteur);
    }

    public void calculMoyenne() {
        int i = 0;
        int valeurTotale = 0;

        while (i < secteurs.size()) {
            valeurTotale = secteurs.get(i).getNote();
            i++;
        }
        this.note = valeurTotale / i;
    }
}
