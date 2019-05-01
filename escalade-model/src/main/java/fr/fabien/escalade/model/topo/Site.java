package fr.fabien.escalade.model.topo;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Site {
    // ----- Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private Date date;
    private String cotationMin;
    private String cotationMax;
    private Integer hauteurMin;
    private Integer hauteurMax;
    private String orientation;
    private String type;
    private String description;
    private String localisation;

    @ManyToOne
    private Utilisateur utilisateur;

    @OneToMany (mappedBy = "site")
    private List<Commentaire> commentaires = new ArrayList<>();

    @OneToMany List<Note> notes = new ArrayList<>();

    @ManyToMany
    private List<Topo> topo = new ArrayList<>();

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OneToMany (mappedBy = "site")
    private List<Secteur> secteurs = new ArrayList<>();


    // ---- Constructors

    public Site() {
    }

    public Site(String nom, Date date, String cotationMin, String cotationMax, Integer hauteurMin, Integer hauteurMax, String orientation, String type, String description, String localisation, Utilisateur utilisateur, List<Commentaire> commentaires, List<Note> notes, List<Topo> topo, List<Secteur> secteurs) {
        this.nom = nom;
        this.date = date;
        this.cotationMin = cotationMin;
        this.cotationMax = cotationMax;
        this.hauteurMin = hauteurMin;
        this.hauteurMax = hauteurMax;
        this.orientation = orientation;
        this.type = type;
        this.description = description;
        this.localisation = localisation;
        this.utilisateur = utilisateur;
        this.commentaires = commentaires;
        this.notes = notes;
        this.topo = topo;
        this.secteurs = secteurs;
    }
}
