package fr.fabien.escalade.model.topo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToMany (mappedBy = "utilisateur")
    private Set<Topo> topos;

    @OneToMany (mappedBy = "utilisateur")
    private List<Topo> notes;

    @OneToMany (mappedBy = "utilisateur")
    private Set<Commentaire> commentaires;

    // ----- Constructors
    public Utilisateur() {
    }

    public Utilisateur(Long id, String login, String motDePasse, String courriel, String prenom, String nom,
                       Boolean isAdmin) {
        this.id = id;
        this.login = login;
        this.motDePasse = motDePasse;
        this.courriel = courriel;
        this.prenom = prenom;
        this.nom = nom;
        this.isAdmin = isAdmin;
    }

    // ----- Methodes
}
