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
import java.util.Set;

@Entity
@Getter
@Setter
public class Secteur extends Publication {
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

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OneToMany (mappedBy = "secteur")
    private List<Voie> voies = new ArrayList<>();

    @ManyToOne
    private Site site;

    // ----- Constructors
    public Secteur() {
    }

    public Secteur(String nom, Date date, String cotationMin, String cotationMax, Integer hauteurMin, Integer hauteurMax, List<Voie> voies, Site site) {
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
