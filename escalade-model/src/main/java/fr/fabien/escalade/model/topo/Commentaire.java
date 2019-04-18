package fr.fabien.escalade.model.topo;

import fr.fabien.escalade.model.topo.Voie;
import fr.fabien.escalade.model.topo.Utilisateur;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonManagedReference;

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

    @ManyToOne
    @JsonManagedReference
    private Voie voie;


    @ManyToOne
    @JsonManagedReference
    private Utilisateur utilisateur;

    // ----- Constructors
    public Commentaire() {
    }

    public Commentaire(String commentaire, Date date) {
        this.commentaire = commentaire;
        this.date = date;
    }

    // ----- Methodes
}
