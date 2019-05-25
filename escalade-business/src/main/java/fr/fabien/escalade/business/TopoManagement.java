package fr.fabien.escalade.business;

import fr.fabien.escalade.consumer.topo.TopoRepository;
import fr.fabien.escalade.model.topo.Site;
import fr.fabien.escalade.model.topo.Topo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class TopoManagement extends CrudManager<Topo, TopoRepository> {
    @Autowired
    ReservationManagement reservationManagement;

    public TopoManagement(TopoRepository repository) {
        super(repository);
    }

    public List<Topo> findToposByUtilisateur_id(Long id) {
        return repository.findToposByUtilisateur_id(id);
    }

    public Topo findTopoById(Long id) {
        return repository.findTopoById(id);
    }

    public List<Topo> findAll() {
        return repository.findAll();
    }

    public List<Topo> toposFilter(List<Topo> topos) {
        List<Long> listId = new ArrayList<>();
        for (int i = 0; i < topos.size(); i++) {
            for (int j = (i - 1); j >= 0; j--) {
                if (topos.get(i).getId() == topos.get(j).getId()) {
                    topos.remove(topos.get(i));
                    i--;
                }
            }
            if (reservationManagement.actualyReserved(topos.get(i)).size() > 0) {
                topos.remove(topos.get(i));
                i--;
            }
        }
        return topos;
    }

    public List<Topo> findAllBySites_Id(List<Site> sites) {
        List<Topo> topos = new ArrayList<>();

        for (Site site : sites) {
            topos.addAll(repository.findAllBySitesId(site.getId()));
        }

        topos = toposFilter(topos);

        return topos;
    }
}
