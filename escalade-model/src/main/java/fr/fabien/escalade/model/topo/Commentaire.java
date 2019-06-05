package fr.fabien.escalade.model.topo;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonManagedReference;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter
@Setter
public class Commentaire {
    // ----- Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min = 3, max = 255, message = "Le commentaire doit contenir entre 3 et 255 caractères")
    private String content;
    private Date date;

    @ManyToOne
    @JsonManagedReference
    private Site site;

    @ManyToOne
    @JsonManagedReference
    private Topo topo;

    @ManyToOne
    @JsonManagedReference
    private Utilisateur utilisateur;

    // ----- Constructors
    public Commentaire() {
    }

    public Commentaire(String content, Date date, Site site, Topo topo, Utilisateur utilisateur) {
        this.content = content;
        this.date = date;
        this.site = site;
        this.topo = topo;
        this.utilisateur = utilisateur;
    }

// ----- Methodes
}
