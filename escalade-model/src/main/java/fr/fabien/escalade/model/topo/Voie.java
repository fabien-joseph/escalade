package fr.fabien.escalade.model.topo;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Voie extends Publication {
    // ----- Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private Double longueur;
    private Date date;
    private String description;

    @ManyToOne
    private Secteur secteur;

    @OneToMany(mappedBy = "voie")
    private List<Note> notes = new ArrayList<>();

    // ----- Constructors
    public Voie() {
    }

    public Voie(String nom, Double longueur, Date date, String description, Secteur secteur, List<Note> notes) {
        this.nom = nom;
        this.longueur = longueur;
        this.date = date;
        this.description = description;
        this.secteur = secteur;
        this.notes = notes;
    }
}
