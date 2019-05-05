package fr.fabien.escalade.business;

import fr.fabien.escalade.consumer.topo.TopoRepository;
import fr.fabien.escalade.model.topo.Topo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class TopoManagement extends CrudManager<Topo, TopoRepository> {

    public TopoManagement(TopoRepository repository) {
        super(repository);
    }

    public List<Topo> findToposByUtilisateur_id (Long id) {
        return repository.findToposByUtilisateur_id(id);
    }

    public Topo findTopoById(Long id) {
        return repository.findTopoById(id);
    }

    public List<Topo> findAll() {
        return repository.findAll();
    }
}
