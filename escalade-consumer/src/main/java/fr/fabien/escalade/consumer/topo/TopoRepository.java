package fr.fabien.escalade.consumer.topo;

import fr.fabien.escalade.model.topo.Site;
import fr.fabien.escalade.model.topo.Topo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopoRepository extends CrudRepository<Topo, Long> {
    List<Topo> findToposByUtilisateur_id(Long utilisateur_id);

    Topo findTopoById (Long id);

    List<Topo> findAll();

    Topo findFirstByNom (String nom);
}

