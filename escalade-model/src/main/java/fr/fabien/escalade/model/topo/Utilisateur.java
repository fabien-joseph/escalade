package fr.fabien.escalade.model.topo;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull
    private String login;
    @NotNull
    private String motDePasse;

    @NotNull
    @Email(message = "Le courriel doit être valide")
    private String courriel;

    @NotNull
    @Size(min = 3, max = 60, message = "Le prénom doit contenir entre 3 et 60 caractères")
    private String prenom;

    @Size(min = 3, max = 60, message = "Le nom doit contenir entre 3 et 60 caractères")
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
    private List<Reservation> reservations = new ArrayList<>();

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
