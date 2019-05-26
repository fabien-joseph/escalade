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
public class Topo {
    // ----- Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Le nom ne doit pas être vide.")
    @Size(min = 3, max = 30, message = "Le nom doit contenir entre 3 et 30 caractères.")
    @Pattern(regexp = "\\w+", message = "Le nom ne doit contenir que des caractères de A à Z et de 0 à 9.")
    private String nom;
    private Date date;
    private Boolean isEnable;

    @ManyToOne
    private Utilisateur utilisateur;

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @ManyToMany (cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "site_topo", joinColumns = @JoinColumn(name = "topo_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "site_id", referencedColumnName = "id"))
    private List<Site> sites = new ArrayList<>();

    @OneToMany(mappedBy = "topo")
    private List<Commentaire> commentaires = new ArrayList<>();

    @OneToMany(mappedBy = "topo", cascade = CascadeType.REMOVE)
    private List<Reservation> reservations = new ArrayList<>();

    // ----- Constructors

    public Topo() {
    }

    public Topo(String nom, Date date, Boolean isEnable, Utilisateur utilisateur, List<Commentaire> commentaires, List<Site> sites) {
        this.nom = nom;
        this.date = date;
        this.isEnable = isEnable;
        this.utilisateur = utilisateur;
        this.commentaires = commentaires;
        this.sites = sites;
    }
}
