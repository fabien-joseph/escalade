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
public class Voie {
    // ----- Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private Double longueur;
    private Date date;
    private String description;
    private Integer note;


    @ManyToOne private Secteur secteur;

    @OneToMany(mappedBy = "voie")
    private List<Note> notes;


    @OneToMany (mappedBy = "voie")
    private Set<Commentaire> commentaires;

    // ----- Constructors
    public Voie() {
    }

    public Voie(String nom, Double longueur, Date date, String description, Integer note, Secteur secteur) {
        this.nom = nom;
        this.longueur = longueur;
        this.date = date;
        this.description = description;
        this.note = note;
        this.secteur = secteur;
    }
}
