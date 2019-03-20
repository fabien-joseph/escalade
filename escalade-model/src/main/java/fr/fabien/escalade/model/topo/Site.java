package fr.fabien.escalade.model.topo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    private Topo topo;

    @OneToMany (mappedBy = "site")
    private Set<Secteur> secteurs;


    // ---- Constructors

    public Site() {
    }

    public Site(String nom, Date date, String cotationMin, String cotationMax, Integer hauteurMin, Integer hauteurMax, String orientation, String type, String description, String localisation, Topo topo) {
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
        this.topo = topo;
    }
}
