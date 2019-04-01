package fr.fabien.escalade.business;

import fr.fabien.escalade.consumer.topo.TopoRepository;
import fr.fabien.escalade.model.topo.Topo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class TopoManagement {
    @Autowired private TopoRepository repository;

    public List<Topo> findToposByUtilisateur_id (Long id) {
        return repository.findToposByUtilisateur_id(id);
    }

    public Topo findTopoById(Long id) {
        return repository.findTopoById(id);
    }

    public List<Topo> findToposByDepartement(String departement) {
        return repository.findToposByDepartement(departement);
    }

    public List<Topo> findAll() {
        return repository.findAll();
    }

    public void ajout(Topo topo) {
        Topo testTopo = repository.findFirstByNom(
                topo.getNom()
        );

        if (testTopo != null) {
            System.out.println("Erreur - Ce topo existe déjà, id = " + testTopo.getId());
        } else {
            repository.save(topo);
        }
    }
}
