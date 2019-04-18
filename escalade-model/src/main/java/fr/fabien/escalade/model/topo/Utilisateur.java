package fr.fabien.escalade.model.topo;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Utilisateur {
    // ----- Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String login;
    private String motDePasse;
    private String courriel;
    private String prenom;
    private String nom;
    private Boolean isAdmin;
    private Date date;

    @OneToMany (mappedBy = "utilisateur")
    @JsonIgnore
    private List<Topo> topos = new ArrayList<>();

    @OneToMany (mappedBy = "utilisateur")
    @JsonIgnore
    private List<Site> sites = new ArrayList<>();

    @OneToMany (mappedBy = "utilisateur")
    @JsonIgnore
    private List<Topo> notes = new ArrayList<>();

    @OneToMany (mappedBy = "utilisateur")
    @JsonIgnore
    private List<Commentaire> commentaires = new ArrayList<>();

    @OneToMany (mappedBy = "utilisateur")
    @JsonIgnore
    private List<Topo> reservation = new ArrayList<>();

    // ----- Constructors
    public Utilisateur() {
    }

    public Utilisateur(String login, String motDePasse, String courriel, String prenom, String nom, Boolean isAdmin, List<Topo> topos, List<Topo> notes, List<Commentaire> commentaires) {
        this.login = login;
        this.motDePasse = motDePasse;
        this.courriel = courriel;
        this.prenom = prenom;
        this.nom = nom;
        this.isAdmin = isAdmin;
        this.topos = topos;
        this.notes = notes;
        this.commentaires = commentaires;
    }
}
