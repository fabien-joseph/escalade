package fr.fabien.escalade.model.topo;

import fr.fabien.escalade.model.commentaire.Commentaire;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Voie {
    // ----- Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private Double longueur;
    private Date date;
    private String description;
    private Integer note;

    @ManyToOne private Secteur secteur;

    @OneToMany (mappedBy = "voie")
    private Set<Commentaire> commentaires;

    // ----- Constructors
    public Voie() {
    }

    public Voie(String nom, Double longueur, Date date, String description, Integer note, Secteur secteur) {
        this.nom = nom;
        this.longueur = longueur;
        this.date = date;
        this.description = description;
        this.note = note;
        this.secteur = secteur;
    }

    // ----- Getters / Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getLongueur() {
        return longueur;
    }

    public void setLongueur(Double longueur) {
        this.longueur = longueur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    // ----- Methodes
}
