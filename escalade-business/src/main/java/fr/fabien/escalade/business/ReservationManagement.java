package fr.fabien.escalade.business;

import fr.fabien.escalade.consumer.topo.ReservationRepository;
import fr.fabien.escalade.model.topo.Reservation;
import fr.fabien.escalade.model.topo.Topo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class ReservationManagement extends CrudManager<Reservation, ReservationRepository> {

    public ReservationManagement(ReservationRepository repository) {
        super(repository);
    }

    public boolean isFree(Reservation reservation) {
        List<Reservation> reservations = repository.validDate(reservation.getDateDebut(), reservation.getDateFin());
        return reservations.size() <= 0;
    }

    public List<Reservation> findAllByTopo (Topo topo) {
        return repository.findAllByTopo(topo);
    }

    public List<Reservation> findAllByUtilisateur_Id(Long utilisateurId) {
        return repository.findAllByUtilisateur_Id(utilisateurId);
    }

    @Override
    public void save(Reservation reservation) {
        repository.save(reservation);
    }
}
