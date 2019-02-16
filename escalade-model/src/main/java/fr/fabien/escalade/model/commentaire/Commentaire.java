package fr.fabien.escalade.model.commentaire;

import fr.fabien.escalade.model.topo.Voie;
import fr.fabien.escalade.model.utilisateur.Utilisateur;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Commentaire {
    // ----- Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String commentaire;
    private Date date;

    @ManyToOne private Voie voie;
    @ManyToOne private Utilisateur utilisateur;

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
