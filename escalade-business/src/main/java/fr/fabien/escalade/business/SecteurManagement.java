package fr.fabien.escalade.business;

import fr.fabien.escalade.consumer.topo.SecteurRepository;
import fr.fabien.escalade.model.topo.Secteur;
import fr.fabien.escalade.model.topo.Site;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class SecteurManagement extends CrudManager<Secteur, SecteurRepository> {

    public SecteurManagement(SecteurRepository repository) {
        super(repository);
    }

    public List<Secteur> findSecteursBySite_id(Long id) {
        return repository.findSecteursBySite_id(id);
    }

    public Secteur findSecteurById(Long id) {
        return repository.findSecteurById(id);
    }
}
