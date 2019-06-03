package fr.fabien.escalade.model.topo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Pattern(regexp = "\\w+", message = "Le nom ne doit contenir que des caractères de A à Z et de 0 à 9.")
    private String nom;
    @NotNull(message = "La longueur ne doit pas être vide.")
    @Min(value = 1, message = "La longueur doit être à 1 minimum")
    @Max(value = 1000, message = "La longueur doit être à 1000 maximum")
    private Integer longueur;
    private Date date;
    @NotNull (message = "La cotation ne doit pas être vide.")
    @Min(value = 1, message = "La cotation doit être à 1 minimum")
    @Max(value = 30, message = "La cotation doit être à 9c maximum")
    private Integer cotation;

    @ManyToOne
    private Secteur secteur;

    // ----- Constructors
    public Voie() {
    }

    public Voie(String nom, Integer longueur, Date date, Secteur secteur, Integer cotation) {
        this.nom = nom;
        this.longueur = longueur;
        this.date = date;
        this.secteur = secteur;
        this.cotation = cotation;
    }
}
