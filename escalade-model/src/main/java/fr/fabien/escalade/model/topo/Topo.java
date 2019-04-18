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
    private String departement;
    private Boolean isEnable;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private Utilisateur utilisateurReserv;

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @ManyToMany
    private List<Site> sites = new ArrayList<>();

    // ----- Constructors

    public Topo() {
    }

    public Topo(String nom, Date date, String departement, boolean isEnable, Utilisateur utilisateur, List<Site> sites) {
        this.nom = nom;
        this.date = date;
        this.departement = departement;
        this.isEnable = isEnable;
        this.utilisateur = utilisateur;
        this.sites = sites;
    }
}
