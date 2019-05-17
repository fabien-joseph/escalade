package fr.fabien.escalade.model.topo;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull(message = "Le nom ne doit pas être vide")
    @Size(min = 3, max = 30, message = "Le nom doit contenir entre 3 et 30 caractères")
    private String nom;
    @Min(1)
    @Max(1000)
    private Integer longueur;
    private Date date;
    @Size(max = 256, message = "La description doit contenir maximum 256 catactères")
    private String description;
    @Min(1)
    @Max(30)
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
