package fr.fabien.escalade.model.utilisateur;

import javax.persistence.*;

@Entity
public class Utilisateur {
    // ----- Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long utilisateur_id;

    @Column(name = "login")
    private String login;

    @Column(name = "motdepasse")
    private String motDePasse;

    @Column(name = "courriel")
    private String courriel;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "nom")
    private String nom;

    @Column(name = "isAdmin")
    private Boolean isAdmin;

    // ----- Constructors
    public Utilisateur() {
    }

    public Utilisateur(Long utilisateur_id, String login, String motDePasse, String courriel, String prenom, String nom,
                       Boolean isAdmin) {
        this.utilisateur_id = utilisateur_id;
        this.login = login;
        this.motDePasse = motDePasse;
        this.courriel = courriel;
        this.prenom = prenom;
        this.nom = nom;
        this.isAdmin = isAdmin;
    }

    // ----- Getters / Setters
    public Long getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(Long utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
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
