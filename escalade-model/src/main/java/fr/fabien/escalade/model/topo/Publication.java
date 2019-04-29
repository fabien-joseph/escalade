package fr.fabien.escalade.model.topo;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
public abstract class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    public List<Commentaire> commentaires;
}
