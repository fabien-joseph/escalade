package fr.fabien.escalade.business;

import fr.fabien.escalade.consumer.topo.ReservationRepository;
import fr.fabien.escalade.model.topo.Reservation;
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
        List<Reservation> reservations = repository.validDate(reservation.getDateFin(), reservation.getDateFin());
        return reservations.size() <= 0;
    }

    @Override
    public void save(Reservation reservation) {
        repository.save(reservation);
    }

}
