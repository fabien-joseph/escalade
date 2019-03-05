package fr.fabien.escalade.model.topo;

import javax.persistence.*;
import java.util.Set;

@Entity
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

    // ----- Getters / Setters
    public Long getUtilisateur_id() {
        return id;
    }

    public void setUtilisateur_id(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }


    // ----- Methodes
}
