package fr.fabien.escalade.topo;

import java.util.Date;

public class Voie {
    // ----- Attributs

    private String nom;
    private Double longueur;
    private Date date;
    private String description;
    private Integer note;

    // ----- Constructors
    public Voie() {
    }

    public Voie(String nom, Double longueur, Date date, String description, Integer note) {
        this.nom = nom;
        this.longueur = longueur;
        this.date = date;
        this.description = description;
        this.note = note;
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

    // ----- Methodes
}
