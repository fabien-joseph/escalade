package fr.fabien.escalade.consumer.topo;

import fr.fabien.escalade.model.topo.Reservation;
import fr.fabien.escalade.model.topo.Topo;
import fr.fabien.escalade.model.topo.Utilisateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE ((r.dateDebut <= :dateStart AND r.dateFin >= :dateStart) OR " +
            "(r.dateDebut <= :dateEnd AND r.dateFin >= :dateEnd)) AND r.topo = :topo")
    List<Reservation> validDate(@Param("dateStart") Date dateStart, @Param("dateEnd") Date dateEnd, @Param("topo") Topo topo);

    @Query("SELECT r FROM Reservation r WHERE (r.dateDebut <= :today AND r.dateFin >= :today) AND r.topo = :topo")
    List<Reservation> actualyReserved(@Param("today") Date date, @Param("topo") Topo topo);

    @Query("SELECT r FROM Reservation r WHERE (r.dateDebut <= :today AND r.dateFin >= :today) AND r.utilisateur = :utilisateur")
    List<Reservation> isReservedByUser(@Param("today") Date date, @Param("utilisateur") Utilisateur utilisateur);

    List<Reservation> findAllByTopo(Topo topo);

    List<Reservation> findAllByUtilisateur_Id(Long utilisateurId);
}
