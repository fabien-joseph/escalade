package fr.fabien.escalade.consumer.topo;

import fr.fabien.escalade.model.topo.Voie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoieRepository extends CrudRepository<Voie, Long> {

    Voie findFirstByNom (String nom);

    Voie findVoieById (Long id);
}
