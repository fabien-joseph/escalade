package fr.fabien.escalade.business;

import fr.fabien.escalade.consumer.topo.VoieRepository;
import fr.fabien.escalade.model.topo.Secteur;
import fr.fabien.escalade.model.topo.Voie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class VoieManagement extends CrudManager<Voie, VoieRepository> {
    @Autowired
    SecteurManagement secteurManagement;
    @Autowired
    VoieManagement voieManagement;

    public VoieManagement(VoieRepository repository) {
        super(repository);
    }

    public Iterable<Voie> findAll() {
        return repository.findAll();
    }

    public Voie findVoieById(Long id) {
        return repository.findVoieById(id);
    }

    /*
    @Override
    public void save(Voie voie) {
        Secteur secteur = voie.getSecteur();
        secteur.getVoies().add(voie);
        secteurManagement.save(secteur);
        secteurManagement.updateMinMax(secteur);
    }

     */
/*
    @Override
    public void deleteById(Long id) {
        Optional<Voie> voie = voieManagement.findById(id);
        if (voie.isPresent()) {
            Secteur secteur = voie.get().getSecteur();
            secteur.getVoies().remove(voie.get());
            secteurManagement.save(secteur);
            secteurManagement.updateMinMax(secteur);
            repository.delete(voie.get());
        }
    }

 */
}
