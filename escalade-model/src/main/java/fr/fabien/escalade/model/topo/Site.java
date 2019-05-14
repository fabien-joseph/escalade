package fr.fabien.escalade.model.topo;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

    public Site(String nom, Date date, String cotationMin, String cotationMax, String departement, Integer hauteurMin, Integer hauteurMax, String orientation, String type, String description, String localisation, Utilisateur utilisateur, List<Commentaire> commentaires, List<Note> notes, List<Topo> topos, List<Secteur> secteurs) {
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
