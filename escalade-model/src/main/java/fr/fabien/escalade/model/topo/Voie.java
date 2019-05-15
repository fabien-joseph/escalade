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
public class Voie {
    // ----- Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private Integer longueur;
    private Date date;
    private String description;
    private Integer cotation;

    @ManyToOne
    private Secteur secteur;

    @OneToMany(mappedBy = "voie")
    private List<Note> notes = new ArrayList<>();

    // ----- Constructors
    public Voie() {
    }

    public Voie(String nom, Integer longueur, Date date, String description, Secteur secteur, List<Note> notes, Integer cotation) {
        this.nom = nom;
        this.longueur = longueur;
        this.date = date;
        this.description = description;
        this.secteur = secteur;
        this.notes = notes;
        this.cotation = cotation;
    }
}
