package fr.fabien.escalade.model.topo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Formula;

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

    @NotNull(message = "Le nom ne doit pas être vide.")
    @Size(min = 3, max = 30, message = "Le nom doit contenir entre 3 et 30 caractères.")
    @Pattern(regexp = "\\w+", message = "Le nom ne doit contenir que des caractères de A à Z et de 0 à 9.")
    private String nom;
    private Date date;
    @Formula(value = "(SELECT MIN(v.cotation) FROM voie AS v INNER JOIN secteur AS s ON v.secteur_id = s.id WHERE s.site_id = id)")
    private Integer cotationMin;
    @Formula("(SELECT MAX(v.cotation) FROM voie AS v INNER JOIN secteur AS s ON v.secteur_id = s.id WHERE s.site_id = id)")
    private Integer cotationMax;
    @Formula("(SELECT MIN(v.longueur) FROM voie AS v INNER JOIN secteur AS s ON v.secteur_id = s.id WHERE s.site_id = id)")
    private Integer hauteurMin;
    @Formula("(SELECT MAX(v.longueur) FROM voie AS v INNER JOIN secteur AS s ON v.secteur_id = s.id WHERE s.site_id = id)")
    private Integer hauteurMax;
    @NotNull (message = "L'orientation ne doit pas être vide.")
    private String departement;
    @NotNull(message = "L'orientation ne doit pas être vide.")
    @Pattern(regexp = "^(Nord|Sud|Est|Ouest)", message = "L'orientation indiquée n'est pas valide.")
    private String orientation;
    @NotNull(message = "Le bloc ne doit pas être vide.")
    @Pattern(regexp = "^(Roche|Bloc)", message = "Le type indiqué n'est pas valide.")
    private String type;
    @Size(max = 256, message = "La description doit contenir maximum 256 catactères.")
    private String description;
    @Size(max = 256, message = "La description doit contenir maximum 256 catactères.")
    private String localisation;

    @ManyToOne
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "site", cascade = CascadeType.REMOVE)
    private List<Commentaire> commentaires = new ArrayList<>();

    @ManyToMany (cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "site_topo", joinColumns = @JoinColumn(name = "site_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "topo_id", referencedColumnName = "id"))
    private List<Topo> topos = new ArrayList<>();

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OneToMany(mappedBy = "site", cascade = CascadeType.REMOVE)
    private List<Secteur> secteurs = new ArrayList<>();

    // ---- Constructors
    public Site() {
    }

    public Site(String nom, Date date, Integer cotationMin, Integer cotationMax, String departement, Integer hauteurMin, Integer hauteurMax, String orientation, String type, String description, String localisation, Utilisateur utilisateur, List<Commentaire> commentaires, List<Topo> topos, List<Secteur> secteurs) {
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
        this.topos = topos;
        this.secteurs = secteurs;
    }
}
