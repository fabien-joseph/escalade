package fr.fabien.escalade.consumer.topo;

import fr.fabien.escalade.model.topo.Voie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoieRepository extends CrudRepository<Voie, Long> {
}
