package fr.fabien.escalade.model.topo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Integer note;
    private Date date;

    @ManyToOne
    private Voie voie;

    @ManyToOne
    private Utilisateur utilisateur;
}
