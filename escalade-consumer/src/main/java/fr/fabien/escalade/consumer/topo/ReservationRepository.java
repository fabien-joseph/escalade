package fr.fabien.escalade.consumer.topo;

import fr.fabien.escalade.model.topo.Reservation;
import fr.fabien.escalade.model.topo.Secteur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE (r.dateDebut <= :dateStart AND r.dateFin >= :dateStart) OR " +
            "(r.dateDebut <= :dateEnd AND r.dateFin >= :dateEnd)")
    List<Reservation> validDate(@Param("dateStart") Date dateStart, @Param("dateEnd") Date dateEnd);
}
