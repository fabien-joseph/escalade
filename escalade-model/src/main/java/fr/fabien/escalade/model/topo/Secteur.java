package fr.fabien.escalade.model.topo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Secteur {
    // ----- Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Le nom ne doit pas être vide")
    @Size(min = 3, max = 30, message = "Le nom doit contenir entre 3 et 30 caractères")
    @Pattern(regexp = "\\w+", message = "Le nom ne doit contenir que des caractères de A à Z et de 0 à 9.")
    private String nom;
    private Date date;
    private Integer cotationMin;
    private Integer cotationMax;
    private Integer hauteurMin;
    private Integer hauteurMax;

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OneToMany (mappedBy = "secteur", cascade = CascadeType.REMOVE)
    private List<Voie> voies = new ArrayList<>();

    @ManyToOne
    private Site site;

    // ----- Constructors
    public Secteur() {
    }

    public Secteur(String nom, Date date, Integer cotationMin, Integer cotationMax, Integer hauteurMin, Integer hauteurMax, List<Voie> voies, Site site) {
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
