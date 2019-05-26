package fr.fabien.escalade.business;

import fr.fabien.escalade.consumer.topo.ReservationRepository;
import fr.fabien.escalade.model.topo.Reservation;
import fr.fabien.escalade.model.topo.Topo;
import fr.fabien.escalade.model.topo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class ReservationManagement extends CrudManager<Reservation, ReservationRepository> {
    @Autowired
    UtilisateurManagement utilisateurManagement;

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
        if (utilisateurManagement.findById(utilisateurId).isPresent()) {
            Utilisateur utilisateur = utilisateurManagement.findById(utilisateurId).get();
            Date date = new Date(System.currentTimeMillis());
            return repository.isReservedByUser(date, utilisateur);
        }
        return null;
    }

    public List<Reservation> findAllByUtilisateur_IdAndDate(Long utilisateurId) {
        return repository.findAllByUtilisateur_Id(utilisateurId);
    }


    public Reservation findFirstByTopo(Topo topo) {
        Date date = new Date(System.currentTimeMillis());
        List<Reservation> reservations = repository.actualyReserved(date, topo);
        if (reservations.size() > 0)
            return reservations.get(0);
        return null;
    }

    @Override
    public void save(Reservation reservation) {
        repository.save(reservation);
    }
}
