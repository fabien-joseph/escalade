package fr.fabien.escalade.model.topo;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Topo {
    // ----- Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private Date date;
    private Boolean isEnable;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private Utilisateur utilisateurReserv;


    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @ManyToMany
    private List<Site> sites = new ArrayList<>();

    @OneToMany(mappedBy = "topo")
    private List<Commentaire> commentaires = new ArrayList<>();

    @OneToMany
    private List<Publication> publications = new ArrayList<>();

    @OneToMany(mappedBy = "topo")
    private List<Reservation> reservations = new ArrayList<>();

    // ----- Constructors

    public Topo() {
    }

    public Topo(String nom, Date date, Boolean isEnable, Utilisateur utilisateur, Utilisateur utilisateurReserv, List<Commentaire> commentaires, List<Site> sites) {
        this.nom = nom;
        this.date = date;
        this.isEnable = isEnable;
        this.utilisateur = utilisateur;
        this.utilisateurReserv = utilisateurReserv;
        this.commentaires = commentaires;
        this.sites = sites;
    }
}
