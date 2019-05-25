package fr.fabien.escalade.business;

import fr.fabien.escalade.consumer.topo.ReservationRepository;
import fr.fabien.escalade.model.topo.Reservation;
import fr.fabien.escalade.model.topo.Topo;
import fr.fabien.escalade.model.topo.Utilisateur;
import org.springframework.data.repository.query.Param;
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
        List<Reservation> reservations = repository.validDate(reservation.getDateDebut(), reservation.getDateFin(), reservation.getTopo());
        return reservations.size() <= 0;
    }

    public List<Reservation> actualyReserved(Topo topo) {
        Date date = new Date(System.currentTimeMillis());
        return repository.actualyReserved(date, topo);
    }

    public boolean isReservedByUser(Utilisateur utilisateur) {
        Date date = new Date(System.currentTimeMillis());
        return repository.isReservedByUser(date, utilisateur).size() > 0;
    }

    public List<Reservation> findAllByTopo(Topo topo) {
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
