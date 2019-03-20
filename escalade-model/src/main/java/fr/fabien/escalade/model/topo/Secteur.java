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
public class Secteur {
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

    @OneToMany (mappedBy = "secteur")
    private Set<Voie> voies;

    @ManyToOne private Site site;

    // ----- Constructors
    public Secteur() {
    }

    public Secteur(String nom, Date date, String cotationMin, String cotationMax, Integer hauteurMin, Integer hauteurMax, Set<Voie> voies, Site site) {
        this.nom = nom;
        this.date = date;
        this.cotationMin = cotationMin;
        this.cotationMax = cotationMax;
        this.hauteurMin = hauteurMin;
        this.hauteurMax = hauteurMax;
        this.voies = voies;
        this.site = site;
    }
}
