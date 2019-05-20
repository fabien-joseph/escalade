package fr.fabien.escalade.model.topo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import javax.validation.constraints.*;
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

    @NotNull(message = "Le nom ne doit pas être vide")
    @Size(min = 3, max = 30, message = "Le nom doit contenir entre 3 et 30 caractères")
    private String nom;
    private Date date;
    private Integer cotationMin;
    private Integer cotationMax;
    private String departement;
    private Integer hauteurMin;
    private Integer hauteurMax;
    private String orientation;
    private String type;
    private String description;
    private String localisation;

    @ManyToOne
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "site")
    private List<Commentaire> commentaires = new ArrayList<>();

    @OneToMany
    List<Note> notes = new ArrayList<>();

    @ManyToMany (mappedBy = "sites")
    private List<Topo> topos = new ArrayList<>();

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OneToMany(mappedBy = "site")
    private List<Secteur> secteurs = new ArrayList<>();

    // ---- Constructors
    public Site() {
    }

    public Site(String nom, Date date, Integer cotationMin, Integer cotationMax, String departement, Integer hauteurMin, Integer hauteurMax, String orientation, String type, String description, String localisation, Utilisateur utilisateur, List<Commentaire> commentaires, List<Note> notes, List<Topo> topos, List<Secteur> secteurs) {
        this.nom = nom;
        this.date = date;
        this.cotationMin = cotationMin;
        this.cotationMax = cotationMax;
        this.departement = departement;
        this.hauteurMin = hauteurMin;
        this.hauteurMax = hauteurMax;
        this.orientation = orientation;
        this.type = type;
        this.description = description;
        this.localisation = localisation;
        this.utilisateur = utilisateur;
        this.commentaires = commentaires;
        this.notes = notes;
        this.topos = topos;
        this.secteurs = secteurs;
    }
}
