package fr.fabien.escalade.model.topo;

import javax.persistence.*;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Integer note;

    @ManyToOne
    private Voie voie;

    @ManyToOne
    private Utilisateur utilisateur;
}
