package fr.fabien.escalade.model.topo;

import fr.fabien.escalade.model.topo.Voie;
import fr.fabien.escalade.model.topo.Utilisateur;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
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

    // ----- Methodes
}
