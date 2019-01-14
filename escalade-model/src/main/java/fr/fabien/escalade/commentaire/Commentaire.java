package fr.fabien.escalade.commentaire;

import java.util.Date;

public class Commentaire {
    // ----- Attributs
    private String commentaire;
    private Date date;

    // ----- Constructors
    public Commentaire() {
    }

    public Commentaire(String commentaire, Date date) {
        this.commentaire = commentaire;
        this.date = date;
    }

    // ----- Getters / Setters
    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // ----- Methodes
}
