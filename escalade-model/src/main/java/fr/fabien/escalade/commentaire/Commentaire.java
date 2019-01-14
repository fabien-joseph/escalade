package fr.fabien.escalade.commentaire;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Commentaire {
    // ----- Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentaire_id;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "date")
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
