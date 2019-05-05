package fr.fabien.escalade.consumer.topo;

import fr.fabien.escalade.model.topo.Reservation;
import fr.fabien.escalade.model.topo.Secteur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
