package fr.fabien.escalade.consumer.topo;

import fr.fabien.escalade.model.topo.Secteur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecteurRepository extends CrudRepository<Secteur, Long> {
    List<Secteur> findSecteursBySite_id (Long site_id);
}
