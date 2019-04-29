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
    private String content;
    private Date date;

    @ManyToOne
    @JsonManagedReference
    private Publication publication;

    @ManyToOne
    @JsonManagedReference
    private Utilisateur utilisateur;

    // ----- Constructors
    public Commentaire() {
    }

    public Commentaire(String content, Date date, Utilisateur utilisateur) {
        this.content = content;
        this.date = date;
        this.utilisateur = utilisateur;
    }

// ----- Methodes
}
