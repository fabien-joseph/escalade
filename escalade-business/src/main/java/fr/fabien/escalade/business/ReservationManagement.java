package fr.fabien.escalade.business;

import fr.fabien.escalade.consumer.topo.ReservationRepository;
import fr.fabien.escalade.consumer.topo.SiteRepository;
import fr.fabien.escalade.model.topo.Reservation;
import fr.fabien.escalade.model.topo.Site;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ReservationManagement extends CrudManager<Reservation, ReservationRepository> {

    public ReservationManagement(ReservationRepository repository) {
        super(repository);
    }

}
